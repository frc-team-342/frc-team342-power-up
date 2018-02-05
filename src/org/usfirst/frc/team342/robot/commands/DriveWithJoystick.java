package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {

	private static final int X_LEFT_AXIS = 0;
	private static final int Y_LEFT_AXIS = 1;
	private static final int X_RIGHT_AXIS = 2;
	private static final int Y_RIGHT_AXIS = 3;

	private static final double DEADZONE = 0.2;

	private double speed_y_left;
	private double speed_x_left;
	private double speed_y_right;
	private double speed_x_right;
	private double x_average;

	private ManipulateWheelDOWN wheelDown;
	private ManipulateWheelUP wheelUp;

	private Joystick Joypad;
	private OI oi;
	private DriveSystem drive;

	public DriveWithJoystick() {

		oi = OI.getInstance();
		drive = DriveSystem.getInstance();
		Joypad = oi.getJoypadRightDrive();
		wheelDown = new ManipulateWheelDOWN();
		wheelUp = new ManipulateWheelUP();
	}

	public void intialize() {

		// when it starts
	}

	public void execute() { 
		
		
		speed_y_left = Joypad.getRawAxis(Y_LEFT_AXIS);
//		speed_x_left = Joypad.getRawAxis(X_LEFT_AXIS);
		
		speed_y_right = Joypad.getRawAxis(Y_RIGHT_AXIS);
//		speed_x_right = Joypad.getRawAxis(X_RIGHT_AXIS);

		
		x_average = (speed_x_left + speed_x_right) / 2.0;
		
		if(!Joypad.getRawButton(5) && !drive.getWheelState()) {
			wheelUp.start();
			x_average = 0.0;
		}else {
			wheelDown.start();
		}
		
//		drive.drive(speed_y_left, speed_y_right, x_average, DEADZONE);
		System.out.println("Speed y is" + speed_y_left);
		System.out.println("Speed x is" + speed_y_right);
		
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
