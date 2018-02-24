package org.usfirst.frc.team342.robot.commands.lift;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition.LiftHeight;
import org.usfirst.frc.team342.robot.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftWithJoystick extends Command {

	private LiftSystem lift;
	private OI oi;

	private static final double DEADZONE = 0.2;
	private static final double ZERO = 0.0;

	private double joystickValue;

	private int POV;

	private LiftToPosition floorPosition;
	private LiftToPosition exchangePosition;
	private LiftToPosition switchPosition;
	private LiftToPosition scaleLow;
	private LiftToPosition scaleMiddle;
	private LiftToPosition scaleHigh;
	private LiftToPosition climbPosition;

	public LiftWithJoystick() {

		oi = OI.getInstance();
		lift = LiftSystem.getInstance();
		// requires(lift);

		floorPosition = new LiftToPosition(LiftHeight.floorposition);
		exchangePosition = new LiftToPosition(LiftHeight.exchangeposition);
		switchPosition = new LiftToPosition(LiftHeight.switchposition);
		scaleLow = new LiftToPosition(LiftHeight.scalelow);
		scaleMiddle = new LiftToPosition(LiftHeight.scalemiddle);
		scaleHigh = new LiftToPosition(LiftHeight.scalehigh);
		climbPosition= new LiftToPosition(LiftHeight.climbposition);
	}

	protected void initialize() {

	}

	protected void execute() {

		joystickValue = oi.getJoystickManipulatorLeftYAxis();

		POV = oi.getManipulatorPOV();
		SmartDashboard.putNumber("POV:", POV);
		
		if (joystickValue < (DEADZONE * -1.0)) {

			lift.liftUp(Math.abs(joystickValue));

		} else if (joystickValue > DEADZONE) {

			lift.liftDown(Math.abs(joystickValue));

		} else if(joystickValue < DEADZONE && joystickValue > (DEADZONE * -1.0)) {
			
			lift.liftUp(ZERO);
			
		} else if (!scaleHigh.isRunning() || !scaleMiddle.isRunning() || !scaleLow.isRunning()
				   || !floorPosition.isRunning() || !exchangePosition.isRunning() || !switchPosition.isRunning()|| !climbPosition.isRunning()) {
			
			/*
			switch (POV) {
			case 0:
				climbPosition.start();
				break;
			case 45:
				scaleHigh.start();
				break;
			case 90:
				scaleMiddle.start();
				break;
			case 135:
				scaleLow.start();
				break;
			case 180:
				floorPosition.start();
				break;
			case 225:
				exchangePosition.start();
				break;
			case 270:
				switchPosition.start();
				break;
			case 315:
				break;
			default:
				break;
			}
			*/
			
		}else {
			
			lift.liftUp(ZERO);
		}

		

	}

	protected boolean isFinished() {

		return false;
	}

	protected void end() {

		lift.liftStop();
	}

	protected void interrupted() {

		end();
	}
}
