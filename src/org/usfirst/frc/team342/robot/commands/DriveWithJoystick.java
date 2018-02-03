package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {

	private static final int LEFT_STICK = Joystick.AxisType.kY.value; 
	private static final int RIGHT_STICK = Joystick.AxisType.kY.value;
	
	private double speed_y_left; 
	private double speed_x_left;
	private double speed_y_right;
	private double speed_x_right;
		
	private Joystick Joypad_Left; 
	private Joystick Joypad_Right;
	private OI oi; 
	private DriveSystem drive; 
	
	public DriveWithJoystick() {
		oi = OI.getInstance(); 
		drive = DriveSystem.getInstance();
		Joypad_Left = oi.getJoypadLeftDrive();
		Joypad_Right = oi.getJoypadRightDrive();
		//tells robot to get what its trying to do 
		//tells drive system to drive with the previous instance 
			
		//joypad = oi.joyleft;
		//joypad = oi.joyright;
		//honestly, no idea what those two things do but they work if joypad doesn't
	
	
		
	}
		
	
	@Override
	protected boolean isFinished() {
		//when the joystick is finished 
		return false;
	}

	public void intialize() {
		//when it starts
	}
	
	public void execute() { 
		
		speed_y_left = Joypad_Left.getRawAxis(1);
		speed_x_left = Joypad_Left.getRawAxis(1);
		
		speed_y_right = Joypad_Left.getRawAxis(5);
		speed_x_right = Joypad_Left.getRawAxis(5);
		
		
		//set to random axis... made them up

		double right = Joypad_Left.getRawAxis(LEFT_STICK); 
		double left = Joypad_Right.getRawAxis(RIGHT_STICK);
		 
		
		
		//what happens when the thing starts
			
	}
	
	public void end() {
		drive.stopDrive();
		//when the thing ends
	}
	
	@Override
	public void interrupted() {
		drive.stopAll(); 
		//if something happens to the thing
	}
	
}
