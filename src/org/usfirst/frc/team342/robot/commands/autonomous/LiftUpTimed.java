package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *	Raises the lift using a timed function
 */
public class LiftUpTimed extends Command {
	
	private LiftSystem lift;
	
	private static final double LIFT_SPEED = 0.5;
	
	private long start_time;
	private long current_time;
	private long duration;
	private long goal;
	
    public LiftUpTimed(int time) {
       
    	lift = LiftSystem.getInstance();
    	requires(lift);
    	
    	duration = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	start_time = System.currentTimeMillis();
    	
    	goal = start_time + duration;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	current_time = System.currentTimeMillis();
    	
    	lift.liftUp(LIFT_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	if(current_time >= goal) {
    		
    		return true;
    	}else {
    		
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	lift.liftStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    }
}
