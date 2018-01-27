package org.usfirst.frc.team342.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimbSystem extends Subsystem {

	private static final ClimbSystem INSTANCE = new ClimbSystem();

	public ClimbSystem() {
		initializeClimbSystem();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public static ClimbSystem getInstance() {
		return INSTANCE;
	}

	private void initializeClimbSystem() {
		// TODO Add Code
	}

	public void climbUpForce(double speed) {
		// TODO Add Code
	}

	public void climbUp(double speed) {
		// TODO Add Code
	}

	public void climbDownForce(double speed) {
		// TODO Add Code
	}

	public void climbDown(double speed) {
		// TODO Add Code
	}

	public void climbStop() {
		// TODO Add Code
	}

	public void stopAll() {
		// TODO Add Code
	}

}
