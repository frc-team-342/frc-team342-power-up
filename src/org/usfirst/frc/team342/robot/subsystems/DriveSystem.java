package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveSystem extends Subsystem {

	private static final DriveSystem INSTANCE = new DriveSystem();

	private WPI_TalonSRX leftMaster;
	private WPI_TalonSRX leftFollow;
	private WPI_TalonSRX rightMaster;
	private WPI_TalonSRX rightFollow;
	private TalonSRX centerWheel;
	private Solenoid pneumaticSuspension;
	private AnalogInput ultrasonicOne;
	private AnalogInput ultrasonicTwo;
	private AHRS navx;
	private boolean front;
	private DifferentialDrive drive;
	private static final boolean DOWN = true;
	private static final boolean UP = false;

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
		pneumaticSuspension = new Solenoid(RobotMap.PNEUMATICWHEEL);
		ultrasonicOne = new AnalogInput(RobotMap.ULTRASONIC_ONE);
		ultrasonicTwo = new AnalogInput(RobotMap.ULTRASONIC_TWO);
		navx = new AHRS(SerialPort.Port.kMXP);
		front = true;

		leftFollow.follow(leftMaster);
		rightFollow.follow(rightMaster);

		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		leftFollow.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		rightFollow.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		drive = new DifferentialDrive(leftMaster, rightMaster);

	}

	public void drive(double Y_left, double Y_right, double X_left, double X_right, double deadzone) {

		double X_avg = (X_left + X_right) / 2.0;

		if (!front) {
			Y_left = Y_left * -1.0;
			Y_right = Y_right * -1.0;
			X_avg = X_avg * -1.0;
		}

		if (Math.abs(Y_right) >= deadzone || Math.abs(Y_left) >= deadzone) {
			drive.tankDrive(Y_left, Y_right);
		}

		if (getWheelState()) {
			if (Math.abs(X_left) >= deadzone || Math.abs(X_right) >= deadzone) {
				centerWheel.set(ControlMode.PercentOutput, X_avg);
			}
		}

	}

	public void driveKeepHeading(double X, double Y, double rot) {
	
		//need to make a main method for driving in autonomous to keep the current heading using the gyro
		
	}

	public double getGyro() {

		return navx.getAngle();
	}

	public void resetGyro() {

		navx.reset();
	}

	public void wheelDown() {

		pneumaticSuspension.set(DOWN);
	}

	public void wheelUp() {

		pneumaticSuspension.set(UP);
	}

	public boolean getWheelState() {

		return pneumaticSuspension.get();
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

		if (front) {
			front = false;
		} else {
			front = true;
		}
	}

	public void stopDrive() {

		leftMaster.set(0.0);
		rightMaster.set(0.0);
		centerWheel.set(ControlMode.PercentOutput, 0.0);
	}

	public void stopAll() {

		leftMaster.set(0.0);
		leftFollow.set(0.0);
		rightMaster.set(0.0);
		rightFollow.set(0.0);
		centerWheel.set(ControlMode.PercentOutput, 0.0);
		pneumaticSuspension.set(getWheelState());
	}

}
