package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class liftDown extends Command {
	
private LiftSystem liftDown;


    public liftDown() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
   
    	liftDown=LiftSystem.getInstance();
    	requires (liftDown);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed;
    	speed= -1.0;
    	liftDown.liftDown(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return liftDown.getLowerLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	liftDown.liftStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	liftDown.stopAll();
    }
}