package org.usfirst.frc.team342.robot.commands.drive;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;

import edu.wpi.first.wpilibj.command.Command;

/**
 *allows us to drive with two Logitech controls
 */
public class DriveWithLogitech extends Command {

	private static final double DEADZONE = 0.2;
	private static final double ZERO = 0.0; 
	
	private double speed_y_left;
	private double speed_x_left;
	private double speed_y_right;
	private double speed_x_right;
	private double x_average; 

	private OI oi; 
	private DriveSystem drive; 
	
	
    public DriveWithLogitech() {

    	//gets feedback from oi and drivesystem
    	oi = OI.getInstance();
    	drive = DriveSystem.getInstance(); 
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//sets up the axis's 
    	speed_y_left = oi.getJoystickLogitechLeftYAxis();
    	speed_x_left = oi.getJoystickLogitechLeftXAxis();
    	speed_y_right = oi.getJoystickLogitechRightYAxis();
    	speed_x_right = oi.getJoystickLogitechRightXAxis();
    	
    	//makes the x axis have a single average
    	x_average = ((speed_x_left + speed_x_right) / 2.0);  
    
    	//establishes the deadzone
    	if(Math.abs(speed_y_left) > DEADZONE || (speed_y_right) > DEADZONE || Math.abs(x_average) > DEADZONE) {
    		
    		if(drive.getWheelState()) {
				
				drive.drive(speed_y_left, speed_y_right, ZERO);
			}else {
				
				drive.drive(speed_y_left, speed_y_right, x_average);
			} 
    	} else {
    		
    		drive.drive(ZERO, ZERO, ZERO);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	drive.stopDrive(); 
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end(); 
    }
}
