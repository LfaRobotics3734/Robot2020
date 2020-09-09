package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveForwardTime extends Command {
    double time;
    double speed;
    boolean done;
    Timer timeToMoveFor;
    VictorSPX motorFrontL, motorFrontR, motorBackL, motorBackR; 
    
    public MoveForwardTime(double time, double speed, VictorSPX motorFrontL, VictorSPX motorFrontR, VictorSPX motorBackL, VictorSPX motorBackR){
        super("MoveForwardTime");
        this.time = time;
        this.speed = speed;
        done = false;
        timeToMoveFor = new Timer();
        this.motorFrontL = motorFrontL;
        this.motorFrontR = motorFrontR;
        this.motorBackL = motorBackL;
        this.motorBackR = motorBackR;
        
    }
    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        if(timeToMoveFor.get() >= time){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void initialize(){
        timeToMoveFor.start();
    }

    @Override
    protected void execute(){
        var perc = 0.2;
        motorFrontL.set(ControlMode.PercentOutput, perc);
        motorFrontR.set(ControlMode.PercentOutput, -perc);
        motorBackL.set(ControlMode.PercentOutput, perc);
        motorBackR.set(ControlMode.PercentOutput, -perc);        
    }

    @Override
    protected void end(){
        motorFrontL.set(ControlMode.PercentOutput, 0);
        motorFrontR.set(ControlMode.PercentOutput, 0);
        motorBackL.set(ControlMode.PercentOutput, 0);
        motorBackR.set(ControlMode.PercentOutput, 0);
    }

}