package org.usfirst.frc.team342.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Switch extends CommandGroup {

    public Switch(char location, char switch_position) {
        
    	if(location == 'L' && switch_position == 'L') {
    		
    		//Switch auto left
    		
    	}else if(location == 'C' && switch_position == 'L') {
    		
    		//Switch auto center left
    		
    	}else if(location == 'C' && switch_position == 'R') {
    		
    		//Switch auto center right
    		
    	}else if(location == 'R' && switch_position == 'R') {
    		
    		//Switch auto right
    		
    	}
    }
}
