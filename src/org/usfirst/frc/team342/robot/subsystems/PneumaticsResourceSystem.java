package org.usfirst.frc.team342.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticsResourceSystem extends Subsystem {

	private static final PneumaticsResourceSystem INSTANCE = new PneumaticsResourceSystem();

	private boolean compressor_state;

	public PneumaticsResourceSystem() {
		initializePneumaticsResourceSystem();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	public static PneumaticsResourceSystem getInstance() {
		return INSTANCE;
	}

	private void initializePneumaticsResourceSystem() {
		// TODO Add Code
		compressor_state = false;
	}

	public void startCompressor() {
		// TODO Add Code
		compressor_state = true;
	}

	public void stopCompressor() {
		// TODO Add Code
		compressor_state = false;
	}

	public double getPressure() {
		// TODO Add Code
		return 0.0;
	}

	public boolean getCompressorState() {
		// TODO Add Code
		return compressor_state;
	}

}
