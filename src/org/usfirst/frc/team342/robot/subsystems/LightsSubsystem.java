package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.hal.AllianceStationID;
import edu.wpi.first.wpilibj.hal.HAL;

public class LightsSubsystem extends Subsystem {

	private static final LightsSubsystem INSTANCE = new LightsSubsystem();
	
	//private DriverStation ds;
	
	//private PWM red;
	//private PWM green;
	//private PWM blue;
	//private PWM twelveVoltsOne;
	//private PWM twelveVoltsTwo;
	//private PWM fiveVolts;
	
	public LightsSubsystem() {
		initializeLightsSubsystem();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

	public static LightsSubsystem getInstance() {

		return INSTANCE;
	}
	
	private void initializeLightsSubsystem() {
		//red = new PWM(RobotMap.RED);
		//green = new PWM(RobotMap.GREEN);
		//blue = new PWM(RobotMap.BLUE);
		//twelveVoltsOne = new PWM(RobotMap.TWELVEVOLTSONE);
		//twelveVoltsTwo = new PWM(RobotMap.TWELVEVOLTSTWO);
		//fiveVolts = new PWM(RobotMap.FIVEVOLTS);
		
		//ds = DriverStation.getInstance();
	}
	
	public void setDriverStationColor() {
		//if(ds.getAlliance().equals(Alliance.Red)){
		//	setRed(255);
		//	setGreen(0);
		//	setBlue(0);
		//}else if(ds.getAlliance().equals(Alliance.Blue)) {
		//	setBlue(255);
		//	setGreen(0);
		//	setRed(0);
		//}
	}
	
	public void setRed(int value) {
		//red.setRaw(value);
	}
	
	public void setGreen(int value) {
		//green.setRaw(value);
	}
	
	public void setBlue(int value) {
		//blue.setRaw(value);
	}
	
	public void setTwelveVoltsOne(int value) {
		//twelveVoltsOne.setRaw(value);
	}
	
	public void setTwelveVoltsTwo(int value) {
		//twelveVoltsTwo.setRaw(value);
	}
	
	public void setFiveVolts(int value) {
		//fiveVolts.setRaw(value);
	}
}
