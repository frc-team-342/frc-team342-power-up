package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *going to put the 5th solenoid down if it was true
 */
public class ManipulateWheelDOWN extends Command {
	
	private DriveSystem drive; 

    public ManipulateWheelDOWN() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	drive = DriveSystem.getInstance(); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	drive.wheelDown();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	end(); 	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
