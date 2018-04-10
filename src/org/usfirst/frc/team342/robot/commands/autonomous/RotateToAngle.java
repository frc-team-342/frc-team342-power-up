package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

public class RotateToAngle extends Command {
	
	private DriveSystem drive;
	
	private double angle;
	private double gyro_angle;
	
	private boolean TurnRight;
	
	private static double RotateSpeed;

	private static final double margin = 10;
	
	private static final double slowestspeed=.4;
	
	/**
	 * @param angle
	 *            converted to int in degrees if the angle is greater than or equal
	 *            to 360 it will do a full loop and then turn to the correct angle
	 */
	public RotateToAngle(int angle) {
		
		drive = DriveSystem.getInstance();
		requires(drive);
		
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		 RotateSpeed = 0.6;
		drive.resetGyro();
		
		gyro_angle = drive.getGyro(false);

		if (Math.abs(angle) > 180) {

			TurnRight = false;
		} else {
			TurnRight = true;

		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		gyro_angle = drive.getGyro(false);
		
		double CurrentDriveSpeed;
		
		
		if (angle!=0) {
			
	//Checks to see if rotate speed is greater than lowest allowable speed
		if (RotateSpeed > slowestspeed) {
			if(angle>=0 && angle<=180){
				RotateSpeed=(angle-gyro_angle)/angle;  //Causes rotate speed to get closer to zero as the angle gets closer to the target
			}
				
			} else {
				RotateSpeed=(gyro_angle - angle)/(360-angle);
			}
				
		}else {
			RotateSpeed=slowestspeed;
		}
	

			CurrentDriveSpeed=RotateSpeed;
		
	
		if (TurnRight) {
			drive.drive(CurrentDriveSpeed, CurrentDriveSpeed * -1.0, 0.0);
		} else {
			drive.drive(CurrentDriveSpeed * -1.0, CurrentDriveSpeed, 0.0);
		}
	}
		

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	
		boolean isFinished = (gyro_angle) <= (angle) + margin && (gyro_angle) >= (angle) - margin;
		
		if (isFinished) {
			return true;
		} else {
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
