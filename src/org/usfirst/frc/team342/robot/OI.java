/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team342.robot;

import org.usfirst.frc.team342.robot.commands.claw.CollectCube;
import org.usfirst.frc.team342.robot.commands.claw.DispenseCube;
import org.usfirst.frc.team342.robot.commands.claw.StopCubeController;
import org.usfirst.frc.team342.robot.commands.drive.ManipulateWheelTOGGLE;
import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition;
import org.usfirst.frc.team342.robot.commands.lift.liftDown;
import org.usfirst.frc.team342.robot.commands.lift.liftUp;
import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition.LiftHeight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private static final OI instance = new OI ();
		
		private Joystick manipulator;
		private Button  manipulator_trigger ,
				manipulator_button2 ,
				manipulator_button3 ,
				manipulator_button4 ,
				manipulator_button5 ,
				manipulator_button6 ,
				manipulator_button7 ,
				manipulator_button8 ,
				manipulator_button9 ,
				manipulator_button10 ;
		private Joystick xbox_drive;
		private Button xbox_drive_trigger ,
				xbox_drive_button2 ,
				xbox_drive_button3 ,
				xbox_drive_button4 ,
				xbox_drive_button5 ,
				xbox_drive_button6 ,
				xbox_drive_button7 ,
				xbox_drive_button8 ,
				xbox_drive_button9 ,
				xbox_drive_button10 ;
		
		//Various Command to be assigned to buttons for manipulator
		
		private CollectCube collectcube;
		private ManipulateWheelTOGGLE manipulatewheeltoggle; 
		private StopCubeController stopcubecontroller;
		private DispenseCube dispensecube;
		private liftUp liftup;
		private liftDown liftdown;
		private LiftToPosition lifttoposition_1;
		private LiftToPosition lifttoposition_2;


	private OI() {
		
		 manipulator = new Joystick(1);
		 		manipulator_trigger = new JoystickButton(manipulator, 1);
		 		manipulator_button2 = new JoystickButton(manipulator, 2);
		 		manipulator_button3 = new JoystickButton(manipulator, 3);
		 		manipulator_button4 = new JoystickButton(manipulator, 4);
		 		manipulator_button5 = new JoystickButton(manipulator, 5);
		 		manipulator_button6 = new JoystickButton(manipulator, 6);
		 		manipulator_button7 = new JoystickButton(manipulator, 7);
		 		manipulator_button8 = new JoystickButton(manipulator, 8);
		 		manipulator_button9 = new JoystickButton(manipulator, 9);
		 		manipulator_button10 = new JoystickButton(manipulator, 10);
		 xbox_drive = new Joystick(0);
		 		xbox_drive_trigger = new JoystickButton(xbox_drive, 1);
				xbox_drive_button2 = new JoystickButton(xbox_drive, 2);
				xbox_drive_button3 = new JoystickButton(xbox_drive, 3);
				xbox_drive_button4 = new JoystickButton(xbox_drive, 4);
				xbox_drive_button5 = new JoystickButton(xbox_drive, 5);
				xbox_drive_button6 = new JoystickButton(xbox_drive, 6);
				xbox_drive_button7 = new JoystickButton(xbox_drive, 7);
				xbox_drive_button8 = new JoystickButton(xbox_drive, 8);
				xbox_drive_button9 = new JoystickButton(xbox_drive, 9);
				xbox_drive_button10 = new JoystickButton(xbox_drive, 10);
				
				
				collectcube= new CollectCube ();
				manipulatewheeltoggle= new ManipulateWheelTOGGLE ();
				stopcubecontroller= new StopCubeController ();
				dispensecube= new DispenseCube ();
				liftup= new liftUp ();
				liftdown= new liftDown ();
				lifttoposition_1= new LiftToPosition(LiftHeight.onethousand);
				lifttoposition_2= new LiftToPosition(LiftHeight.fourthousand);
				
				
				// manipulator buttons (TEMP) mapped to commands
				manipulator_trigger.whileHeld(dispensecube);
				manipulator_trigger.whenReleased(stopcubecontroller);
				manipulator_button2.whenPressed(stopcubecontroller);
				manipulator_button3.whenPressed(manipulatewheeltoggle);
				manipulator_button4.whenPressed(collectcube);
				
				manipulator_button9.whenPressed(lifttoposition_1);
				manipulator_button10.whenPressed(lifttoposition_2);
				
}
	public static OI getInstance () {
		return instance;
	}
	
	public Joystick getJoystickDrive () {
		return xbox_drive ;
	}
	
	public Joystick getJoystickManipulator () {
		return manipulator ;
	}
	

}