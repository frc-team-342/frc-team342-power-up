package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.commands.claw.LowerClaw;
import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition;
import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition.LiftHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Switch extends CommandGroup {
	
	// Angles for various positions in autonomous
	private static final int CENTER_RIGHT_ANGLE = 45;
	private static final int CENTER_LEFT_ANGLE = 315;
	private static final int LEFT_ANGLE = 90;
	private static final int RIGHT_ANGLE = 270;
	
	// Distances for autonomous
	private static final double FORWARD_DISTANCE_CENTER = 10.0;
	private static final double FORWARD_DISTANCE_SIDE = 0.0;
	private static final double OFF_WALL = 1.0;
	private static final double DRIVE_IN = 1.0;
	
	// Time for dispensing the cube
	private static final int DISPENSE_TIME = 2;
	
	// Cube controller commands
	private LowerClaw lower_claw;
	private DispenseCubeTimed dispense_cube_timed;
	
	// Drive commands
	private RotateToAngle rotate_to_angle;
	private DriveToDistance drive_to_goal;
	private DriveToDistance drive_out;
	private DriveToDistance drive_in;
	
	// Lift commands
	private LiftToPosition lift_to_position;
		
    public Switch(char location, char switch_position) {
        
    	if(location == 'L' && switch_position == 'L') {
    		
    		//Switch auto left
    		SmartDashboard.putString("Switch Value: ", "LL");
    		
    		lower_claw = new LowerClaw();
    		lift_to_position = new LiftToPosition(LiftHeight.switchposition);
    		drive_to_goal = new DriveToDistance(FORWARD_DISTANCE_SIDE);
    		rotate_to_angle = new RotateToAngle(LEFT_ANGLE);
    		drive_in = new DriveToDistance(DRIVE_IN);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		addSequential(lower_claw);
    		addParallel(lift_to_position);
    		addSequential(drive_out);
    		addSequential(rotate_to_angle);
    		addSequential(drive_in);
    		addSequential(dispense_cube_timed);
    		
    	} else if(location == 'C' && switch_position == 'L') {
    		
    		//Switch auto center left
    		SmartDashboard.putString("Switch Value: ", "CL");
    		
    		lower_claw = new LowerClaw();
    		lift_to_position = new LiftToPosition(LiftHeight.switchposition);
    		drive_out = new DriveToDistance(OFF_WALL);
    		rotate_to_angle = new RotateToAngle(CENTER_LEFT_ANGLE);
    		drive_to_goal = new DriveToDistance(FORWARD_DISTANCE_CENTER);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		addSequential(lower_claw);
    		addParallel(lift_to_position);
    		addSequential(drive_out);
    		addSequential(rotate_to_angle);
    		addSequential(drive_to_goal);
    		addSequential(dispense_cube_timed);
    		
    	} else if(location == 'C' && switch_position == 'R') {
    		
    		//Switch auto center right
    		SmartDashboard.putString("Switch Value: ", "CR");
    		
    		lower_claw = new LowerClaw();
    		lift_to_position = new LiftToPosition(LiftHeight.switchposition);
    		drive_out = new DriveToDistance(OFF_WALL);
    		rotate_to_angle = new RotateToAngle(CENTER_RIGHT_ANGLE);
    		drive_to_goal = new DriveToDistance(FORWARD_DISTANCE_CENTER);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		addSequential(lower_claw);
    		addParallel(lift_to_position);
    		addSequential(drive_out);
    		addSequential(rotate_to_angle);
    		addSequential(drive_to_goal);
    		addSequential(dispense_cube_timed);
    		
    	} else if(location == 'R' && switch_position == 'R') {
    		
    		//Switch auto right
    		SmartDashboard.putString("Switch Value: ", "RR");
    		
    		lower_claw = new LowerClaw();
    		lift_to_position = new LiftToPosition(LiftHeight.switchposition);
    		drive_to_goal = new DriveToDistance(FORWARD_DISTANCE_SIDE);
    		rotate_to_angle = new RotateToAngle(RIGHT_ANGLE);
    		drive_in = new DriveToDistance(DRIVE_IN);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		addSequential(lower_claw);
    		addParallel(lift_to_position);
    		addSequential(drive_to_goal);
    		addSequential(rotate_to_angle);
    		addSequential(drive_in);
    		addSequential(dispense_cube_timed);
    		
    	}
    }
}
