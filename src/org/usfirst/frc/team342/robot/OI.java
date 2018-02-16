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
import org.usfirst.frc.team342.robot.commands.drive.FastDrive;
import org.usfirst.frc.team342.robot.commands.drive.ManipulateWheelDOWN;
import org.usfirst.frc.team342.robot.commands.drive.ManipulateWheelUP;
import org.usfirst.frc.team342.robot.commands.drive.SlowDrive;

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
		private Button  
						manipulator_button1 ,
						manipulator_button2 ,
		//				manipulator_button3 ,
						manipulator_button4 ,
		//				manipulator_button5 ,
						manipulator_button6 ;
		//				manipulator_button7 ,
		//				manipulator_button8 ,
		//				manipulator_button9 ,
		//				manipulator_button10 ;
		
		private Joystick xbox_drive;
		private Button 	
		//				xbox_drive_button1 ,
		//				xbox_drive_button2 ,
		//				xbox_drive_button3 ,
		//				xbox_drive_button4 ,
						xbox_drive_button5 ,
						xbox_drive_button6 ;
		//				xbox_drive_button7 ,
		//				xbox_drive_button8 ,
		//				xbox_drive_button9 ,
		//				xbox_drive_button10 ;
		
		//Various Commands to be assigned to buttons for the driver
		private ManipulateWheelUP manipulatewheelup;
		private ManipulateWheelDOWN manipulatewheeldown;
		private SlowDrive slowdrive;
		private FastDrive fastdrive;
		
		//Various Commands to be assigned to buttons for manipulator
		private CollectCube collectcube; 
		private StopCubeController stopcubecontroller;
		private DispenseCube dispensecube;
		
		
	private OI() {
		
		xbox_drive = new Joystick(0);
	 	//	xbox_drive_button1 = new JoystickButton(xbox_drive, 1);
		//	xbox_drive_button2 = new JoystickButton(xbox_drive, 2);
		//	xbox_drive_button3 = new JoystickButton(xbox_drive, 3);
		//	xbox_drive_button4 = new JoystickButton(xbox_drive, 4);
			xbox_drive_button5 = new JoystickButton(xbox_drive, 5);
			xbox_drive_button6 = new JoystickButton(xbox_drive, 6);
		//	xbox_drive_button7 = new JoystickButton(xbox_drive, 7);
		//	xbox_drive_button8 = new JoystickButton(xbox_drive, 8);
		//	xbox_drive_button9 = new JoystickButton(xbox_drive, 9);
		//	xbox_drive_button10 = new JoystickButton(xbox_drive, 10);
			
		manipulator = new Joystick(1);
	 		manipulator_button1 = new JoystickButton(manipulator, 1);
	 		manipulator_button2 = new JoystickButton(manipulator, 2);
	 	//	manipulator_button3 = new JoystickButton(manipulator, 3);
		 	manipulator_button4 = new JoystickButton(manipulator, 4);
		// 	manipulator_button5 = new JoystickButton(manipulator, 5);
		 	manipulator_button6 = new JoystickButton(manipulator, 6);
		//	manipulator_button7 = new JoystickButton(manipulator, 7);
		// 	manipulator_button8 = new JoystickButton(manipulator, 8);
		// 	manipulator_button9 = new JoystickButton(manipulator, 9);
		// 	manipulator_button10 = new JoystickButton(manipulator, 10);
		 	
				
		//Instantiating Commands
			//Drive
				manipulatewheelup = new ManipulateWheelUP();
				manipulatewheeldown = new ManipulateWheelDOWN();
				slowdrive = new SlowDrive();
				fastdrive = new FastDrive();
				
			//Manipulator
				collectcube= new CollectCube ();
				stopcubecontroller= new StopCubeController ();
				dispensecube= new DispenseCube ();
				
				
		// Drive Controller Buttons
		xbox_drive_button5.whenPressed(manipulatewheeldown);
		xbox_drive_button5.whenReleased(manipulatewheelup);
		xbox_drive_button6.whenPressed(slowdrive);
		xbox_drive_button6.whenReleased(fastdrive);
				
		// Manipulator Buttons
		manipulator_button1.whileHeld(dispensecube);
		manipulator_button1.whenReleased(stopcubecontroller);
		manipulator_button2.whenPressed(stopcubecontroller);
		manipulator_button4.whenPressed(collectcube);
		manipulator_button6.whenPressed(slowdrive);
	
		
	}
	
	public static OI getInstance () {
		return instance;
	}
	
	/*
	public Joystick getJoystickDrive () {
		return xbox_drive ;
	}
	
	public Joystick getJoystickManipulator () {
		return manipulator ;
	}
	*/
	
	public int getManipulatorPOV () {
		return manipulator.getPOV();
	}
	
	
	// Methods to get the multiple axis on the xbox_drive Joystick
	public double getJoystickDriveLeftYAxis() {
		
		return xbox_drive.getRawAxis(RobotMap.Y_LEFT_AXIS);
	}
	
	public double getJoystickDriveLeftXAxis() {
		
		return xbox_drive.getRawAxis(RobotMap.X_LEFT_AXIS);
	}
	
	public double getJoystickDriveRightYAxis() {
	
		return xbox_drive.getRawAxis(RobotMap.Y_RIGHT_AXIS);
	}
	
	public double getJoystickDriveRightXAxis() {
		
		return xbox_drive.getRawAxis(RobotMap.X_RIGHT_AXIS);
	}
	
	
	// Methods to get the multiple axis on the manipulator Joystick
	public double getJoystickManipulatorLeftYAxis() {
		
		return manipulator.getRawAxis(RobotMap.Y_LEFT_AXIS);
	}
	
	public double getJoystickManipulatorLeftXAxis() {
		
		return manipulator.getRawAxis(RobotMap.X_LEFT_AXIS);
	}
	
	public double getJoystickManipulatorRightYAxis() {
	
		return manipulator.getRawAxis(RobotMap.Y_RIGHT_AXIS);
	}
	
	public double getJoystickManipulatorRightXAxis() {
		
		return manipulator.getRawAxis(RobotMap.X_RIGHT_AXIS);
	}

}