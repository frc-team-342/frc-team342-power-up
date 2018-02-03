package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftToPosition extends Command {
	private LiftSystem LiftToPosition;
	private double CurrentHeight;
	private double goal;
	private boolean up;
	
	public enum LiftHeight{
		fourthousand,
		onethousand
	}
	
	public LiftToPosition(LiftHeight height) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		LiftToPosition = LiftSystem.getInstance();
		requires(LiftToPosition);
		
		switch (height) {
		case fourthousand:
			goal = 4000;
			break;
		case onethousand:
			goal = 1000;
			break;
		default:
		}
	}
	
	

	// Called just before this Command runs the first time
	protected void initialize() {
		CurrentHeight = LiftToPosition.getLiftEncoder();
		if (CurrentHeight > goal) {
			up = false;
		} else if (CurrentHeight <= goal) {
			up = true;
		}

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
		CurrentHeight = LiftToPosition.getLiftEncoder();
			if (up == true) {
				LiftToPosition.liftUp(0.15);

			} else {
				LiftToPosition.liftDown(0.15);
			}
		
		
		
		SmartDashboard.putBoolean("UP:"	,up);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		CurrentHeight = LiftToPosition.getLiftEncoder();
		if (CurrentHeight > (goal - 1000.0) && CurrentHeight < (goal + 1000.0)) {   //deadzone so that robot doesn't have to keep adjusting
			return true;
		} else {
			return false;
		}

	}

	// Called once after isFinished returns true
	protected void end() {
		LiftToPosition.liftStop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		LiftToPosition.stopAll();
	}
}
