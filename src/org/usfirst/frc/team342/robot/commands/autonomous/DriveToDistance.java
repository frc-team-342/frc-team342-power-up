package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This will allow the robot to go forward a certain distance in autonomous
 * Been tested on
 */

public class DriveToDistance extends Command {

	private DriveSystem drive;

	private double goal;
	
	private double init_Left;
	private double init_Right;
	private double current_Left;
	private double current_Right; 
	private double left_rotation_count;
	private double right_rotation_count;
	
	private static final double LEFT_SPEED = 0.5;
	private static final double RIGHT_SPEED = 0.5;
	private static final double CENTER_SPEED = 0.0;
	
	public enum Distance {

		// put in numbers once we get them
		CENTER_SWITCH(8), SIDE_SWITCH(13.5), SCALE_DISTANCE(24), DRIVE_IN_DISTANCE(1), DRIVE_OFF_WALL(1), DRIVE_FORWARD_DISTANCE(13);

		public final double value;

		Distance(double initValue) {
			this.value = initValue;
		}
	}
	
//	public DriveToDistance(double distance) {
//		
//		drive = DriveSystem.getInstance(); 
//		requires(drive);
//		
//		goal = distance; 
//	}

	public DriveToDistance(Distance distance) {

		drive = DriveSystem.getInstance();
		requires(drive);

		goal = distance.value;
	}

	protected void initialize() {
		
		init_Left = Math.abs(drive.getLeftMasterEncoder());
		init_Right = Math.abs(drive.getRightMasterEncoder()); 
	}

	protected void execute() {
		
		current_Left = (Math.abs(drive.getLeftMasterEncoder())) - init_Left;
		current_Right = (Math.abs(drive.getRightMasterEncoder())) - init_Right;	
		
		left_rotation_count = current_Left / 4096;
		right_rotation_count = current_Right / 4096;
		
		drive.drive(LEFT_SPEED, RIGHT_SPEED, CENTER_SPEED);
		
	}

	protected boolean isFinished() {
		
		if(left_rotation_count >= goal || right_rotation_count >= goal) {
			return true;
			
		}else {
			return false;
		}
	}

	protected void end() {

		drive.stopDrive();
	}

	protected void interrupted() {

		end();
	}
}
