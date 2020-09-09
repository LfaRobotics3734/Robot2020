/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class Climb extends CommandBase {
  private DoubleSolenoid solenoid;
  double time;
  Timer timeToClimb;
  boolean done;

  public Climb(double time, DoubleSolenoid _solenoid) {
      this.time = time;
      done = false;
      timeToClimb = new Timer();
      this.solenoid = _solenoid;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timeToClimb.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    solenoid.set(DoubleSolenoid.Value.kReverse);
    solenoid.set(DoubleSolenoid.Value.kForward);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timeToClimb.get() >= time){
      return true;
    }else{
      return false;
    }
  }
}
