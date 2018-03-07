package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drive until a certain time has passed
 */
public class DriveTimed extends Command {

	private DriveSystem drive;
	
	private static final long TIME_DEADZONE = 500;
	private static final double SLOW_SPEED = 0.3;

	private long starttime;
	private long endtime;
	
	private double speed;
	private double time;
	private double gyro_angle;
	private double angle;
	private double total_correction;
	private double left_speed;
	private double right_speed;
	
	private static final double ZERO = 0.0;
	
	private static final double KP = 0.6;

	public DriveTimed(double time, double speed) {

		drive = DriveSystem.getInstance();
		requires(drive);

		this.time = time * 1000.0;
		this.speed = speed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
		starttime = System.currentTimeMillis();
		endtime = starttime + (int)time;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		if(System.currentTimeMillis() > (endtime - TIME_DEADZONE)){
			
			if(gyro_angle > 180.0) {
	    		
	    		angle = 360.0 - gyro_angle;
	        	total_correction = angle * KP;
	        	
	        	left_speed = SLOW_SPEED;
	        	right_speed = SLOW_SPEED - total_correction;
	    	}else {
	    		
	    		angle = gyro_angle;
	        	total_correction = angle * KP;
	        	
	        	left_speed = SLOW_SPEED - total_correction;
	        	right_speed = SLOW_SPEED;
	    	}
	    	
	    	drive.drive(left_speed, right_speed, ZERO);
	    	
		}else {
			
			if(gyro_angle > 180.0) {
	    		
	    		angle = 360.0 - gyro_angle;
	        	total_correction = angle * KP;
	        	
	        	left_speed = speed;
	        	right_speed = speed - total_correction;
	    	}else {
	    		
	    		angle = gyro_angle;
	        	total_correction = angle * KP;
	        	
	        	left_speed = speed - total_correction;
	        	right_speed = speed;
	    	}
	    	
	    	drive.drive(left_speed, right_speed, ZERO);
	    	
		}
		
		SmartDashboard.putNumber("angle: ", angle);
		SmartDashboard.putNumber("left_speed: ", left_speed);
		SmartDashboard.putNumber("right_speed: ", right_speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		
		if(System.currentTimeMillis() >= endtime) {
			return true;
		}else {
			return false;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		
		drive.stopDrive();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		
		end();
	}
}
