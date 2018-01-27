package org.usfirst.frc.team342.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSystem extends Subsystem {

	private static final DriveSystem INSTANCE = new DriveSystem();

	private boolean wheel_down;

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
		// TODO Add Code
		wheel_down = false;
	}

	public void drive(double X, double Y, double rot) {
		// TODO Add Code
	}

	public void driveKeepHeading(double X, double Y, double rot) {
		// TODO Add Code
	}

	public double getGyro() {
		// TODO Add Code
		return 0.0;
	}

	public void resetGyro() {
		// TODO Add Code
	}

	public void wheelDown() {
		// TODO Add Code
		wheel_down = true;
	}

	public void wheelUp() {
		// TODO Add Code
		wheel_down = false;
	}

	public boolean getWheelState() {
		return wheel_down;
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

	public double getUltrasonic() {
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
