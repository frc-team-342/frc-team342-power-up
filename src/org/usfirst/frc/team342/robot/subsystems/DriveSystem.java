package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSystem extends Subsystem {

	// Instance of the class.
	private static final DriveSystem INSTANCE = new DriveSystem();

	// Motor Controllers.
	private WPI_TalonSRX leftMaster;
	private WPI_TalonSRX leftFollow;
	private WPI_TalonSRX rightMaster;
	private WPI_TalonSRX rightFollow;
	private TalonSRX centerWheel;

	// Pneumatic Solenoid.
	private DoubleSolenoid pneumaticSuspension;

	// Ultrasonic Sensors.
	private AnalogInput ultrasonicOne;
	private AnalogInput ultrasonicTwo;

	// NavX.
	private AHRS navx;

	// Booleans for controlling the robot.
	private boolean front;
	private boolean slow;

	// Current variables.
	private static final int AMPS = 200;
	private static final int TIMEOUT_MS = 10;
	private static final int PEAK_DURATION = 200;
	private static final int AMPSCENTER = 35;
	private static final double RAMP_TIME = 0.1;
	private  double scaleFactor = 1 / 4000; 

	public DriveSystem() {

		initializeDriveSystem();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public static DriveSystem getInstance() {

		return INSTANCE;
	}

	private void initializeDriveSystem() {

		// Configures various booleans for drive.
		// EX: If slow is true, the robot goes slow.
		front = false;
		slow = false;

		// Instantiate Motor Controllers, Sensors, Pneumatics, and the NavX.
		leftMaster = new WPI_TalonSRX(RobotMap.LEFTMASTER);
		leftFollow = new WPI_TalonSRX(RobotMap.LEFTFOLLOW);
		rightMaster = new WPI_TalonSRX(RobotMap.RIGHTMASTER);
		rightFollow = new WPI_TalonSRX(RobotMap.RIGHTFOLLOW);
		centerWheel = new TalonSRX(RobotMap.CENTERWHEEL);
		pneumaticSuspension = new DoubleSolenoid(RobotMap.PNEUMATICWHEEL_UP, RobotMap.PNEUMATICWHEEL_DOWN);
		ultrasonicOne = new AnalogInput(RobotMap.ULTRASONIC_ONE);
		ultrasonicTwo = new AnalogInput(RobotMap.ULTRASONIC_TWO);
		navx = new AHRS(SPI.Port.kMXP);
		
		// Inverts the right set of motors to make the wheels travel in same direction
		rightMaster.setInverted(true);
		rightFollow.setInverted(true);

		// Configures the encoder onto the master motor controllers.
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		// Controls the current. To change any of these change the constants at the top
		// of the class.
		leftMaster.configPeakCurrentLimit(AMPS, TIMEOUT_MS);
		leftMaster.configPeakCurrentDuration(PEAK_DURATION, TIMEOUT_MS);
		leftMaster.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		leftMaster.enableCurrentLimit(true);

		leftFollow.configPeakCurrentLimit(AMPS, TIMEOUT_MS);
		leftFollow.configPeakCurrentDuration(PEAK_DURATION, TIMEOUT_MS);
		leftFollow.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		leftFollow.enableCurrentLimit(true);

		rightMaster.configPeakCurrentLimit(AMPS, TIMEOUT_MS);
		rightMaster.configPeakCurrentDuration(PEAK_DURATION, TIMEOUT_MS);
		rightMaster.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		rightMaster.enableCurrentLimit(true);

		rightFollow.configPeakCurrentLimit(AMPS, TIMEOUT_MS);
		rightFollow.configPeakCurrentDuration(PEAK_DURATION, TIMEOUT_MS);
		rightFollow.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		rightFollow.enableCurrentLimit(true);

		// Configures the open loop ramp to set the motor to ramp up to speed after a
		// specified time other than jerking to full speed.
		rightMaster.configOpenloopRamp(RAMP_TIME, 0);
		rightFollow.configOpenloopRamp(RAMP_TIME, 0);
		leftFollow.configOpenloopRamp(RAMP_TIME, 0);
		leftMaster.configOpenloopRamp(RAMP_TIME, 0);

		// Controls the current of the center wheel. To change any of these change the
		// constants at the top of the class.
		centerWheel.configPeakCurrentLimit(AMPSCENTER, TIMEOUT_MS);
		centerWheel.configPeakCurrentDuration(PEAK_DURATION, TIMEOUT_MS);
		centerWheel.configContinuousCurrentLimit(AMPSCENTER, TIMEOUT_MS);
		centerWheel.enableCurrentLimit(true);

	}

	public void setslow(boolean slow) {
		this.slow = slow;
	}

	public void drive(double Left_joy_Y, double Right_joy_Y, double center, double deadzone) {

		// Check if the slow boolean is set to true to cut the robot's speed in half.
		if (slow) {

			Left_joy_Y /= 2.0;
			Right_joy_Y /= 2.0;
			SmartDashboard.putNumber("Test_1", Left_joy_Y);
			SmartDashboard.putNumber("Test_2", Right_joy_Y);
		}

		// Check if the front boolean is false to change the direction of the Inputs.
		if (!front) {

			Left_joy_Y = Left_joy_Y * -1.0;
			Right_joy_Y = Right_joy_Y * -1.0;
			center = center * -1.0;
		}

		if (Math.abs(Right_joy_Y) > deadzone || Math.abs(Left_joy_Y) > deadzone) {

			rightMaster.set(Right_joy_Y);
			rightFollow.set(Right_joy_Y);
			leftMaster.set(Left_joy_Y);
			leftFollow.set(Left_joy_Y);
		} else {

			rightMaster.set(0.0);
			rightFollow.set(0.0);
			leftMaster.set(0.0);
			leftFollow.set(0.0);
		}

		if (Math.abs(center) > deadzone && getWheelState()) {

			centerWheel.set(ControlMode.PercentOutput, center);
		} else {

			centerWheel.set(ControlMode.PercentOutput, 0.0);
		}
	}

	public void driveKeepHeading(double X, double Y, double rot) {

		// need to make a main method for driving in autonomous to keep the current
		// heading using the gyro

	}

	public double getGyro() {

		return navx.getAngle();
	}

	public void resetGyro() {

		navx.reset();
	}

	public void wheelDown() {

		pneumaticSuspension.set(Value.kForward);
	}

	public void wheelUp() {

		pneumaticSuspension.set(Value.kReverse);
	}

	public boolean getWheelState() {

		if (pneumaticSuspension.get().equals(Value.kForward)) {
			return true;
		} else {
			return false;
		}
	}

	public double getLeftMasterEncoder() {

		return leftMaster.getSelectedSensorPosition(0);
	}

	public double getRightMasterEncoder() {

		return rightMaster.getSelectedSensorPosition(0);
	}

	public double getUltrasonicOne() {

		// \/ Is this the right method to use to get an analog output? \/
		return ultrasonicOne.getAverageVoltage();
	}

	public double getUltrasonicTwo() {

		// \/ Is this the right method to use to get an analog output? \/
		return ultrasonicTwo.getAverageVoltage();
	}

	public void changeFront() {

		front = !front;
	}

	public void stopDrive() {

		rightMaster.set(0.0);
		rightFollow.set(0.0);
		leftMaster.set(0.0);
		leftFollow.set(0.0);
		centerWheel.set(ControlMode.PercentOutput, 0.0);
	}

	public void stopAll() {

		leftMaster.set(0.0);
		leftFollow.set(0.0);
		rightMaster.set(0.0);
		rightFollow.set(0.0);
		centerWheel.set(ControlMode.PercentOutput, 0.0);
	}
	
	public double getDistance(double encoder) {
		double feet; 
		feet = encoder * scaleFactor; 
		return feet; 
	}
	
	

}
