package org.usfirst.frc.team342.robot.commands.autonomous;

import org.usfirst.frc.team342.robot.commands.autonomous.DriveToDistance.Distance;
import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition;
import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition.LiftHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Switch extends CommandGroup {
	
	private static final int CENTER_RIGHT_ANGLE = 12;
	private static final int CENTER_LEFT_ANGLE = 348;
	
	private RotateToAngle rotatetoangle;
	private DriveToDistance drivetodistance;
	private LiftToPosition lifttoposition;
	
    public Switch(char location, char switch_position) {
        
    	if(location == 'L' && switch_position == 'L') {
    		//Switch auto left
    		SmartDashboard.putString("Switch Value: ", "LL");
    		
    		
    	}else if(location == 'C' && switch_position == 'L') {
    		//Switch auto center left
    		SmartDashboard.putString("Switch Value: ", "CL");
    		
    		rotatetoangle = new RotateToAngle(348);
    		drivetodistance = new DriveToDistance(Distance.distance1);
    		lifttoposition = new LiftToPosition(LiftHeight.fourthousand);
    		
    		addParallel(rotatetoangle);
    		addParallel(lifttoposition);
    		
    		
    	}else if(location == 'C' && switch_position == 'R') {
    		//Switch auto center right
    		SmartDashboard.putString("Switch Value: ", "CR");
    		
    		
    	}else if(location == 'R' && switch_position == 'R') {
    		//Switch auto right
    		SmartDashboard.putString("Switch Value: ", "RR");
    		
    		
    	}
    }
}
