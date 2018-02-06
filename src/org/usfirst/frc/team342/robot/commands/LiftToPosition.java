package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftToPosition extends Command {
	private LiftSystem LiftToPosition;
	private double CurrentHeight;
	private double goal;
	private boolean isUp;

	// we use enum to simplify getting values for positions
	public enum LiftHeight {
		fourthousand(4000), onethousand(1000);
		public final int value;

		LiftHeight(int initValue) {
			this.value = initValue;
		}
	}

	public LiftToPosition(LiftHeight height) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		LiftToPosition = LiftSystem.getInstance(); // get instance gives us access to lift system
		requires(LiftToPosition);

		goal = height.value; // goal equals the values set in the enum above
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		CurrentHeight = LiftToPosition.getLiftEncoder();

		isUp = CurrentHeight <= goal; // is up if current position is less than or equal to where it needs to be

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// sets the speed that the encoder goes if not in deadzone
		CurrentHeight = LiftToPosition.getLiftEncoder();
		if (isUp) {
			LiftToPosition.liftUp(0.15);

		} else {
			LiftToPosition.liftDown(0.15);
		}

		SmartDashboard.putBoolean("UP:", isUp);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		CurrentHeight = LiftToPosition.getLiftEncoder();
		// deadzone so that robot doesn't have to keep adjusting
		// if robot is in deadzone it will stop
		boolean isInDeadzone = CurrentHeight > (goal - 1000.0) && CurrentHeight < (goal + 1000.0);
		return isInDeadzone;

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
