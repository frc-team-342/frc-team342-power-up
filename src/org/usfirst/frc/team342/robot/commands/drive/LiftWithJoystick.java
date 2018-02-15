package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition;
import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition.LiftHeight;
import org.usfirst.frc.team342.robot.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftWithJoystick extends Command {

	private LiftSystem lift;
	private OI oi;

	private Joystick manipulator;

	private static final double DEADZONE = 0.2;
	private static final int AXIS = 1;
	private static final double SPEED = 1.0;
	
	private int POV;

	private LiftToPosition floorPosition;
	private LiftToPosition exchangePosition;
	private LiftToPosition switchPosition;
	private LiftToPosition scaleLow;
	private LiftToPosition scaleMiddle;
	private LiftToPosition scaleHigh;

	public LiftWithJoystick() {

		oi = OI.getInstance();
		lift = LiftSystem.getInstance();
		//requires(lift);
		manipulator = oi.getJoystickManipulator();
		
		floorPosition = new LiftToPosition(LiftHeight.floorposition);
		exchangePosition = new LiftToPosition(LiftHeight.exchangeposition);
		switchPosition= new LiftToPosition(LiftHeight.switchposition);
		scaleLow = new LiftToPosition(LiftHeight.scalelow);
		scaleMiddle = new LiftToPosition(LiftHeight.scalemiddle);
		scaleHigh = new LiftToPosition(LiftHeight.scalehigh);
	}

	protected void initialize() {

	}

	protected void execute() {
		
		POV=oi.getManipulatorPOV();
		SmartDashboard.putNumber("POV:", POV);
		
		if (manipulator.getRawAxis(AXIS) > DEADZONE) {
			
			lift.liftUp(SPEED);
		} else if (manipulator.getRawAxis(AXIS) < DEADZONE * -1.0) {
			
			lift.liftDown(SPEED);
		} else{
			
			switch(POV) {
			case 0:
				scaleHigh.start();
				break;
			case 45:
				scaleMiddle.start();
				break;
			case 90:
				scaleLow.start();
				break;
			case 135:
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
		}
		
		if(scaleHigh.isCompleted()) {
			scaleHigh.cancel();
		}

	}

	protected boolean isFinished() {

		return false;
	}

	protected void end() {

		lift.liftStop();
	}

	protected void interrupted() {

		lift.stopAll();
	}
}
