package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifier.LEDChannel;
import com.ctre.phoenix.CANifierFaults;
import com.ctre.phoenix.CANifierJNI;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LightsSubsystem extends Subsystem {

	private static final LightsSubsystem INSTANCE = new LightsSubsystem();
	
	private CANifier rgb_controller;
	
	private DriverStation ds;
	
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
		
		rgb_controller = new CANifier(RobotMap.CANIFIER);
		ds = DriverStation.getInstance();
	}
	
	public void setDriverStationColor() {
		
		if(ds.getAlliance().equals(Alliance.Red)) {
			
			setRed(1.0);
		}else if(ds.getAlliance().equals(Alliance.Blue)){
			
			setBlue(1.0);
		}
		
	}
	
	public void setRed(double percent_output) {
		
		rgb_controller.setLEDOutput(percent_output, LEDChannel.LEDChannelA);
	}
	
	public void setGreen(double percent_output) {

		rgb_controller.setLEDOutput(percent_output, LEDChannel.LEDChannelB);
	}
	
	public void setBlue(double percent_output) {

		rgb_controller.setLEDOutput(percent_output, LEDChannel.LEDChannelC);
	}
	
	public void setRGB(double percent_red, double percent_green, double percent_blue) {
		
		rgb_controller.setLEDOutput(percent_red, LEDChannel.LEDChannelA);
		rgb_controller.setLEDOutput(percent_green, LEDChannel.LEDChannelB);
		rgb_controller.setLEDOutput(percent_blue, LEDChannel.LEDChannelC);
	}
}
