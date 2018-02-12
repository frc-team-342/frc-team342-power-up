package org.usfirst.frc.team342.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveForward extends CommandGroup {

    public DriveForward(char location) {
    	
        if(location == 'L') {
        	
        	SmartDashboard.putString("Drive Forward Value: ", "L");
        	//Drive Forward auto left
        	
        }else if(location == 'C') {
        	
        	SmartDashboard.putString("Drive Forward Value: ", "C");
        	//Drive Forward auto center
        	
        }else if(location == 'R') {
        	
        	SmartDashboard.putString("Drive Forward Value: ", "R");
        	//Drive Forward auto right
        	
        }
    }
}
