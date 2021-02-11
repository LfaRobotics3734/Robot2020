package frc.robot;

/*import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.StatusFrameRate;
import com.ctre.CANTalon.TalonControlMode;*/
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.*;

public class Encode extends Command{
	//This is assuming that the ID of the talon is 0
	TalonSRX _tal = new TalonSRX(0);
	PlotThread _plotThread;

	public void teleopInit(){
		//tracks every 1ms, may want to lower this later to not use as much bandwidth
		_tal.setStatusFramPeriod(StatusFrameEnhanced.Status_2_Feedback0, 1);
		_tal.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncdoer_Relative);
		_plotThread = new PlotThread(this);
		new Thread(_plotThread).start();
	}

	public void teleopPeriodic(){
		_tal.set(ControlMode.PercentOutput, 0.25);
	}

	class PlotThread implement Runnable {
		Robot robot;

		public PlotThread(Robot robot){
			this.robot = robot;
		}

		public void run(){
			while(true){
				//This should measure and display readings?
				try{
					Thread.sleep(1);
				} catch (Exception e) {}
				double velocity = this.robot._tal.getSelectedSensorVelcoity(0);
				SmartDashboard.putNumber("vel", velocity);
			}
		}
	}
}

//https://github.com/CrossTheRoadElec/Phoenix-Examples-Languages/blob/master/Java%20General/MagEncoder_Relative/src/main/java/frc/robot/Robot.java
// That's where I got most of this code from.
//Make sure to figure out how to use Talon with Phoenix and NOT CANTalon
//since CANTalon is outdated and I don't think it's supported anymore
//https://docs.ctre-phoenix.com/en/stable/ch04_DoINeedThis.html
//https://docs.ctre-phoenix.com/en/stable/ch05_PrepWorkstation.html
//https://docs.ctre-phoenix.com/en/stable/ch14_MCSensor.html
