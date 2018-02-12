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
	//TODO assign a value to current distance or find some other way to make it work

	private double totalAverage;
	private double distanceLeft;
	//TODO also assign this a value 
	//NOTE: it is distance left, as in distance left to drive

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
		totalAverage = (DriveToDistance.getLeftMasterEncoder() + DriveToDistance.getRightMasterEncoder()) / 2;
		currentDistance = totalAverage;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		if (totalAverage > goal) {
			distanceLeft = Math.abs(/* the distance left to go */ - totalAverage);
			DriveToDistance.drive(0.5, 0.5, 0.0, 0.2); 
		} else {
			DriveToDistance.stopDrive();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		boolean isInDeadzone = currentDistance > (goal - 1000.0) && currentDistance < (goal + 1000.0); 
		return isInDeadzone;
	}

	// Called once after isFinished returns true
	protected void end() {
		DriveToDistance.stopDrive();  
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		DriveToDistance.stopAll();
	}
}
