package org.usfirst.frc.team342.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LightsSubsystem extends Subsystem {

	private static final LightsSubsystem INSTANCE = new LightsSubsystem();

	public LightsSubsystem() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public static LightsSubsystem getInstance() {
		return INSTANCE;
	}

}
