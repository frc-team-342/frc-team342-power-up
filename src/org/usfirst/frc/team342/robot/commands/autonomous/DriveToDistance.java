package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This will allow the robot to go forward a certain distance in autonomous
 * Been tested on
 */

public class DriveToDistance extends Command {

	private DriveSystem drive;

	private double goal;
	
	private boolean backwards;
	
	private double init_Left;
	private double init_Right;
	private double current_Left;
	private double current_Right; 
	private double left_rotation_count;
	private double right_rotation_count;
	
	private double degrees_off_zero;
	private double left_speed;
	private double right_speed;
	
	private static final double kP = -50;
	private static final double SPEED_CONST = 1600;
	
	public enum Distance {

		// put in numbers once we get them
		CENTER_SWITCH_LEFT(10 * 0.95), CENTER_SWITCH_RIGHT(10.2 * 0.95), SCALE_ACROSS_PLATFORM_ZONE(19.3 * 0.95), PLATFORM_ZONE_WALL(25.05 * 0.95), SCALE_FROM_PLATFORM_ZONE(0 * 0.95), SIDE_SWITCH(17.316 * 0.95), SCALE_DISTANCE(37.179 * 0.95), DRIVE_IN_DISTANCE_SCALE(0.5 * 0.95),
		DRIVE_IN_DISTANCE_SWITCH(3 * 0.95), DRIVE_OFF_WALL(1 * 0.95), DRIVE_FORWARD_DISTANCE(13 * 0.95), DRIVE_OFF_SWITCH_WALL(2 * 0.95), DRIVE_TO_PLATFORM_ZONE_FROM_SWITCH(3.66 * 0.95),
		DRIVE_TO_CUBE_SWITCH(2.87 * 0.95), DRIVE_TO_CUBES(5 * 0.95);

		//SIDE_SWITCH(13.5)
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

	public DriveToDistance(Distance distance, boolean backwards) {

		drive = DriveSystem.getInstance();
		requires(drive);

		goal = distance.value;
		this.backwards = backwards;
	}

	protected void initialize() {
		
		init_Left = drive.getLeftMasterEncoder();
		init_Right = drive.getRightMasterEncoder(); 
		
		drive.resetGyro();
	}

	protected void execute() {

		left_speed = SPEED_CONST;
		right_speed = SPEED_CONST;
		
		current_Left = drive.getLeftMasterEncoder() - init_Left;
		current_Right = drive.getRightMasterEncoder() - init_Right;	
		
		left_rotation_count = Math.abs(current_Left / 4096);
		right_rotation_count = Math.abs(current_Right / 4096);
		
		//drive.drive(LEFT_SPEED, RIGHT_SPEED, CENTER_SPEED);
		
		if(drive.getGyro(backwards) > 180 && drive.getGyro(backwards) < 359) {
			
			degrees_off_zero = (360 - drive.getGyro(backwards));
			
			if(degrees_off_zero > 15) {
				degrees_off_zero = 15.0;
			}
			
			if(backwards) {
				
				left_speed = left_speed - (degrees_off_zero * kP);
				right_speed = right_speed + (degrees_off_zero * kP);
			}else {
				
				left_speed = left_speed - (degrees_off_zero * kP);
				right_speed = right_speed + (degrees_off_zero * kP);
			}
			
			
		}else if(drive.getGyro(backwards) < 180 && drive.getGyro(backwards) > 1) {
			
			degrees_off_zero = drive.getGyro(backwards);
			
			if(degrees_off_zero > 15) {
				degrees_off_zero = 15.0;
			}
			
			if(backwards) {
				
				left_speed = left_speed + (degrees_off_zero * kP);
				right_speed = right_speed - (degrees_off_zero * kP);
			}else {
				
				left_speed = left_speed + (degrees_off_zero * kP);
				right_speed = right_speed - (degrees_off_zero * kP);
			}
			
		}
		
		if(backwards) {
			drive.driveSetSpeed(left_speed * -1.0, right_speed * -1.0);
		}else {
			drive.driveSetSpeed(left_speed, right_speed);
		}
		
		SmartDashboard.putNumber("left", left_rotation_count);
		SmartDashboard.putNumber("right", right_rotation_count);
		
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
