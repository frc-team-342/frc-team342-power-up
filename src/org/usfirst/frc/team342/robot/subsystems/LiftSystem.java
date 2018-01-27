package org.usfirst.frc.team342.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSystem extends Subsystem {

	private static final LiftSystem INSTANCE = new LiftSystem();

	public LiftSystem() {
		initializeLiftSystem();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public static LiftSystem getInstance() {
		return INSTANCE;
	}

	private void initializeLiftSystem() {
		// TODO Add Code
	}

	public void liftUpForce(double speed) {
		// TODO Add Code
	}

	public void liftUp(double speed) {
		// TODO Add Code
	}

	public void liftDownForce(double speed) {
		// TODO Add Code
	}

	public void liftDown(double speed) {
		// TODO Add Code
	}

	public double getPotentiometer() {
		return 0.0;
	}

	public void liftStop() {
		// TODO Add Code
	}

	public void stopAll() {
		// TODO Add Code
	}

}
