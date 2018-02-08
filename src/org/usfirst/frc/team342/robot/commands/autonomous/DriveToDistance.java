package org.usfirst.frc.team342.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

/**
 * This will allow the robot to go forward a certain distance in autonomous
 */

public class DriveToDistance extends Command {

	private DriveSystem DriveToDistance;
	private double goal;
	private double currentDistance;

	private double leftAverage;
	private double rightAverage;
	private double totalAverage;
	private double distanceLeft; 

	public enum Distance {
		// put in numbers once we get them
		distance1(0), distance2(0);
		public final int value;

		Distance(int initValue) {
			this.value = initValue;
		}
	}

	public DriveToDistance(Distance distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		DriveToDistance = DriveSystem.getInstance();
		requires(DriveToDistance);

		goal = distance.value;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		currentDistance = DriveToDistance;

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		leftAverage = (DriveToDistance.getLeftFollowerEncoder() + DriveToDistance.getLeftMasterEncoder()) / 2.0;
		rightAverage = (Math.abs(DriveToDistance.getRightFollowerEncoder() + DriveToDistance.getRightMasterEncoder())
				/ 2.0);

		totalAverage = (leftAverage + rightAverage);

		if (totalAverage > goal) {
			distanceLeft = Math.abs(/* the distance left to go */ - totalAverage);
			DriveToDistance.
		} else {
			DriveToDistance.stopDrive();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
