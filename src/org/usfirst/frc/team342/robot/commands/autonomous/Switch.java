package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.commands.autonomous.DriveToDistance.Distance;
import org.usfirst.frc.team342.robot.commands.claw.CollectCube;
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
	private static final int CENTER_RIGHT_ANGLE = 35;
	private static final int CENTER_LEFT_ANGLE = 325;
	private static final int STRAIGHT_LEFT_ANGLE = 40;
	private static final int STRAIGHT_RIGHT_ANGLE = 320;
	private static final int LEFT_ANGLE = 90;
	private static final int RIGHT_ANGLE = 270;
	private static final int NINETY_DEGREES = 90;
	private static final int ONE_EIGHTY_DEGREES = 180;
	private static final int TWO_SEVENTY_DEGREES = 270;
	
	// Time for raising the lift
	private static final int LIFT_TIME = 1500;
	
	// Time for dispensing the cube
	private static final int DISPENSE_TIME = 2;
	
	// Cube controller commands
	private LowerClaw lower_claw;
	private DispenseCubeTimed dispense_cube_timed;
	private CollectCube collect_cube;
	
	// Drive commands
	private RotateToAngle rotate_to_angle_one;
	private RotateToAngle rotate_to_angle_two;
	private RotateToAngle straighten_out;
	private DriveToDistance drive_to_goal;
	private DriveToDistance drive_out;
	private DriveToDistance drive_in;
	private DriveToDistance drive_to_platform_zone;
	private DriveToDistance drive_to_cube;
	private DriveToDistance drive_to_pick_cube_up;
	
	// Lift commands
	private LiftToPosition lift_to_position;
	private LiftUpTimed lift_up_to_switch;
	private LiftDownTimed lift_down_to_floor;
	
		
    public Switch(char location, char switch_position, boolean second_cube) {
        
    	if(location == 'L' && switch_position == 'L' && !second_cube) {
    		
    		//Switch auto left
    		SmartDashboard.putString("Switch Value: ", "LL");
    		
    		lower_claw = new LowerClaw();
    		drive_to_goal = new DriveToDistance(Distance.SIDE_SWITCH, false);
    		lift_to_position = new LiftToPosition(LiftHeight.switchposition);
    		lift_up_to_switch = new LiftUpTimed(LIFT_TIME);
    		rotate_to_angle_one = new RotateToAngle(LEFT_ANGLE);
    		drive_in = new DriveToDistance(Distance.DRIVE_IN_DISTANCE_SWITCH, false);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		
    		addSequential(lower_claw);
    		addSequential(drive_to_goal);
    		//addParallel(lift_to_position);
    		addSequential(lift_up_to_switch);
    		addSequential(rotate_to_angle_one);
    		addSequential(drive_in, 0.5);
    		addSequential(dispense_cube_timed);
    		
    	} else if(location == 'L' && switch_position == 'L' && second_cube) {
    		
    		//NICK IS WRITING THIS CODE
    		
    		//Switch auto right
    		SmartDashboard.putString("Switch Value: ", "RR");
    		
    		lower_claw = new LowerClaw();
    		drive_to_goal = new DriveToDistance(Distance.SIDE_SWITCH, false);
    		lift_to_position = new LiftToPosition(LiftHeight.switchposition);
    		lift_up_to_switch = new LiftUpTimed(LIFT_TIME);
    		rotate_to_angle_one = new RotateToAngle(LEFT_ANGLE);
    		drive_in = new DriveToDistance(Distance.DRIVE_IN_DISTANCE_SWITCH, false);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		drive_out = new DriveToDistance(Distance.DRIVE_OFF_SWITCH_WALL, true);
    		rotate_to_angle_two = new RotateToAngle(TWO_SEVENTY_DEGREES);
    		drive_to_platform_zone = new DriveToDistance(Distance.DRIVE_TO_PLATFORM_ZONE_FROM_SWITCH, false);
    		drive_to_cube = new DriveToDistance(Distance.DRIVE_TO_CUBE_SWITCH, false);
    		drive_to_pick_cube_up = new DriveToDistance(Distance.DRIVE_IN_DISTANCE_SWITCH, false);
    		
    		addSequential(lower_claw);
    		addSequential(drive_to_goal);
    		//addParallel(lift_to_position);
    		addSequential(lift_up_to_switch);
    		addSequential(rotate_to_angle_one);
    		addSequential(drive_in, 0.5);
    		addSequential(dispense_cube_timed);
    		addSequential(drive_out);
    		addSequential(rotate_to_angle_two);
    		addSequential(drive_to_platform_zone);
    		addSequential(rotate_to_angle_one);
    		addSequential(drive_to_cube);
    		addSequential(rotate_to_angle_one);
    		addSequential(drive_to_pick_cube_up);
    		addParallel(collect_cube);
    		addSequential(lift_up_to_switch);
    		addSequential(drive_in);
    		addSequential(dispense_cube_timed);
    		
    	} else if(location == 'C' && switch_position == 'L' && !second_cube) {
    		
    		//Switch auto center left
    		SmartDashboard.putString("Switch Value: ", "CL");
    		
    		lower_claw = new LowerClaw();
    		lift_to_position = new LiftToPosition(LiftHeight.switchposition);
    		lift_up_to_switch = new LiftUpTimed(LIFT_TIME);
    		drive_out = new DriveToDistance(Distance.DRIVE_OFF_WALL, false);
    		rotate_to_angle_one = new RotateToAngle(CENTER_LEFT_ANGLE);
    		drive_to_goal = new DriveToDistance(Distance.CENTER_SWITCH_LEFT, false);
    		straighten_out = new RotateToAngle(STRAIGHT_LEFT_ANGLE);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		addSequential(lower_claw);
    		addSequential(drive_out);
    		addSequential(rotate_to_angle_one);
    		//addParallel(lift_to_position);
    		addSequential(lift_up_to_switch);
    		addSequential(drive_to_goal);
    		addSequential(straighten_out, 1.0);
    		addSequential(dispense_cube_timed);
    		
    	} else if(location == 'C' && switch_position == 'L' && second_cube) {
    		
    		//ELIZABETH IS WRITING THIS CODE
    	
    	} else if(location == 'C' && switch_position == 'R' && !second_cube) {
    		
    		//Switch auto center right
    		SmartDashboard.putString("Switch Value: ", "CR");
    		
    		lower_claw = new LowerClaw();
    		lift_to_position = new LiftToPosition(LiftHeight.switchposition);
    		lift_up_to_switch = new LiftUpTimed(LIFT_TIME);
    		drive_out = new DriveToDistance(Distance.DRIVE_OFF_WALL, false);
    		rotate_to_angle_one = new RotateToAngle(CENTER_RIGHT_ANGLE);
    		drive_to_goal = new DriveToDistance(Distance.CENTER_SWITCH_RIGHT, false);
    		straighten_out = new RotateToAngle(STRAIGHT_RIGHT_ANGLE);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		addSequential(lower_claw);
    		addSequential(drive_out);
    		addSequential(rotate_to_angle_one);
    		//addParallel(lift_to_position);
    		addSequential(lift_up_to_switch);
    		addSequential(drive_to_goal);
    		addSequential(straighten_out, 1.0);
    		addSequential(dispense_cube_timed);
    		
    	} else if(location == 'C' && switch_position == 'R' && second_cube) {
    		
    		//ELIZABETH IS WRITING THIS CODE
    	
    	} else if(location == 'R' && switch_position == 'R' && !second_cube) {
    		
    		//Switch auto right
    		SmartDashboard.putString("Switch Value: ", "RR");
    		
    		lower_claw = new LowerClaw();
    		drive_to_goal = new DriveToDistance(Distance.SIDE_SWITCH, false);
    		lift_to_position = new LiftToPosition(LiftHeight.switchposition);
    		lift_up_to_switch = new LiftUpTimed(LIFT_TIME);
    		rotate_to_angle_one = new RotateToAngle(RIGHT_ANGLE);
    		drive_in = new DriveToDistance(Distance.DRIVE_IN_DISTANCE_SWITCH, false);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		addSequential(lower_claw);
    		addSequential(drive_to_goal);
    		//addParallel(lift_to_position);
    		addSequential(lift_up_to_switch);
    		addSequential(rotate_to_angle_one);
    		addSequential(drive_in, 0.5);
    		addSequential(dispense_cube_timed);
    		
    	} else if(location == 'R' && switch_position == 'R' && second_cube) {
    		
    		//NICK IS WRITING THIS CODE
    		
    		//Switch auto right
    		SmartDashboard.putString("Switch Value: ", "RR");
    		
    		lower_claw = new LowerClaw();
    		drive_to_goal = new DriveToDistance(Distance.SIDE_SWITCH, false);
    		lift_to_position = new LiftToPosition(LiftHeight.switchposition);
    		lift_up_to_switch = new LiftUpTimed(LIFT_TIME);
    		rotate_to_angle_one = new RotateToAngle(RIGHT_ANGLE);
    		drive_in = new DriveToDistance(Distance.DRIVE_IN_DISTANCE_SWITCH, false);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		drive_out = new DriveToDistance(Distance.DRIVE_OFF_SWITCH_WALL, true);
    		rotate_to_angle_two = new RotateToAngle(NINETY_DEGREES);
    		drive_to_platform_zone = new DriveToDistance(Distance.DRIVE_TO_PLATFORM_ZONE_FROM_SWITCH, false);
    		drive_to_cube = new DriveToDistance(Distance.DRIVE_TO_CUBE_SWITCH, false);
    		drive_to_pick_cube_up = new DriveToDistance(Distance.DRIVE_IN_DISTANCE_SWITCH, false);
    		
    		addSequential(lower_claw);
    		addSequential(drive_to_goal);
    		//addParallel(lift_to_position);
    		addSequential(lift_up_to_switch);
    		addSequential(rotate_to_angle_one);
    		addSequential(drive_in, 0.5);
    		addSequential(dispense_cube_timed);
    		addSequential(drive_out);
    		addSequential(rotate_to_angle_two);
    		addSequential(drive_to_platform_zone);
    		addSequential(rotate_to_angle_one);
    		addSequential(drive_to_cube);
    		addSequential(rotate_to_angle_one);
    		addSequential(drive_to_pick_cube_up);
    		addParallel(collect_cube);
    		addSequential(lift_up_to_switch);
    		addSequential(drive_in);
    		addSequential(dispense_cube_timed);
    	} 
    }
}
