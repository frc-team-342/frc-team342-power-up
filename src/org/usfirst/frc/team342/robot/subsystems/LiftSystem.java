package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSystem extends Subsystem {

	private static final LiftSystem INSTANCE = new LiftSystem();

	private TalonSRX liftMaster;
	
	private TalonSRX liftFollow;
	
	private DigitalInput lowerLimit;
	private DigitalInput upperLimit;
	
	// variables for current limits
	private int amps= 1;
	private int timeout= 10;
	private int milliseconds= 200;

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
		lowerLimit = new DigitalInput(RobotMap.LIFTLOWERLIMIT);
		upperLimit = new DigitalInput(RobotMap.LIFTUPPERLIMIT);
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
		
		liftMaster.setInverted(true);
		
	}

	public void liftUpForce(double speed) {
		liftMaster.set(ControlMode.PercentOutput, speed);
	}

	public void liftUp(double speed) {
		liftMaster.set(ControlMode.PercentOutput, speed);
	}

	public void liftDownForce(double speed) {
		liftMaster.set(ControlMode.PercentOutput, speed * -1.0);
	}

	public void liftDown(double speed) {
		liftMaster.set(ControlMode.PercentOutput, speed * -1.0);
	}

	public boolean getUpperLimit() {
		return upperLimit.get();
	}

	public boolean getLowerLimit() {
		return lowerLimit.get();
	}

	public double getLiftEncoder() {
		return liftMaster.getSensorCollection().getPulseWidthPosition();
	}

	public void liftStop() {
		liftMaster.set(ControlMode.PercentOutput, 0.0);
	}

	public void stopAll() {
		liftMaster.set(ControlMode.PercentOutput, 0.0);
	}
}
