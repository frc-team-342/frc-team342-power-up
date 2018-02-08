package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class LiftWithJoystick extends Command {

	private LiftSystem LiftWithJoystick;
	private OI oi;

	private Joystick manipulator;

	private static final double DEADZONE = 0.2;
	private static final int AXIS = 1;
	private static final double SPEED = 1.0;

	public LiftWithJoystick() {

		oi = OI.getInstance();
		LiftWithJoystick = LiftSystem.getInstance();
		requires(LiftWithJoystick);
		manipulator = oi.getJoystickManipulator();
	}

	protected void initialize() {

	}

	protected void execute() {

		if (manipulator.getRawAxis(AXIS) > DEADZONE) {
			LiftWithJoystick.liftUp(SPEED);
		} else if (manipulator.getRawAxis(AXIS) < DEADZONE * -1.0) {
			LiftWithJoystick.liftDown(SPEED);
		}
	}

	protected boolean isFinished() {

		return false;
	}

	protected void end() {

		LiftWithJoystick.liftStop();
	}

	protected void interrupted() {

		LiftWithJoystick.stopAll();
	}
}
