package org.usfirst.frc.team342.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticsResourceSystem extends Subsystem {

	private static final PneumaticsResourceSystem INSTANCE = new PneumaticsResourceSystem();

	private Compressor compressor;

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

		compressor = new Compressor();
	}

	public void startCompressor() {

		compressor.start();
	}

	public void stopCompressor() {

		compressor.stop();
	}

	public double getPressure() {

		// \/ How Do We Do this? \/
		return 0.0;
	}

	public boolean getCompressorState() {

		return compressor.enabled();
	}

}
