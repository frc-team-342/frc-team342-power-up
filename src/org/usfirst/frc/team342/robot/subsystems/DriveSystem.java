package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

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
	}

	public void drive(double X, double Y, double rot) {
		// TODO Add Code
	}

	public void driveKeepHeading(double X, double Y, double rot) {
		// TODO Add Code
	}

	public double getGyro() {
		return navx.getAngle();
	}

	public void resetGyro() {
		// TODO Add Code
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

	public double getFrontLeftEncoder() {
		return 0.0;
	}

	public double getFrontRightEncoder() {
		return 0.0;
	}

	public double getBackLeftEncoder() {
		return 0.0;
	}

	public double getBackRightEncoder() {
		return 0.0;
	}

	public double getUltrasonicOne() {
		return 0.0;
	}
	
	public double getUltrasonicTwo() {
		return 0.0;
	}

	public void changeFront() {
		// TODO Add Code
	}

	public void stopDrive() {
		// TODO Add Code
	}

	public void stopAll() {
		// TODO Add Code
	}

}
