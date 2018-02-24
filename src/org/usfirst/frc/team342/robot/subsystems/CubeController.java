package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CubeController extends Subsystem {

	private static final CubeController INSTANCE = new CubeController();

	private DoubleSolenoid pneumaticClaw;
	private Solenoid pneumaticClawHolder;
	
	private Talon intakeMaster;
	private Talon intakeFollow;
	
	private AnalogInput infraredSensor;

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
		
		pneumaticClawHolder = new Solenoid(RobotMap.PNEUMATICCLAW_RELEASE_OPEN);
		
		intakeMaster = new Talon(RobotMap.INTAKEMASTER);
		intakeFollow = new Talon(RobotMap.INTAKEFOLLOW);
		infraredSensor = new AnalogInput(RobotMap.INFRAREDSENSOR);
				
		intakeFollow.setInverted(true);
		
	}
	
	public void lowerClaw() {
		
		pneumaticClawHolder.set(true);
	}
	
	public boolean getClawHolder() {
		
		return pneumaticClawHolder.get();
	}

	public void collectCube(double speed) {
		
		speed = speed * -1.0;
		
		pneumaticClaw.set(Value.kForward);
		intakeMaster.set(speed);
		intakeFollow.set(speed);

	}

	public void closeClawAndStop() {

		pneumaticClaw.set(Value.kReverse);
		intakeMaster.set(0.0);
		intakeFollow.set(0.0);
	}

	public void dispenseCube(double speed) {
		
		pneumaticClaw.set(Value.kForward);
		intakeMaster.set(speed);
		intakeFollow.set(speed);
	}

	public boolean getPneumaticClawState() {

		if (pneumaticClaw.get().equals(Value.kForward)) {
			return true;
		} else {
			return false;
		}
	}

	public double getInfrared() {

		return infraredSensor.getAverageVoltage();
	}

	public void collectorStop() {

		intakeMaster.set(0.0);
		intakeFollow.set(0.0);
	}

}
