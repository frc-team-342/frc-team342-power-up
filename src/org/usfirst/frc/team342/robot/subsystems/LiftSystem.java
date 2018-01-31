package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftSystem extends Subsystem {

	private static final LiftSystem INSTANCE = new LiftSystem();

	private TalonSRX liftMaster;
	private TalonSRX liftFollow;
	private DigitalInput lowerLimit;
	private DigitalInput upperLimit;
	private AnalogInput potentiometer;

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
		potentiometer = new AnalogInput(RobotMap.POTENTIOMETER);
		
		liftMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		liftFollow.follow(liftMaster);
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
	
	public double getLiftEncoder () {
		return liftMaster.getSelectedSensorPosition(0);
	}

	public double getPotentiometer() {

		// \/ Is this the right method to use to get an analog output? \/
		return potentiometer.getAverageVoltage();
	}

	public void liftStop() {

		liftMaster.set(ControlMode.PercentOutput, 0.0);
	}

	public void stopAll() {

		liftMaster.set(ControlMode.PercentOutput, 0.0);
		liftFollow.set(ControlMode.PercentOutput, 0.0);
	}

}
