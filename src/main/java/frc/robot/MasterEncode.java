package frc.robot;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class MasterEncode extends Robot {
  TalonSRX _talon1 = new TalonSRX(0); /* make a Talon */
  TalonSRX _talon2 = new TalonSRX(0); /* make a Talon */
  TalonSRX _talon3 = new TalonSRX(0); /* make a Talon */
  TalonSRX _talon4 = new TalonSRX(0); /* make a Talon */
  Joystick _joystick = new Joystick(0); /* make a joystick */
  Faults _faults = new Faults(); /* temp to fill with latest faults */

  public double circumference;
  public double lastRead[] = new double[4];
  public double curRead[] = new double[4];
  public boolean first = true;
  public double distLeft;
  public double distRight;
  public double distToTravel; //Maybe turn this to an array of distances or a 2d array

  @Override
  public void teleopInit() {
    /* factory default values */
    _talon1.configFactoryDefault();
    _talon2.configFactoryDefault();
    _talon3.configFactoryDefault();
    _talon4.configFactoryDefault();

    /*
     * choose whatever you want so "positive" values moves mechanism forward,
     * upwards, outward, etc.
     *
     * Note that you can set this to whatever you want, but this will not fix motor
     * output direction vs sensor direction.
     */
    _talon1.setInverted(false);
    _talon2.setInverted(false);
    _talon3.setInverted(false);
    _talon4.setInverted(false);

    /*
     * flip value so that motor output and sensor velocity are the same polarity. Do
     * this before closed-looping
     */
    _talon1.setSensorPhase(false); // <<<<<< Adjust this
    _talon2.setSensorPhase(false); // <<<<<< Adjust this
    _talon3.setSensorPhase(false); // <<<<<< Adjust this
    _talon4.setSensorPhase(false); // <<<<<< Adjust this
  }

  @Override
  public void teleopPeriodic() {
    //double xSpeed = _joystick.getRawAxis(1) * -1; // make forward stick positive

    /* update motor controller */
    //_talon.set(ControlMode.PercentOutput, xSpeed);
    /* check our live faults */
    //_talon.getFaults(_faults);
    /* hold down btn1 to print stick values */
    //if (_joystick.getRawButton(1)) {
      /*System.out.println("Sensor Vel:" + _talon.getSelectedSensorVelocity());
      System.out.println("Sensor Pos:" + _talon.getSelectedSensorPosition());
      System.out.println("Out %" + _talon.getMotorOutputPercent());
      System.out.println("Out Of Phase:" + _faults.SensorOutOfPhase);*/
      curRead[0] = _talon1.getSelectedSensorPosition();
      curRead[1] = _talon2.getSelectedSensorPosition();
      curRead[2] = _talon3.getSelectedSensorPosition();
      curRead[3] = _talon4.getSelectedSensorPosition();
      if(!first){
      distLeft += ((lastRead[0]-curRead[0]) * circumference/4096) + (lastRead[1]-curRead[1]) * circumference/4096)/2;
      distRight += ((lastRead[2]-curRead[2]) * circumference/4096) + (lastRead[3]-curRead[3]) * circumference/4096)/2;
      } else {
        first = false;
      }
      lastRead[0] = curRead[0];
      lastRead[1] = curRead[1];
      lastRead[2] = curRead[2];
      lastRead[3] = curRead[3];
      //System.out.println("Dist Traveled: " + dist);
    
    if(!dist > distToTravel-(0.05*circumference)){
      _talon1.set(ControlMode.PercentOutput, 0.2);
      _talon2.set(ControlMode.PercentOutput, 0.2);
      _talon3.set(ControlMode.PercentOutput, 0.2);
      _talon4.set(ControlMode.PercentOutput, 0.2);
    }
    else if( !dist < distToTravel+(0.05*circumference)){
      _talon1.set(ControlMode.PercentOutput, -0.2);
      _talon2.set(ControlMode.PercentOutput, -0.2);
      _talon3.set(ControlMode.PercentOutput, -0.2);
      _talon4.set(ControlMode.PercentOutput, -0.2);
    }
    else{
      _talon1.set(ControlMode.PercentOutput, 0);
      _talon2.set(ControlMode.PercentOutput, 0);
      _talon3.set(ControlMode.PercentOutput, 0);
      _talon4.set(ControlMode.PercentOutput, 0);
    }
    //}
  }
}
