package org.usfirst.frc.team342.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Switch extends CommandGroup {

    public Switch(char location, char switch_position) {
        
    	if(location == 'L' && switch_position == 'L') {
    		
    		SmartDashboard.putString("Switch Value: ", "LL");
    		//Switch auto left
    		
    	}else if(location == 'C' && switch_position == 'L') {
    		
    		SmartDashboard.putString("Switch Value: ", "CL");
    		//Switch auto center left
    		
    	}else if(location == 'C' && switch_position == 'R') {
    		
    		SmartDashboard.putString("Switch Value: ", "CR");
    		//Switch auto center right
    		
    	}else if(location == 'R' && switch_position == 'R') {
    		
    		SmartDashboard.putString("Switch Value: ", "RR");
    		//Switch auto right
    		
    	}
    }
}
