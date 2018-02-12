package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RotateToAngle extends Command {
	private DriveSystem RotateToAngle;
	private double angle;
	private boolean TurnRight;
	private static final double RotateSpeed = 0.4;

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
		if (TurnRight) {
			RotateToAngle.drive(RotateSpeed, RotateSpeed * -1.0, 0, 0.0);
		} else {
			RotateToAngle.drive(RotateSpeed * -1.0, RotateSpeed, 0, 0.0);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double gyro_angle = RotateToAngle.getGyro();

		SmartDashboard.putNumber("ANGLE: ", gyro_angle);

		if (TurnRight) {
			if (Math.abs(gyro_angle) >= Math.abs(angle)) {
				return true;
			} else {
				return false;
			}

		} else if (Math.abs(gyro_angle) <= Math.abs(angle)) {
			return true;
		} else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		RotateToAngle.stopAll();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
