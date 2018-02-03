package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class liftUp extends Command {
	
	private LiftSystem liftUp;

    public liftUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	   
    	liftUp=LiftSystem.getInstance();
    	requires (liftUp);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed;
    	speed= -1.0;
    	  liftUp.liftUp(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return liftUp.getUpperLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	liftUp.liftStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	liftUp.stopAll();
    }
}