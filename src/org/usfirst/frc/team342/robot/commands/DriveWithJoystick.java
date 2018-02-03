package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {

	private static final int Y_AXIS = Joystick.AxisType.kY.value; 
	private static final int X_AXIS = Joystick.AxisType.kY.value;
	
	private static final double DEADZONE = 0.2;
	
	private double speed_y_left; 
	private double speed_x_left;
	private double speed_y_right;
	private double speed_x_right;
	private double x_average;
	
	private ManipulateWheelTOGGLE wheelToggle;
		
	private Joystick Joypad_Left; 
	private Joystick Joypad_Right;
	private OI oi; 
	private DriveSystem drive; 
	
	public DriveWithJoystick() {
		
		oi = OI.getInstance(); 
		drive = DriveSystem.getInstance();
		Joypad_Left = oi.getJoypadLeftDrive();
		Joypad_Right = oi.getJoypadRightDrive();
		wheelToggle = new ManipulateWheelTOGGLE();
	}

	public void intialize() {
		
		//when it starts
	}
	
	public void execute() { 
		
		speed_y_left = Joypad_Left.getRawAxis(Y_AXIS);
		speed_x_left = Joypad_Right.getRawAxis(X_AXIS);
		
		speed_y_right = Joypad_Left.getRawAxis(Y_AXIS);
		speed_x_right = Joypad_Right.getRawAxis(X_AXIS);
		
		x_average = (speed_x_left + speed_x_right) / 2.0;
		
		if(!Joypad_Left.getRawButton(1)) {
			x_average = 0.0;
		}else {
			wheelToggle.start();
		}
		
		drive.drive(speed_y_left, speed_y_right, x_average, DEADZONE);
	}
	
	@Override
	protected boolean isFinished() {

		return false;
	}
	
	public void end() {
		
		drive.stopDrive();
	}
	
	@Override
	public void interrupted() {
		
		drive.stopAll(); 
	}
	
}
