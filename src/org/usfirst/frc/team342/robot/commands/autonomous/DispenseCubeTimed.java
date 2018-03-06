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
	
	private static final double SPEED = 1.0;
	
	private CubeController cube_controller;
	
	/**
	 * @param time Time in seconds.
	 */
    public DispenseCubeTimed(int time) {
        
    	cube_controller = CubeController.getInstance();
    	duration = time * 1000;
    }

    protected void initialize() {
    	
    	start_time = System.currentTimeMillis();
    	goal = start_time + duration;
    }

    protected void execute() {
    	
    	current_time = System.currentTimeMillis();
    	
    	cube_controller.dispenseCube(SPEED);
    }

    protected boolean isFinished() {
    	
    	//if the time is less than goal then keep going 
    	if(current_time >= goal) {
    		return true;
    	} else {
    		return false;
    	}
    }

    protected void end() {
    
    	cube_controller.closeClawAndStop();
    }

    protected void interrupted() {
    	
    	end();
    }
}
