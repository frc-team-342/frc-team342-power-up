/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team342.robot;

import org.usfirst.frc.team342.robot.commands.CollectCube;
import org.usfirst.frc.team342.robot.commands.DispenseCube;
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
	
		private Joystick leftJoy;
		private Button leftJoy_trigger,
				leftJoy_button2 ,
				leftJoy_button3 ,
				leftJoy_button4 ,
				leftJoy_button5 ,
				leftJoy_button6 ,
				leftJoy_button7,
				leftJoy_button8 ;
		private Joystick rightJoy ;
		private  Button rightJoy_trigger ,
				rightJoy_button2 ,
				rightJoy_button3 ,
				rightJoy_button4 ,
				rightJoy_button5 ,
				rightJoy_button6 ,
				rightJoy_button7 ,
				rightJoy_button8 ;
		private Joystick rightPad;
		private Button  rightPad_trigger ,
				rightPad_button2 ,
				rightPad_button3 ,
				rightPad_button4 ,
				rightPad_button5 ,
				rightPad_button6 ,
				rightPad_button7 ,
				rightPad_button8 ,
				rightPad_button9 ,
				rightPad_button10 ;
		private Joystick leftPad;
		private Button leftPad_trigger ,
				leftPad_button2 ,
				leftPad_button3 ,
				leftPad_button4 ,
				leftPad_button5 ,
				leftPad_button6 ,
				leftPad_button7 ,
				leftPad_button8 ;
		
		//Various Command to be assigned to buttons for manipulator
		
		private CollectCube collectcube;
		private ManipulateWheelTOGGLE manipulatewheeltoggle; 
		private StopCubeController stopcubecontroller;
		private DispenseCube dispensecube;
		private liftUp liftup;
		private liftDown liftdown;


	private OI() {
		 leftJoy = new Joystick(2);
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
		 rightPad = new Joystick(0);
		  rightPad_trigger = new JoystickButton(rightPad, 1);
				rightPad_button2 = new JoystickButton(rightPad, 2);
				rightPad_button3 = new JoystickButton(rightPad, 3);
				rightPad_button4 = new JoystickButton(rightPad, 4);
				rightPad_button5 = new JoystickButton(rightPad, 5);
				rightPad_button6 = new JoystickButton(rightPad, 6);
				rightPad_button7 = new JoystickButton(rightPad, 7);
				rightPad_button8 = new JoystickButton(rightPad, 8);
				rightPad_button9 = new JoystickButton(rightPad, 9);
				rightPad_button10 = new JoystickButton(rightPad, 10);
		 leftPad = new Joystick(3);
		leftPad_trigger = new JoystickButton(leftPad, 1);
				leftPad_button2 = new JoystickButton(leftPad, 2);
				leftPad_button3 = new JoystickButton(leftPad, 3);
				leftPad_button4 = new JoystickButton(leftPad, 4);
				leftPad_button5 = new JoystickButton(leftPad, 5);
				leftPad_button6 = new JoystickButton(leftPad, 6);
				leftPad_button7 = new JoystickButton(leftPad, 7);
				leftPad_button8 = new JoystickButton(leftPad, 8);
				
				liftup = new liftUp();
				liftdown = new liftDown();
				
				// manipulator buttons (TEMP) mapped to commands
				
				rightPad_trigger.whileHeld(dispensecube);
				rightPad_button2.whenPressed(stopcubecontroller);
				rightPad_button3.whenPressed(manipulatewheeltoggle);
				rightPad_button4.whenPressed(collectcube);
				rightPad_button5.whileHeld(liftup);
				rightPad_button6.whileHeld(liftdown);
				
				
				
				
}
	public static OI getInstance () {
		return instance;
	}
	
	public Joystick getJoystickLeftDrive () {
		return leftJoy ;
	}
	
	public Joystick getJoystickRightDrive () {
		return rightJoy ;
	}
	
	public Joystick getJoypadLeftDrive () {
		return leftPad ; 
	}
	
	public Joystick getJoypadRightDrive () {
		return rightPad ;
	}
}