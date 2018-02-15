package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This will allow the robot to go forward a certain distance in autonomous
 */

public class DriveToDistance extends Command {

	private DriveSystem drive;

	private double goal;
	
	private double init_Left;
	private double init_Right;
	private double current_Left;
	private double current_Right; 
	private double left_rotation_count;
	private double right_rotation_count;
	
	private static final double LEFT_SPEED = 0.5;
	private static final double RIGHT_SPEED = 0.5;
	private static final double CENTER_SPEED = 0.0;
	
	public enum Distance {

		// put in numbers once we get them
		distance1(10), distance2(5), distance3(1);

		public final int value;

		Distance(int initValue) {
			this.value = initValue;
		}
	}

	public DriveToDistance(Distance distance) {

		drive = DriveSystem.getInstance();
		requires(drive);

		goal = distance.value;
	}

	protected void initialize() {
		
		init_Left = Math.abs(drive.getLeftMasterEncoder());
		init_Right = drive.getRightMasterEncoder(); 
	}

	protected void execute() {
		
		current_Left = (Math.abs(drive.getLeftMasterEncoder())) - init_Left;
		current_Right = (drive.getRightMasterEncoder()) - init_Right;	
		
		left_rotation_count = current_Left / 4096;
		right_rotation_count = current_Right / 4096;
		
		drive.drive(LEFT_SPEED, RIGHT_SPEED, CENTER_SPEED);
		
		SmartDashboard.putNumber("GOAL", goal);
		SmartDashboard.putNumber("CUR_LEFT", current_Left);
		SmartDashboard.putNumber("CUR_RIGHT", current_Right);
		SmartDashboard.putNumber("ROT_COUNT_LEFT", left_rotation_count);
		SmartDashboard.putNumber("ROT_COUNT_RIGHT", right_rotation_count);
	}

	protected boolean isFinished() {
		
		if(left_rotation_count >= goal || right_rotation_count >= goal) {
			return true;
			
		}else {
			return false;
		}
	}

	protected void end() {

		drive.stopDrive();
	}

	protected void interrupted() {

		end();
	}
}
