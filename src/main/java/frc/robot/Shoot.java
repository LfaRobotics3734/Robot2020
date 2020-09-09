package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {  
    private static final double delay = 1;
    private static final double timeForOneBall = 2;
    double time;
    double speedPercShoot;
    double speedPercElev;
    boolean done;
    Timer timeToShoot;
    VictorSP motorShoot;
    VictorSPX motorElev;
    
    public Shoot(final int numBalls, final double speedPercShoot, final double speedPercElev, final VictorSP motorShoot, final VictorSPX motorElev) {
        timeToShoot = new Timer();
        this.time = timeForOneBall * numBalls;
        this.speedPercElev = speedPercElev;
        this.speedPercShoot = speedPercShoot;
        this.motorShoot = motorShoot;
        this.motorElev = motorElev;
        done = false;
    }

    @Override
    protected boolean isFinished() {
        if(timeToShoot.get() >= time){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void initialize(){
        timeToShoot.start();
        motorShoot.set( -speedPercShoot);
    }

    @Override
    protected void execute(){
        if(timeToShoot.get() >= delay){
            motorElev.set(ControlMode.PercentOutput, -speedPercElev);
        }
    }

    @Override
    protected void end(){
        motorElev.set(ControlMode.PercentOutput, 0);
        motorShoot.set(0);
    }

}