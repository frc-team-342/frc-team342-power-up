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
	
	private static final double LEFTSPEED = 0.5;
	private static final double RIGHTSPEED = 0.5;
	private static final double CENTERSPEED = 0.0;
	private static final double DEADZONE = 0.0;
	
	//TODO also assign this a value 
	//NOTE: it is distance left, as in distance left to drive

	public enum Distance {
		// put in numbers once we get them
		distance1(50), distance2(50);
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
		totalAverage = Math.abs(DriveToDistance.getLeftMasterEncoder()) + Math.abs(DriveToDistance.getRightMasterEncoder()) / 2.0;
		currentDistance = DriveToDistance.getDistance(totalAverage); 

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		if (currentDistance < goal) {
			//distanceLeft = Math.abs(goal - totalAverage);
			DriveToDistance.drive(LEFTSPEED, RIGHTSPEED, CENTERSPEED, DEADZONE); 
			
			currentDistance = (DriveToDistance.getLeftMasterEncoder() + DriveToDistance.getRightMasterEncoder()) / 2.0;
			
			
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
