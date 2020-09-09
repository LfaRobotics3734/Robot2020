package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class TurnAround extends Command {
    double time;
    double speed;
    boolean done;
    Timer timeToTurn;
    VictorSPX motorFrontL, motorFrontR, motorBackL, motorBackR; 
    
    public TurnAround(double speed, VictorSPX motorFrontL, VictorSPX motorFrontR, VictorSPX motorBackL, VictorSPX motorBackR){
        super("MoveForwardTime");
        timeToTurn = new Timer();
        this.speed = speed;
        this.motorFrontL = motorFrontL;
        this.motorFrontR = motorFrontR;
        this.motorBackL = motorBackL;
        this.motorBackR = motorBackR;
        done = false;
    }
    //
    @Override
    protected boolean isFinished() {
        // TODO Auto-generated method stub
        if(timeToTurn.get() >= time){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void initialize(){
        timeToTurn.start();
    }

    @Override
    protected void execute(){
        motorFrontL.set(ControlMode.PercentOutput, speed);
        motorFrontR.set(ControlMode.PercentOutput, speed);
        motorBackL.set(ControlMode.PercentOutput, speed);
        motorBackR.set(ControlMode.PercentOutput, speed);        
    }

    @Override
    protected void end(){
        motorFrontL.set(ControlMode.PercentOutput, 0);
        motorFrontR.set(ControlMode.PercentOutput, 0);
        motorBackL.set(ControlMode.PercentOutput, 0);
        motorBackR.set(ControlMode.PercentOutput, 0);
    }

}