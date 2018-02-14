package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Switch extends CommandGroup {
	
	private static final int CENTER_RIGHT_ANGLE = 45;
	private static final int CENTER_LEFT_ANGLE = 315;
	
	private static final double SPEED = -0.25;
	
	private RotateToAngle rotatetoangle;
	private DriveToDistance drivetodistance;
	private DriveTimed drivetimed;
	private LiftToPosition lifttoposition;
	
    public Switch(char location, char switch_position) {
        
    	if(location == 'L' && switch_position == 'L') {
    		//Switch auto left
    		SmartDashboard.putString("Switch Value: ", "LL");
    		
    		
    	}else if(location == 'C' && switch_position == 'L') {
    		
    		//Switch auto center left
    		SmartDashboard.putString("Switch Value: ", "CL");
    		
    		rotatetoangle = new RotateToAngle(CENTER_LEFT_ANGLE);
    		drivetimed = new DriveTimed(3, SPEED);
    		
    		addSequential(rotatetoangle);
    		addSequential(drivetimed);
    		
    		
    	}else if(location == 'C' && switch_position == 'R') {
    		
    		//Switch auto center right
    		SmartDashboard.putString("Switch Value: ", "CR");
    		
    		rotatetoangle = new RotateToAngle(CENTER_RIGHT_ANGLE);
    		drivetimed = new DriveTimed(3, SPEED);
    		
    		addSequential(rotatetoangle);
    		addSequential(drivetimed);
    		
    		
    	}else if(location == 'R' && switch_position == 'R') {
    		//Switch auto right
    		SmartDashboard.putString("Switch Value: ", "RR");
    		
    		
    	}
    }
}
