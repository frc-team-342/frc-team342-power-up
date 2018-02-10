package org.usfirst.frc.team342.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Scale extends CommandGroup {

    public Scale(char location) {
       
    	if(location == 'L') {
    		
    		SmartDashboard.putString("Scale Value: ", "L");
    		//Scale auto left
    		
    	}else if(location == 'R') {
    		
    		SmartDashboard.putString("Scale Value: ", "R");
    		//Scale auto right
    		
    	}
    }
}
