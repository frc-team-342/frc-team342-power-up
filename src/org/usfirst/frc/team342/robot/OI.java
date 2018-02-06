/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team342.robot;

import org.usfirst.frc.team342.robot.commands.CollectCube;
import org.usfirst.frc.team342.robot.commands.DispenseCube;
import org.usfirst.frc.team342.robot.commands.LiftToPosition;
import org.usfirst.frc.team342.robot.commands.LiftToPosition.LiftHeight;
import org.usfirst.frc.team342.robot.commands.ManipulateWheelTOGGLE;
import org.usfirst.frc.team342.robot.commands.StopCubeController;
import org.usfirst.frc.team342.robot.commands.liftDown;
import org.usfirst.frc.team342.robot.commands.liftUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private static final OI instance = new OI ();
	
	 // Create the joystick and the 6 buttons on it
<<<<<<< HEAD
		Joystick leftJoy;
		 Button leftJoy_trigger,
				leftJoy_button2 ,
				leftJoy_button3 ,
				leftJoy_button4 ,
				leftJoy_button5 ,
				leftJoy_button6 ,
				leftJoy_button7,
				leftJoy_button8 ;
		 
		Joystick rightJoy ;
		 Button rightJoy_trigger ,
				rightJoy_button2 ,
				rightJoy_button3 ,
				rightJoy_button4 ,
				rightJoy_button5 ,
				rightJoy_button6 ,
				rightJoy_button7 ,
				rightJoy_button8 ;
		 
		Joystick rightPad;
		Button  rightPad_trigger ,
				rightPad_button2 ,
				rightPad_button3 ,
				rightPad_button4 ,
				rightPad_button5 ,
				rightPad_button6 ,
				rightPad_button7 ,
				rightPad_button8 ;
		
		Joystick leftPad;
		Button leftPad_trigger ,
				leftPad_button2 ,
				leftPad_button3 ,
				leftPad_button4 ,
				leftPad_button5 ,
				leftPad_button6 ,
				leftPad_button7 ,
				leftPad_button8 ;


	private OI() {
		 leftJoy = new Joystick(0);
		  leftJoy_trigger = new JoystickButton(leftJoy, 1);
				leftJoy_button2 = new JoystickButton(leftJoy, 2);
				leftJoy_button3 = new JoystickButton(leftJoy, 3);
				leftJoy_button4 = new JoystickButton(leftJoy, 4);
				leftJoy_button5 = new JoystickButton(leftJoy, 5);
				leftJoy_button6 = new JoystickButton(leftJoy, 6);
				leftJoy_button7 = new JoystickButton(leftJoy, 7);
				leftJoy_button8 = new JoystickButton(leftJoy, 8);
				
		  rightJoy = new Joystick(1);
		  rightJoy_trigger = new JoystickButton(rightJoy, 1);
				rightJoy_button2 = new JoystickButton(rightJoy, 2);
				rightJoy_button3 = new JoystickButton(rightJoy, 3);
				rightJoy_button4 = new JoystickButton(rightJoy, 4);
				rightJoy_button5 = new JoystickButton(rightJoy, 5);
				rightJoy_button6 = new JoystickButton(rightJoy, 6);
				rightJoy_button7 = new JoystickButton(rightJoy, 7);
				rightJoy_button8 = new JoystickButton(rightJoy, 8);
				
		 rightPad = new Joystick(2);
		  rightPad_trigger = new JoystickButton(rightPad, 1);
				rightPad_button2 = new JoystickButton(rightPad, 2);
				rightPad_button3 = new JoystickButton(rightPad, 3);
				rightPad_button4 = new JoystickButton(rightPad, 4);
				rightPad_button5 = new JoystickButton(rightPad, 5);
				rightPad_button6 = new JoystickButton(rightPad, 6);
				rightPad_button7 = new JoystickButton(rightPad, 7);
				rightPad_button8 = new JoystickButton(rightPad, 8);
				
		 leftPad = new Joystick(3);
		leftPad_trigger = new JoystickButton(leftPad, 1);
				leftPad_button2 = new JoystickButton(leftPad, 2);
				leftPad_button3 = new JoystickButton(leftPad, 3);
				leftPad_button4 = new JoystickButton(leftPad, 4);
				leftPad_button5 = new JoystickButton(leftPad, 5);
				leftPad_button6 = new JoystickButton(leftPad, 6);
				leftPad_button7 = new JoystickButton(leftPad, 7);
				leftPad_button8 = new JoystickButton(leftPad, 8);
=======
	
		
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
				
				liftup = new liftUp();
				liftdown = new liftDown();
				lifttoposition_1= new LiftToPosition(LiftHeight.onethousand);
				lifttoposition_2= new LiftToPosition(LiftHeight.fourthousand);
				
				
				
				
				// manipulator buttons (TEMP) mapped to commands
				
				manipulator_trigger.whileHeld(dispensecube);
				manipulator_button2.whenPressed(stopcubecontroller);
				manipulator_button3.whenPressed(manipulatewheeltoggle);
				manipulator_button4.whenPressed(collectcube);
				manipulator_button5.whileHeld(liftup);
				manipulator_button6.whileHeld(liftdown);
				manipulator_button9.whenPressed(lifttoposition_1);
				manipulator_button10.whenPressed(lifttoposition_2);
				
				
				
>>>>>>> dev-eo
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