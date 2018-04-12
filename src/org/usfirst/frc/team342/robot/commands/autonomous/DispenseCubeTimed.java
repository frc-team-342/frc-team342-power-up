package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.subsystems.CubeController;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turns on the cube dispenser for a specified amount of time.
 */
public class DispenseCubeTimed extends Command {
	
	private long start_time;
	private long current_time;
	private long duration;
	private long goal;
	
	private static final double SPEED = 0.7;
	
	private CubeController cube_controller;
	
	/**
	 * 
	 * @param time Time in seconds.
	 */
    public DispenseCubeTimed(int time) {
        
    	cube_controller = CubeController.getInstance();
    	duration = time * 1000;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	start_time = System.currentTimeMillis();
    	goal = start_time + duration;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	current_time = System.currentTimeMillis();
    	
    	cube_controller.dispenseCube(SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if(current_time >= goal) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    
    	cube_controller.closeClawAndStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    }
}
