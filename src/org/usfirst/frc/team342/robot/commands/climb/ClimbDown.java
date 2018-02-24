package org.usfirst.frc.team342.robot.commands.climb;

import org.usfirst.frc.team342.robot.subsystems.ClimbSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Makes the robot climb down.
 */
public class ClimbDown extends Command {

	private ClimbSystem climb;
	
	private static final double SPEED = 1.0;
	
    public ClimbDown() {
    	
    	climb = ClimbSystem.getInstance();
    	requires(climb);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	climb.climbUp(SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	climb.climbStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    }
}
