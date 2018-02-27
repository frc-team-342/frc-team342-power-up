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
	
	private double gyro_angle;
	private double angle;
	private double total_correction;
	private double left_speed;
	private double right_speed;
	
	private static final double SPEED = 0.25;
	private static final double ZERO = 0.0;
	
	private static final double KP = 0.6;
	
	
	public enum Distance {

		// put in numbers once we get them
		CENTER_SWITCH(10), SIDE_SWITCH(15), SCALE_DISTANCE(35), DRIVE_IN_DISTANCE(0.5), DRIVE_OFF_WALL(1), DRIVE_FORWARD_DISTANCE(13);

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
		
		gyro_angle = drive.getGyro();
    	
    	if(gyro_angle > 180.0) {
    		
    		angle = 360.0 - gyro_angle;
        	total_correction = angle * KP;
        	
        	left_speed = SPEED;
        	right_speed = SPEED - total_correction;
    	}else {
    		
    		angle = gyro_angle;
        	total_correction = angle * KP;
        	
        	left_speed = SPEED - total_correction;
        	right_speed = SPEED;
    	}
    	
    	drive.drive(left_speed, right_speed, ZERO);
		
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
