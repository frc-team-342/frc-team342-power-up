package org.usfirst.frc.team342.robot.commands.claw;

import org.usfirst.frc.team342.robot.subsystems.CubeController;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Lowers the claw from the solenoid that holds it up.
 */
public class LowerClaw extends Command {
	
	private CubeController cube_controller;
	
    public LowerClaw() {
        
    	cube_controller = CubeController.getInstance();
    	requires(cube_controller);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	cube_controller.lowerClaw();
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
    	
    }
}
