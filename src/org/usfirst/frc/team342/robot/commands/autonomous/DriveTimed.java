package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive until a certain time has passed
 */
public class DriveTimed extends Command {

	private DriveSystem drive;
	
	private static final long TIME_DEADZONE = 500;
	private static final double SLOW_SPEED = 0.3;

	private double speed;
	private double time;
	private long starttime;
	private long endtime;

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
			drive.drive(SLOW_SPEED, SLOW_SPEED, 0.0);
		}else {
			drive.drive(speed, speed, 0.0);
		}
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
