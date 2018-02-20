package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveWithJoystick extends Command {
	
	private static final double ZERO = 0.0;
	private static final double DEADZONE = 0.2;

	private double speed_y_left;
	private double speed_x_left;
	private double speed_y_right;
	private double speed_x_right;
	private double x_average;

	private OI oi;
	private DriveSystem drive;

	public DriveWithJoystick() {

		//gets the DriveSystem, and OI
		oi = OI.getInstance();
		drive = DriveSystem.getInstance();
	}

	public void intialize() {

	}

	public void execute() {

	//gets and assigns the speeds and values of the axis's and out puts it
		speed_y_left = oi.getJoystickDriveLeftYAxis() * -1.0;
		speed_x_left = oi.getJoystickDriveLeftXAxis();

		speed_y_right = oi.getJoystickDriveRightYAxis() * -1.0;
		speed_x_right = oi.getJoystickDriveRightXAxis();

		x_average = ((speed_x_left + speed_x_right) / 2.0);
		
		if(Math.abs(speed_y_left) > DEADZONE || Math.abs(speed_y_right) > DEADZONE || Math.abs(x_average) > DEADZONE) {
			
			if(drive.getWheelState()) {
				
				drive.drive(speed_y_left, speed_y_right, ZERO);
			}else {
				
				drive.drive(speed_y_left, speed_y_right, x_average);
			}
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
