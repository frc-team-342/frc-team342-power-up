package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.commands.claw.LowerClaw;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForward extends CommandGroup {
	
	// Distances for autonomous
	private static final double DRIVE_DISTANCE_CENTER = 0.0;
	private static final double DRIVE_DISTANCE_SIDE = 0.0;
	
	// Drive commands
	private DriveToDistance drive_to_distance;
	
	// Cube controller commands
	private LowerClaw lower_claw;
	
    public DriveForward(char location) {
    	
        if(location == 'L') {
        	
        	//Drive Forward auto left
        	SmartDashboard.putString("Drive Forward Value: ", "L");
        	
        	lower_claw = new LowerClaw();
        	drive_to_distance = new DriveToDistance(DRIVE_DISTANCE_SIDE);
        	
        	addSequential(lower_claw);
        	addSequential(drive_to_distance);
        	
        }else if(location == 'C') {
        	
        	//Drive Forward auto center
        	SmartDashboard.putString("Drive Forward Value: ", "C");

        	lower_claw = new LowerClaw();
        	drive_to_distance = new DriveToDistance(DRIVE_DISTANCE_CENTER);
        	
        	addSequential(lower_claw);
        	addSequential(drive_to_distance);
        	
        }else if(location == 'R') {
        	
        	//Drive Forward auto right
        	SmartDashboard.putString("Drive Forward Value: ", "R");

        	lower_claw = new LowerClaw();
        	drive_to_distance = new DriveToDistance(DRIVE_DISTANCE_SIDE);
        	
        	addSequential(lower_claw);
        	addSequential(drive_to_distance);
        	
        }
    }
}
