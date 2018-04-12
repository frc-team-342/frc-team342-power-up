package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.commands.autonomous.DriveToDistance.Distance;
import org.usfirst.frc.team342.robot.commands.claw.LowerClaw;
import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition;
import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition.LiftHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Scale extends CommandGroup {
	
	// Angles for various positions in autonomous
	private static final int LEFT_ANGLE = 90;
	private static final int RIGHT_ANGLE = 270;
	private static final int NINTEY = 94;
	private static final int TWO_SEVENTY = 274;
	
	// Time for raising the lift
	//OLD = 3875
	private static final int LIFT_TIME = 3600;
	
	// Time for dispensing the cube
	private static final int DISPENSE_TIME = 2;
	
	// Cube controller commands
	private LowerClaw lower_claw;
	private DispenseCubeTimed dispense_cube_timed;
	
	// Lift positions
	private LiftToPosition lift_to_position;
	private LiftUpTimed lift_up_timed;
	private LiftDownTimed lift_down;
	
	// Drive commands
	private RotateToAngle rotate_to_scale_side;
	private DriveToDistance drive_to_scale_side;
	private RotateToAngle turn_around;
	private DriveToDistance drive_to_platform_zone;
	private RotateToAngle rotate_to_platform_zone;
	private DriveToDistance drive_across_platform_zone;
	private RotateToAngle rotate_to_scale;
	private DriveToDistance drive_to_scale_platform_zone;
	
    public Scale(char location, boolean second_cube, boolean cross_over) {
       
    	if(location == 'L'  && !cross_over) {
    		
    		//Scale auto left
    		SmartDashboard.putString("Scale Value: ", "L");
    		
    		lower_claw = new LowerClaw();
    		drive_to_scale_side = new DriveToDistance(Distance.SCALE_DISTANCE, false);
    		lift_to_position = new LiftToPosition(LiftHeight.scalemiddle);
    		lift_up_timed = new LiftUpTimed(LIFT_TIME);
    		rotate_to_scale_side = new RotateToAngle(LEFT_ANGLE);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		turn_around = new RotateToAngle(90);
    		lift_down = new LiftDownTimed(0); 
    		
    		addSequential(lower_claw);
    		addSequential(drive_to_scale_side);
    		//addParallel(lift_to_position);
    		addSequential(lift_up_timed);
    		addSequential(rotate_to_scale_side);
    		addSequential(dispense_cube_timed);
    		//addSequential(turn_around); 
    		//addSequential(lift_down);
    		//TODO find the rotation of current cube
    		
    	}else if(location == 'R' && !cross_over) {
    		
    		//Scale auto right
    		SmartDashboard.putString("Scale Value: ", "R");

    		lower_claw = new LowerClaw();
    		drive_to_scale_side = new DriveToDistance(Distance.SCALE_DISTANCE, false);
    		lift_to_position = new LiftToPosition(LiftHeight.scalemiddle);
    		lift_up_timed = new LiftUpTimed(LIFT_TIME);
    		rotate_to_scale_side = new RotateToAngle(RIGHT_ANGLE);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		addSequential(lower_claw);
    		addSequential(drive_to_scale_side);
    		//addParallel(lift_to_position);
    		addSequential(lift_up_timed);
    		addSequential(rotate_to_scale_side);
    		addSequential(dispense_cube_timed);
    		
    	}else if(location == 'L' && cross_over) {
    		
    		//Scale auto left crossover
    		SmartDashboard.putString("Scale Value: ",  "LC");
    		
    		lower_claw = new LowerClaw();
    		drive_to_platform_zone = new DriveToDistance(Distance.PLATFORM_ZONE_WALL, false);
    		rotate_to_platform_zone = new RotateToAngle(NINTEY);
    		drive_across_platform_zone = new DriveToDistance(Distance.SCALE_ACROSS_PLATFORM_ZONE, false);
    		rotate_to_scale = new RotateToAngle(TWO_SEVENTY);
    		lift_to_position = new LiftToPosition(LiftHeight.scalehigh);
    		lift_up_timed = new LiftUpTimed(LIFT_TIME);
    		drive_to_scale_platform_zone = new DriveToDistance(Distance.SCALE_FROM_PLATFORM_ZONE, false);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		addSequential(lower_claw);
    		addSequential(drive_to_platform_zone);
    		addSequential(rotate_to_platform_zone);
    		addSequential(drive_across_platform_zone);
    		addSequential(rotate_to_scale);
    		addSequential(lift_to_position);
    		addSequential(lift_up_timed);
    		addSequential(drive_to_scale_platform_zone);
    		addSequential(dispense_cube_timed);
    		
    	}else if(location == 'R' && cross_over) {

    		//Scale auto right crossover
    		SmartDashboard.putString("Scale Value: ",  "RC");
    		
    		lower_claw = new LowerClaw();
    		drive_to_platform_zone = new DriveToDistance(Distance.PLATFORM_ZONE_WALL, false);
    		rotate_to_platform_zone = new RotateToAngle(TWO_SEVENTY);
    		drive_across_platform_zone = new DriveToDistance(Distance.SCALE_ACROSS_PLATFORM_ZONE, false);
    		rotate_to_scale = new RotateToAngle(NINTEY);
    		lift_to_position = new LiftToPosition(LiftHeight.scalehigh);
    		lift_up_timed = new LiftUpTimed(LIFT_TIME);
    		drive_to_scale_platform_zone = new DriveToDistance(Distance.SCALE_FROM_PLATFORM_ZONE, false);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		addSequential(lower_claw);
    		addSequential(drive_to_platform_zone);
    		addSequential(rotate_to_platform_zone);
    		addSequential(drive_across_platform_zone);
    		addSequential(rotate_to_scale);
    		addSequential(lift_to_position);
    		addSequential(lift_up_timed);
    		addSequential(drive_to_scale_platform_zone);
    		addSequential(dispense_cube_timed);
    	}
    }
}
