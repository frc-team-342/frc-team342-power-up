package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ManipulateWheelTOGGLE extends Command {
	
	private DriveSystem drive;
	private boolean isUp;

    public ManipulateWheelTOGGLE() {
    	
        drive = DriveSystem.getInstance();
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	isUp = drive.getWheelState();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	if(isUp) {
    		drive.wheelDown();
    	}else {
    		drive.wheelUp();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	drive.stopAll();
    }
}
