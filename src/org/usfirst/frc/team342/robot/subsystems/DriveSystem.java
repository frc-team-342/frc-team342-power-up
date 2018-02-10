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
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSystem extends Subsystem {

	private static final DriveSystem INSTANCE = new DriveSystem();

	private WPI_TalonSRX leftMaster;
	private WPI_TalonSRX leftFollow;
	private WPI_TalonSRX rightMaster;
	private WPI_TalonSRX rightFollow;
	private TalonSRX centerWheel;

	private DoubleSolenoid pneumaticSuspension;

	private AnalogInput ultrasonicOne;
	private AnalogInput ultrasonicTwo;

	private AHRS navx;

	private boolean front;

	private DifferentialDrive drive;

	// Here are the current variables. Change them at will.
	private static final int AMPS = 200;
	private static final int TIMEOUT_MS = 10;
	private static final int PEAK_DURATION = 200;
	private static final int AMPSCENTER = 35;

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

		leftMaster = new WPI_TalonSRX(RobotMap.LEFTMASTER);
		leftFollow = new WPI_TalonSRX(RobotMap.LEFTFOLLOW);
		rightMaster = new WPI_TalonSRX(RobotMap.RIGHTMASTER);
		rightFollow = new WPI_TalonSRX(RobotMap.RIGHTFOLLOW);
		centerWheel = new TalonSRX(RobotMap.CENTERWHEEL);
		pneumaticSuspension = new DoubleSolenoid(RobotMap.PNEUMATICWHEEL_UP, RobotMap.PNEUMATICWHEEL_DOWN);
		ultrasonicOne = new AnalogInput(RobotMap.ULTRASONIC_ONE);
		ultrasonicTwo = new AnalogInput(RobotMap.ULTRASONIC_TWO);
		navx = new AHRS(SPI.Port.kMXP);
		front = false;

		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		// leftFollow.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		// rightFollow.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		drive = new DifferentialDrive(leftMaster, rightMaster);

		// This is to control the current. If you want to change any of the bellow
		// variables go to where it is declared
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
		
		rightMaster.configOpenloopRamp(0.1, 50);
		leftMaster.configOpenloopRamp(0.1, 50);

		centerWheel.configPeakCurrentLimit(AMPSCENTER, TIMEOUT_MS);
		centerWheel.configPeakCurrentDuration(PEAK_DURATION, TIMEOUT_MS);
		centerWheel.configContinuousCurrentLimit(AMPSCENTER, TIMEOUT_MS);
		centerWheel.enableCurrentLimit(true);

	}

	public void drive(double Left_joy_Y, double Right_joy_Y, double X_average, double deadzone) {

		if (!front) {
			Left_joy_Y = Left_joy_Y * -1.0;
			Right_joy_Y = Right_joy_Y * -1.0;
			X_average = X_average * -1.0;
		}

		if (Math.abs(Right_joy_Y) > deadzone || Math.abs(Left_joy_Y) > deadzone) {
			
			/* -1.0 temp to test right going backwards. */
			rightMaster.set(-1.0 * Right_joy_Y);
			rightFollow.set(-1.0 * Right_joy_Y);

			leftMaster.set(Left_joy_Y);
			leftFollow.set(Left_joy_Y);
		} else {
			
			rightMaster.set(0.0);
			rightFollow.set(0.0);
			leftMaster.set(0.0);
			leftFollow.set(0.0);
		}

		if (Math.abs(X_average) > deadzone) {
			centerWheel.set(ControlMode.PercentOutput, X_average);
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

	/** @return True if the wheel is down. */
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

	public double getLeftFollowerEncoder() {

		return leftFollow.getSelectedSensorPosition(0);
	}

	public double getRightMasterEncoder() {

		return rightMaster.getSelectedSensorPosition(0);
	}

	public double getRightFollowerEncoder() {

		return rightFollow.getSelectedSensorPosition(0);
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

}
