package org.usfirst.frc.team342.robot.commands.lift;

import org.usfirst.frc.team342.robot.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftToPosition extends Command {
	private LiftSystem liftto;
	private double CurrentHeight;
	private double goal;
	private boolean isUp;

	// we use enum to simplify getting values for positions
	public enum LiftHeight {
		floorposition(50), exchangeposition(500),switchposition(1000),scalelow(2500),scalemiddle(3500),scalehigh(4096);
		public final int value;

		LiftHeight(int initValue) {
			this.value = initValue;
		}
	}

	public LiftToPosition(LiftHeight height) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		liftto = LiftSystem.getInstance(); // get instance gives us access to lift system
		//requires(liftto);

		goal = height.value; // goal equals the values set in the enum above
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		CurrentHeight = liftto.getLiftEncoder();

		isUp = CurrentHeight <= goal; // is up if current position is less than or equal to where it needs to be

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// sets the speed that the encoder goes if not in deadzone
		CurrentHeight = liftto.getLiftEncoder();
		if (isUp) {
			liftto.liftUp(0.15);

		} else {
			liftto.liftDown(0.15);
		}

		SmartDashboard.putBoolean("UP:", isUp);
		SmartDashboard.putNumber("enc:", CurrentHeight);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		CurrentHeight = liftto.getLiftEncoder();
		// deadzone so that robot doesn't have to keep adjusting
		// if robot is in deadzone it will stop
		boolean isInDeadzone = CurrentHeight > (goal - 1000.0) && CurrentHeight < (goal + 1000.0);
		return isInDeadzone;

	}

	// Called once after isFinished returns true
	protected void end() {
		liftto.liftStop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		liftto.stopAll();
	}
}
