package org.usfirst.frc.team342.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeController extends Subsystem {

	private static final CubeController INSTANCE = new CubeController();
	
	public CubeController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public static CubeController getInstance() {
		return INSTANCE;
	}
	
	private void initializeCubeController() {
		// TODO Add Code
	}
	
	public void collectCube(double speed) {
		// TODO Add Code
	}
	
	public void dispenseCube(double speed) {
		// TODO Add Code
	}
	
	public void collectorOutForce(double speed) {
		// TODO Add Code
	}
	
	public void collectorInForce(double speed) {
		// TODO Add Code
	}
	
	public void collectorStop() {
		// TODO Add Code
	}
	
	public void stopAll() {
		// TODO Add Code
	}

}
