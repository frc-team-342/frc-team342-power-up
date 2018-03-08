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
	private static final double SPEED_CONST = 1500;
	
	public enum Distance {

		// put in numbers once we get them
		CENTER_SWITCH(12), SIDE_SWITCH(15), SCALE_DISTANCE(35), DRIVE_IN_DISTANCE_SCALE(0.5), DRIVE_IN_DISTANCE_SWITCH(3), DRIVE_OFF_WALL(1), DRIVE_FORWARD_DISTANCE(13);
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

	public DriveToDistance(Distance distance) {

		drive = DriveSystem.getInstance();
		requires(drive);

		goal = distance.value;
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
		
		left_rotation_count = current_Left / 4096;
		right_rotation_count = current_Right / 4096;
		
		//drive.drive(LEFT_SPEED, RIGHT_SPEED, CENTER_SPEED);
		
		if(drive.getGyro() > 180 && drive.getGyro() < 359) {
			
			degrees_off_zero = (360 - drive.getGyro());
			
			if(degrees_off_zero > 15) {
				degrees_off_zero = 15.0;
			}
			
			left_speed = left_speed - (degrees_off_zero * kP);
			right_speed = right_speed + (degrees_off_zero * kP);
			
		}else if(drive.getGyro() < 180 && drive.getGyro() > 1) {
			
			degrees_off_zero = drive.getGyro();
			
			if(degrees_off_zero > 15) {
				degrees_off_zero = 15.0;
			}
			
			left_speed = left_speed + (degrees_off_zero * kP);
			right_speed = right_speed - (degrees_off_zero * kP);
			
		}
		
		drive.driveSetSpeed(left_speed, right_speed);
		
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
