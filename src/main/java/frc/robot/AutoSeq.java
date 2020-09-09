/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoSeq extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutoSeq(VictorSPX motorFrontL, VictorSPX motorFrontR, VictorSPX motorBackL, VictorSPX motorBackR, VictorSPX motorElev, VictorSP motorShoot) {
    addSequential(new MoveBackwardTime(1, .5, motorFrontL, motorFrontR, motorBackL, motorBackR));
    //addSequential(new MoveForwardTime(4, .5, motorFrontL, motorFrontR, motorBackL, motorBackR));
    addSequential(new Shoot(3, .8, .6, motorShoot, motorElev));
    addSequential(new MoveBackwardTime(3, .5, motorFrontL, motorFrontR, motorBackL, motorBackR));
    
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
