package org.usfirst.frc.team342.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

/**
 * This will allow the robot to go forward a certain distance in autonomous
 */

public class DriveToDistance extends Command {

	private DriveSystem DriveToDistance;
	
	private boolean leftisnegative;
	private boolean rightisnegative;
	
	private double goal;
	private double currentDistance_Left;
	private double currentDistance_Right;
	private double distanceleft_Left;
	private double distanceleft_Right;

	private static final double LEFT_SPEED = 0.5;
	private static final double RIGHT_SPEED = 0.5;
	private static final double CENTER_SPEED = 0.0;
	
	private static final double GOAL_DEADZONE = 0.015;
	
	public enum Distance {

		// put in numbers once we get them
		distance1(5), distance2(5);

		public final int value;

		Distance(int initValue) {
			this.value = initValue;
		}
	}

	public DriveToDistance(Distance distance) {

		DriveToDistance = DriveSystem.getInstance();
		requires(DriveToDistance);

		goal = distance.value;
	}

	protected void initialize() {
		
		if(DriveToDistance.getRightMasterEncoder() <= 0.0) {
			rightisnegative = true;
		}else {
			rightisnegative = false;
		}
		
		if(DriveToDistance.getLeftMasterEncoder() <= 0.0) {
			leftisnegative = true;
		}else {
			leftisnegative = false;
		}
	}

	protected void execute() {
		
		if(DriveToDistance.getRightMasterEncoder() <= 0.0) {
			rightisnegative = true;
		}else {
			rightisnegative = false;
		}
		
		if(DriveToDistance.getLeftMasterEncoder() <= 0.0) {
			leftisnegative = true;
		}else {
			leftisnegative = false;
		}
		
		currentDistance_Left = DriveToDistance.getDistance((DriveToDistance.getLeftMasterEncoder()));
		currentDistance_Right = DriveToDistance.getDistance((DriveToDistance.getRightMasterEncoder()));
		
		if(leftisnegative) {
			
			distanceleft_Left = currentDistance_Left + goal;
		}else {
			
			distanceleft_Left = currentDistance_Left - goal;
		}
		
		if(rightisnegative) {
			
			distanceleft_Right = currentDistance_Right + goal;
		}else {
			
			distanceleft_Right = currentDistance_Right - goal;
		}
		
		SmartDashboard.putNumber("Raw Encoder Value LEFT", DriveToDistance.getLeftMasterEncoder());
		SmartDashboard.putNumber("Raw Encoder Value RIGHT", DriveToDistance.getRightMasterEncoder());
		SmartDashboard.putNumber("Current Distance LEFT", currentDistance_Left);
		SmartDashboard.putNumber("Current Distance RIGHT", currentDistance_Right);
		SmartDashboard.putNumber("Distance Left LEFT", distanceleft_Left);
		SmartDashboard.putNumber("Distance Left RIGHT", distanceleft_Right);
		
		DriveToDistance.drive(LEFT_SPEED, RIGHT_SPEED, CENTER_SPEED);
	}

	protected boolean isFinished() {
		
		if((distanceleft_Left > 0.0 - GOAL_DEADZONE || distanceleft_Left < 0.0 + GOAL_DEADZONE) || (distanceleft_Right > 0.0 - GOAL_DEADZONE || distanceleft_Right < 0.0 + GOAL_DEADZONE)) {
			
			return true;
		}else {
			
			return false;
		}
	}

	protected void end() {

		DriveToDistance.stopDrive();
	}

	protected void interrupted() {

		end();
	}
}
