package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSystem extends Subsystem {

	private static final LiftSystem INSTANCE = new LiftSystem();

	private TalonSRX liftMaster;
	
	private TalonSRX liftFollow;
	
	
	
	// variables for current limits
	private int amps= 10;
	private int timeout= 10;
	private int milliseconds= 200;
	// ramp
	private static final double RAMP_TIME = 0.2;
	
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

		liftMaster = new TalonSRX(RobotMap.LIFTMASTER);
		liftFollow = new TalonSRX(RobotMap.LIFTFOLLOW);
		liftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		liftFollow.follow(liftMaster);
		
		
		// current limits for liftMaster
		liftMaster.configPeakCurrentLimit(amps, timeout);
		liftMaster.configPeakCurrentDuration(milliseconds, timeout);
		liftMaster.configContinuousCurrentLimit(amps, timeout);
		liftMaster.enableCurrentLimit(true);
		
		// current limits for liftFollow
		liftFollow.configPeakCurrentLimit(amps, timeout);
		liftFollow.configPeakCurrentDuration(milliseconds, timeout);
		liftFollow.configContinuousCurrentLimit(amps, timeout);
		liftFollow.enableCurrentLimit(true);
		
		liftMaster.configOpenloopRamp(RAMP_TIME, 0);
		liftFollow.configOpenloopRamp(RAMP_TIME, 0);
	
		
	}

	public void liftUpForce(double speed) {
		liftMaster.set(ControlMode.PercentOutput, speed * -1.0);
	}

	public void liftUp(double speed) {
		liftMaster.set(ControlMode.PercentOutput, speed * -1.0);
	}

	public void liftDownForce(double speed) {
		liftMaster.set(ControlMode.PercentOutput, speed);
	}

	public void liftDown(double speed) {
		liftMaster.set(ControlMode.PercentOutput, speed);
	}


	public double[] getLiftEncoder() {
		
		double[] testvalues = new double[5];
		
		testvalues[0] = liftMaster.getSelectedSensorPosition(0);
		testvalues[1] = liftMaster.getSensorCollection().getAnalogIn();
		testvalues[2] = liftMaster.getSensorCollection().getAnalogInRaw();
		testvalues[3] = liftMaster.getSensorCollection().getPulseWidthPosition();
		testvalues[4] = liftMaster.getSensorCollection().getQuadraturePosition();
		
		return testvalues;
	}

	public void liftStop() {
		liftMaster.set(ControlMode.PercentOutput, 0.0);
	}
}
