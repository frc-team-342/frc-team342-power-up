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
	
	// Time for raising the lift
	private static final int LIFT_TIME = 3850;
	
	// Time for dispensing the cube
	private static final int DISPENSE_TIME = 2;
	
	// Cube controller commands
	private LowerClaw lower_claw;
	private DispenseCubeTimed dispense_cube_timed;
	
	// Lift positions
	private LiftToPosition lift_to_position;
	private LiftUpTimed lift_up_timed;
	
	// Drive commands
	private RotateToAngle rotate_to_angle;
	private DriveToDistance drive_to_goal;
	private DriveToDistance drive_in;
	
    public Scale(char location) {
       
    	if(location == 'L') {
    		
    		//Scale auto left
    		SmartDashboard.putString("Scale Value: ", "L");
    		
    		lower_claw = new LowerClaw();
    		drive_to_goal = new DriveToDistance(Distance.SCALE_DISTANCE);
    		lift_to_position = new LiftToPosition(LiftHeight.scalemiddle);
    		lift_up_timed = new LiftUpTimed(LIFT_TIME);
    		rotate_to_angle = new RotateToAngle(LEFT_ANGLE);
    		drive_in = new DriveToDistance(Distance.DRIVE_IN_DISTANCE_SCALE);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		addSequential(lower_claw);
    		addSequential(drive_to_goal);
    		//addParallel(lift_to_position);
    		addSequential(lift_up_timed);
    		addSequential(rotate_to_angle);
    		addSequential(drive_in);
    		addSequential(dispense_cube_timed);
    		
    	}else if(location == 'R') {
    		
    		//Scale auto right
    		SmartDashboard.putString("Scale Value: ", "R");

    		lower_claw = new LowerClaw();
    		drive_to_goal = new DriveToDistance(Distance.SCALE_DISTANCE);
    		lift_to_position = new LiftToPosition(LiftHeight.scalemiddle);
    		lift_up_timed = new LiftUpTimed(LIFT_TIME);
    		rotate_to_angle = new RotateToAngle(RIGHT_ANGLE);
    		drive_in = new DriveToDistance(Distance.DRIVE_IN_DISTANCE_SCALE);
    		dispense_cube_timed = new DispenseCubeTimed(DISPENSE_TIME);
    		
    		addSequential(lower_claw);
    		addSequential(drive_to_goal);
    		//addParallel(lift_to_position);
    		addSequential(lift_up_timed);
    		addSequential(rotate_to_angle);
    		addSequential(drive_in);
    		addSequential(dispense_cube_timed);
    	}
    }
}
