package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeController extends Subsystem {

	private static final CubeController INSTANCE = new CubeController();

	private DoubleSolenoid pneumaticClaw;
	
	private TalonSRX intakeMaster;
	private TalonSRX intakeFollow;
	
	private AnalogInput infraredSensor;
	
	private static final int AMPS = 35;
	private static final int TIMEOUT_MS = 10;
	private static final int PEAK_DURATION = 200;

	public CubeController() {

		initializeCubeController();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public static CubeController getInstance() {

		return INSTANCE;
	}

	private void initializeCubeController() {

		pneumaticClaw = new DoubleSolenoid(RobotMap.PNEUMATICCLAW_OPEN, RobotMap.PNEUMATICCLAW_CLOSED);
		intakeMaster = new TalonSRX(RobotMap.INTAKEMASTER);
		intakeFollow = new TalonSRX(RobotMap.INTAKEFOLLOW);
		infraredSensor = new AnalogInput(RobotMap.INFRAREDSENSOR);

		intakeFollow.follow(intakeMaster);
		intakeFollow.setInverted(true);
		
		//Set up current limiting for the two motor controllers
		intakeMaster.configPeakCurrentLimit(AMPS, TIMEOUT_MS);
		intakeMaster.configPeakCurrentDuration(PEAK_DURATION, TIMEOUT_MS);
		intakeMaster.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		intakeMaster.enableCurrentLimit(true);
		
		intakeFollow.configPeakCurrentLimit(AMPS, TIMEOUT_MS);
		intakeFollow.configPeakCurrentDuration(PEAK_DURATION, TIMEOUT_MS);
		intakeFollow.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		intakeFollow.enableCurrentLimit(true);
	}

	public void collectCube(double speed) {

		pneumaticClaw.set(Value.kForward);
		intakeMaster.set(ControlMode.PercentOutput, speed);

	}

	public void closeClawAndStop() {

		pneumaticClaw.set(Value.kReverse);
		intakeMaster.set(ControlMode.PercentOutput, 0.0);
	}

	public void dispenseCube(double speed) {

		pneumaticClaw.set(Value.kForward);
		intakeMaster.set(ControlMode.PercentOutput, speed * -1.0);
	}

	// UNUSED \/
	public void collectorOutForce(double speed) {

		pneumaticClaw.set(Value.kForward);
		intakeMaster.set(ControlMode.PercentOutput, speed);
	}

	// UNUSED \/
	public void collectorInForce(double speed) {

		pneumaticClaw.set(Value.kForward);
		intakeMaster.set(ControlMode.PercentOutput, speed);
	}

	public boolean getPneumaticClawState() {

		if (pneumaticClaw.get().equals(Value.kForward)) {
			return true;
		} else {
			return false;
		}
	}

	public double getInfrared() {

		// \/ Is this the right method to use to get an analog output? \/
		return infraredSensor.getAverageVoltage();
	}

	public void collectorStop() {

		intakeMaster.set(ControlMode.PercentOutput, 0.0);
	}

	public void stopAll() {

		intakeMaster.set(ControlMode.PercentOutput, 0.0);
		intakeMaster.set(ControlMode.PercentOutput, 0.0);
	}

}
