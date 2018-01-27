package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeController extends Subsystem {

	private static final CubeController INSTANCE = new CubeController();

	private Solenoid pneumaticClaw;
	private TalonSRX intakeMaster;
	private TalonSRX intakeFollow;
	private AnalogInput infraredSensor;

	private static final boolean OPEN = true;
	private static final boolean CLOSED = false;

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

		pneumaticClaw = new Solenoid(RobotMap.PNEUMATICCLAW);
		intakeMaster = new WPI_TalonSRX(RobotMap.INTAKEMASTER);
		intakeFollow = new WPI_TalonSRX(RobotMap.INTAKEFOLLOW);
		infraredSensor = new AnalogInput(RobotMap.INFRAREDSENSOR);

		intakeFollow.follow(intakeMaster);
	}

	public void collectCube(double speed) {

		pneumaticClaw.set(OPEN);
		intakeMaster.set(ControlMode.PercentOutput, speed);

	}

	public void dispenseCube(double speed) {

		pneumaticClaw.set(OPEN);
		intakeMaster.set(ControlMode.PercentOutput, speed);
	}

	public void collectorOutForce(double speed) {

		pneumaticClaw.set(OPEN);
		intakeMaster.set(ControlMode.PercentOutput, speed);
	}

	public void collectorInForce(double speed) {

		pneumaticClaw.set(CLOSED);
		intakeMaster.set(ControlMode.PercentOutput, speed);
	}

	public boolean getPneumaticClawState() {

		return pneumaticClaw.get();
	}

	public double getInfrared() {

		// \/ Is this the right method to use to get an analog output? \/
		return infraredSensor.getAverageVoltage();
	}

	public void collectorStop() {
		intakeMaster.set(ControlMode.PercentOutput, 0.0);
		pneumaticClaw.set(CLOSED);
	}

	public void stopAll() {
		intakeMaster.set(ControlMode.PercentOutput, 0.0);
		intakeMaster.set(ControlMode.PercentOutput, 0.0);
		pneumaticClaw.set(getPneumaticClawState());
	}

}
