package org.usfirst.frc.team342.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraVisionSystem extends Subsystem {
	
	private static final CameraVisionSystem INSTANCE = new CameraVisionSystem();
	
	public CameraVisionSystem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public static CameraVisionSystem getInstance() {
		return INSTANCE;
	}

}
