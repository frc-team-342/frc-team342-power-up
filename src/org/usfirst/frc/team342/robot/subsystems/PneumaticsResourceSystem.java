package org.usfirst.frc.team342.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticsResourceSystem extends Subsystem {
	
	private boolean compressor_state;
	
	public PneumaticsResourceSystem() {
		// TODO Auto-generated constructor stub
	}

	public PneumaticsResourceSystem(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

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
		return null;
	}
	
	public boolean getCompressorState() {
		// TODO Add Code
		return compressor_state;
	}
	
}
