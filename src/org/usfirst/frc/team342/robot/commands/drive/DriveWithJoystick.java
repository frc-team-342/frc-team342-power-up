package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {

	private static final int X_LEFT_AXIS = 0;
	private static final int Y_LEFT_AXIS = 1;
	private static final int X_RIGHT_AXIS = 4;
	private static final int Y_RIGHT_AXIS = 5;
	
	private static final double ZERO = 0.0;
	
	private static final double DEADZONE = 0.2;

	private double speed_y_left;
	private double speed_x_left;
	private double speed_y_right;
	private double speed_x_right;
	private double x_average;

	private OI oi;
	private DriveSystem drive;
	private Joystick Joypad;

	public DriveWithJoystick() {

	//gets drive. oi, and the oi joystick
		oi = OI.getInstance();
		drive = DriveSystem.getInstance();
		Joypad = oi.getJoystickDrive();
	}

	public void intialize() {

	}

	public void execute() {

	//gets and assigns the speeds and values of the axis's and out puts it
		speed_y_left = Joypad.getRawAxis(Y_LEFT_AXIS) * -1.0;
		speed_x_left = Joypad.getRawAxis(X_LEFT_AXIS);

		speed_y_right = Joypad.getRawAxis(Y_RIGHT_AXIS) * -1.0;
		speed_x_right = Joypad.getRawAxis(X_RIGHT_AXIS);

		x_average = ((speed_x_left + speed_x_right) / 2.0);
		
		if(Math.abs(speed_y_left) > DEADZONE || Math.abs(speed_y_right) > DEADZONE || Math.abs(x_average) > DEADZONE) {
			
			drive.drive(speed_y_left, speed_y_right, x_average);
		}else {
			
			drive.drive(ZERO, ZERO, ZERO);
		}

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

		end();
	}

}
