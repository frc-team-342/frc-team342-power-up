/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team342.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private static final OI instance = new OI ();
	 // Create the joystick and the 6 buttons on it
		Joystick leftJoy = new Joystick(1);
		 Button leftJoy_trigger = new JoystickButton(leftJoy, 1),
				leftJoy_button2 = new JoystickButton(leftJoy, 2),
				leftJoy_button3 = new JoystickButton(leftJoy, 3),
				leftJoy_button4 = new JoystickButton(leftJoy, 4),
				leftJoy_button5 = new JoystickButton(leftJoy, 5),
				leftJoy_button6 = new JoystickButton(leftJoy, 6),
				leftJoy_button7 = new JoystickButton(leftJoy, 7),
				leftJoy_button8 = new JoystickButton(leftJoy, 8);
		Joystick rightJoy = new Joystick(2);
		 Button rightJoy_trigger = new JoystickButton(rightJoy, 1),
				rightJoy_button2 = new JoystickButton(rightJoy, 2),
				rightJoy_button3 = new JoystickButton(rightJoy, 3),
				rightJoy_button4 = new JoystickButton(rightJoy, 4),
				rightJoy_button5 = new JoystickButton(rightJoy, 5),
				rightJoy_button6 = new JoystickButton(rightJoy, 6),
				rightJoy_button7 = new JoystickButton(rightJoy, 7),
				rightJoy_button8 = new JoystickButton(rightJoy, 8);
		Joystick rightPad = new Joystick(3);
		Button  rightPad_trigger = new JoystickButton(rightPad, 1),
				rightPad_button2 = new JoystickButton(rightPad, 2),
				rightPad_button3 = new JoystickButton(rightPad, 3),
				rightPad_button4 = new JoystickButton(rightPad, 4),
				rightPad_button5 = new JoystickButton(rightPad, 5),
				rightPad_button6 = new JoystickButton(rightPad, 6),
				rightPad_button7 = new JoystickButton(rightPad, 7),
				rightPad_button8 = new JoystickButton(rightPad, 8);
		Joystick leftPad = new Joystick(4);
		Button leftPad_trigger = new JoystickButton(leftPad, 1),
				leftPad_button2 = new JoystickButton(leftPad, 2),
				leftPad_button3 = new JoystickButton(leftPad, 3),
				leftPad_button4 = new JoystickButton(leftPad, 4),
				leftPad_button5 = new JoystickButton(leftPad, 5),
				leftPad_button6 = new JoystickButton(leftPad, 6),
				leftPad_button7 = new JoystickButton(leftPad, 7),
				leftPad_button8 = new JoystickButton(leftPad, 8);

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	private OI() {
		
		
		//drive
		

		//cube controller
		
		//climb
		
		//pneumatics
		
		//lift system
}
	public static OI getInstance () {
		return instance;
	}
}