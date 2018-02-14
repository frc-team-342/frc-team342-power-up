package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateToAngle extends Command {
	private DriveSystem RotateToAngle;
	private double angle;
	private boolean TurnRight;
	private static final double RotateSpeed = 0.5;
	private static final double RotateSlowSpeed=0.2;
	static final double margin = 10;
	static final double slowmargin=50;
	double gyro_angle = RotateToAngle.getGyro();
	

	private double angleinitial;

	/**
	 * @param angle
	 *            converted to int in degrees if the angle is greater than or equal
	 *            to 360 it will do a full loop and then turn to the correct angle
	 */
	public RotateToAngle(int angle) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		RotateToAngle = DriveSystem.getInstance();
		requires(RotateToAngle);
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		angleinitial = RotateToAngle.getGyro();

		if (Math.abs(angle) > 180) {

			TurnRight = false;
		} else {
			TurnRight = true;

		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double CurrentDriveSpeed;
		gyro_angle =RotateToAngle.getGyro();
		boolean slowdown = (gyro_angle) <= (angle)+slowmargin && (gyro_angle) >= (angle)-slowmargin;
		if (slowdown) {
			CurrentDriveSpeed=RotateSlowSpeed;
			

	}else {
		CurrentDriveSpeed=RotateSpeed;
	}
	
		if (TurnRight) {
			RotateToAngle.drive(CurrentDriveSpeed, CurrentDriveSpeed * -1.0, 0.0);
		} else {
			RotateToAngle.drive(CurrentDriveSpeed * -1.0, CurrentDriveSpeed, 0.0);
		}
	}
		

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	
		
		SmartDashboard.putNumber(" TARGET ANGLE: ", angle);
		SmartDashboard.putNumber("ANGLE: ", gyro_angle);
		

		
			boolean isFinished = (gyro_angle) <= (angle)+margin && (gyro_angle) >= (angle)-margin;
			if (isFinished) {
				SmartDashboard.putBoolean("isfinished: ", isFinished);
				return true;
			} else {
				return false;
			}
	}

	// Called once after isFinished returns true
	protected void end() {
		RotateToAngle.stopDrive();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
