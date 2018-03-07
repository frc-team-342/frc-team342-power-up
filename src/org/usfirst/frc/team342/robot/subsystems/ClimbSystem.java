package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ClimbSystem extends Subsystem {

	private static final ClimbSystem INSTANCE = new ClimbSystem();

	private TalonSRX climbmaster;
	private TalonSRX climbfollow;
	
	private DoubleSolenoid climbextend;
	
	// variables for current limits
		private static final int AMPS= 50;
		private static final int TIMEOUT= 10;
		private static final int MILLISECONDS= 200;
		
	private static final double ZERO = 0.0;
	
	public ClimbSystem() {

		initializeClimbSystem();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public static ClimbSystem getInstance() {

		return INSTANCE;
	}

	private void initializeClimbSystem() {
		
		climbmaster = new TalonSRX(RobotMap.CLIMBMASTER);
		climbfollow = new TalonSRX(RobotMap.CLIMBFOLLOW);
		
		climbextend = new DoubleSolenoid(RobotMap.PNEUMATICCLIMB_EXTEND, RobotMap.PNEUMATICCLIMB_RETRACT);
		
		// current limits for liftMaster
			climbmaster.configPeakCurrentLimit(AMPS, TIMEOUT);
			climbmaster.configPeakCurrentDuration(MILLISECONDS, TIMEOUT);
			climbmaster.configContinuousCurrentLimit(AMPS, TIMEOUT);
			climbmaster.enableCurrentLimit(true);
				
		// current limits for liftFollow
			climbfollow.configPeakCurrentLimit(AMPS, TIMEOUT);
			climbfollow.configPeakCurrentDuration(MILLISECONDS, TIMEOUT);
			climbfollow.configContinuousCurrentLimit(AMPS, TIMEOUT);
			climbfollow.enableCurrentLimit(true);
		
		climbfollow.follow(climbmaster);
	}

	public void climbUp(double speed) {

		climbmaster.set(ControlMode.PercentOutput, speed);
		
	}
	public void ExtendArm() {
	climbextend.set(Value.kReverse);	
	}
	
	public void RetractArm() {
		climbextend.set(Value.kForward);	
		}
	
	public void climbDown(double speed) {
		
		speed = speed * -1.0;
		climbmaster.set(ControlMode.PercentOutput, speed);
		
	}
	
	public boolean getClimbExtender() {
		
		if(climbextend.get().equals(Value.kForward)) {
			return true;
		}else{
			return false;
		}
	}

	public void climbStop() {
		climbmaster.set(ControlMode.PercentOutput, ZERO);
	}

}
