package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveWithJoystick extends Command {

	private static final int X_LEFT_AXIS = 0;
	private static final int Y_LEFT_AXIS = 1;
	private static final int X_RIGHT_AXIS = 4;
	private static final int Y_RIGHT_AXIS = 5;

	private static final double DEADZONE = 0.4;

	private double speed_y_left;
	private double speed_x_left;
	private double speed_y_right;
	private double speed_x_right;
	private double x_average;

	private OI oi;
	private DriveSystem drive;
	private Joystick Joypad;

	public DriveWithJoystick() {

		oi = OI.getInstance();
		drive = DriveSystem.getInstance();
		Joypad = oi.getJoystickDrive();
	}

	public void intialize() {

	}

	public void execute() {

		speed_y_left = Joypad.getRawAxis(Y_LEFT_AXIS);
		speed_x_left = Joypad.getRawAxis(X_LEFT_AXIS);

		speed_y_right = Joypad.getRawAxis(Y_RIGHT_AXIS);
		speed_x_right = Joypad.getRawAxis(X_RIGHT_AXIS);

		x_average = (speed_x_left + speed_x_right) / 2.0;
		
		if (!Joypad.getRawButton(5)) {
			x_average = 0.0;
		} 

		SmartDashboard.putNumber("Left Y", speed_y_left);
		SmartDashboard.putNumber("Left X", speed_x_left);
		SmartDashboard.putNumber("Right Y", speed_y_right);
		SmartDashboard.putNumber("Right X", speed_x_right);
		SmartDashboard.putNumber("Average X", x_average);

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
