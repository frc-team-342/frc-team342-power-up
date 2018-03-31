/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team342.robot;

import org.usfirst.frc.team342.robot.commands.claw.CollectCube;
import org.usfirst.frc.team342.robot.commands.claw.DispenseCube;
import org.usfirst.frc.team342.robot.commands.claw.LowerClaw;
import org.usfirst.frc.team342.robot.commands.claw.StopCubeController;
import org.usfirst.frc.team342.robot.commands.climb.ClimbDown;
import org.usfirst.frc.team342.robot.commands.climb.ClimbUp;
import org.usfirst.frc.team342.robot.commands.climb.ExtendArm;
import org.usfirst.frc.team342.robot.commands.climb.RetractArm;
import org.usfirst.frc.team342.robot.commands.drive.FastDrive;
import org.usfirst.frc.team342.robot.commands.drive.ManipulateWheelDOWN;
import org.usfirst.frc.team342.robot.commands.drive.ManipulateWheelUP;
import org.usfirst.frc.team342.robot.commands.drive.Reverse;
import org.usfirst.frc.team342.robot.commands.drive.SlowDrive;
import org.usfirst.frc.team342.robot.commands.lift.LiftToPosition;
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

		private Joystick xbox_drive;
		private Button 	
						xbox_drive_buttonA ,
		//				xbox_drive_buttonB ,
		//				xbox_drive_buttonX ,
		//				xbox_drive_buttonY ,
		//				xbox_drive_leftBumper ,
						xbox_drive_rightBumper;
		//				xbox_drive_backButton ,
		//				xbox_drive_startButton,
		//				xbox_drive_leftstickButton,
		//				xbox_drive_rightstickButton;
	
		private Joystick manipulator;
		private Button  
						manipulator_buttonA ,
						manipulator_buttonB ,
		//				manipulator_buttonX ,
						manipulator_buttonY ,
						manipulator_leftBumper ,
						manipulator_rightBumper,
						manipulator_backButton ,
						manipulator_startButton ,
		//				manipulator_leftstickButton ,
						manipulator_rightStickButton;
		
		private Joystick logitech_drive_left;
		//private Button
		//				logitech_left_button1;
		//				logitech_left_button2,
		//				logitech_left_button3,
		//				logitech_left_button4,
		//				logitech_left_button5,
		//				logitech_left_button6,
		//				logitech_left_button7,
		//				logitech_left_button8,
		//				logitech_left_button9,
		//				logitech_left_button10,
		//				logitech_left_button11,
		//				logitech_left_button12;
		
		private Joystick logitech_drive_right;
		//private Button
		//				logitech_right_button1;
		//				logitech_right_button2,
		//				logitech_right_button3,
		//				logitech_right_button4,
		//				logitech_right_button5,
		//				logitech_right_button6,
		//				logitech_right_button7,
		//				logitech_right_button8,
		//				logitech_right_button9,
		//				logitech_right_button10,
		//				logitech_right_button11,
		//				logitech_right_button12;
		
		//Various Commands to be assigned to buttons for the driver
		private ManipulateWheelUP manipulatewheelup;
		private ManipulateWheelDOWN manipulatewheeldown;
		private SlowDrive slowdrive;
		private FastDrive fastdrive;
		private Reverse reverse;
		
		//Various Commands to be assigned to buttons for manipulator
		private CollectCube collectcube; 
		private StopCubeController stopcubecontroller;
		private DispenseCube dispensecube;
		private ClimbUp climbup;
		private ClimbDown climbdown;
		private LowerClaw lowerclaw;
		private ExtendArm extendarm;
		private RetractArm retractarm;
		
		
		
	private OI() {
		
		xbox_drive = new Joystick(0);
	 		xbox_drive_buttonA = new JoystickButton(xbox_drive, 1);
		//	xbox_drive_buttonB = new JoystickButton(xbox_drive, 2);
		//	xbox_drive_buttonX = new JoystickButton(xbox_drive, 3);
		//	xbox_drive_buttonY = new JoystickButton(xbox_drive, 4);
		//	xbox_drive_leftBumper = new JoystickButton(xbox_drive, 5);
			xbox_drive_rightBumper = new JoystickButton(xbox_drive, 6);
		//	xbox_drive_backButton = new JoystickButton(xbox_drive, 7);
		//	xbox_drive_startButton new JoystickButton(xbox_drive, 8);
		//	xbox_drive_leftstickButton = new JoystickButton(xbox_drive, 9);
		//	xbox_drive_rightstickButton = new JoystickButton(xbox_drive, 10);
			
		manipulator = new Joystick(1);
	 		manipulator_buttonA = new JoystickButton(manipulator, 1);
	 		manipulator_buttonB = new JoystickButton(manipulator, 2);
	 	//	manipulator_buttonX = new JoystickButton(manipulator, 3);
		 	manipulator_buttonY = new JoystickButton(manipulator, 4);
		 	manipulator_leftBumper = new JoystickButton(manipulator, 5);
		 	manipulator_rightBumper = new JoystickButton(manipulator, 6);
			manipulator_backButton = new JoystickButton(manipulator, 7);
		 	manipulator_startButton = new JoystickButton(manipulator, 8);
		// 	manipulator_leftstickButton = new JoystickButton(manipulator, 9);
		 	manipulator_rightStickButton = new JoystickButton(manipulator, 10);
		 	
		logitech_drive_left = new Joystick(2);
		 //	logitech_left_button1 = new JoystickButton(logitech_drive_left, 1);
		 //	logitech_left_button2 = new JoystickButton(logitech_drive_left, 2);
		 //	logitech_left_button3 = new JoystickButton(logitech_drive_left, 3);
		 //	logitech_left_button4 = new JoystickButton(logitech_drive_left, 4);
		 //	logitech_left_button5 = new JoystickButton(logitech_drive_left, 5);
		 //	logitech_left_button6 = new JoystickButton(logitech_drive_left, 6);
		 //	logitech_left_button7 = new JoystickButton(logitech_drive_left, 7);
		 //	logitech_left_button8 = new JoystickButton(logitech_drive_left, 8);
		 //	logitech_left_button9 = new JoystickButton(logitech_drive_left, 9);
		 //	logitech_left_button10 = new JoystickButton(logitech_drive_left, 10);
		 //	logitech_left_button11 = new JoystickButton(logitech_drive_left, 11);
		 //	logitech_left_button12 = new JoystickButton(logitech_drive_left, 12);
		 	
		logitech_drive_right = new Joystick(3);
		 //	logitech_right_button1 = new JoystickButton(logitech_drive_right, 1);
		 //	logitech_right_button2 = new JoystickButton(logitech_drive_right, 2);
		 //	logitech_right_button3 = new JoystickButton(logitech_drive_right, 3);
		 //	logitech_right_button4 = new JoystickButton(logitech_drive_right, 4);
		 //	logitech_right_button5 = new JoystickButton(logitech_drive_right, 5);
		 //	logitech_right_button6 = new JoystickButton(logitech_drive_right, 6);
		 //	logitech_right_button7 = new JoystickButton(logitech_drive_right, 7);
		 //	logitech_right_button8 = new JoystickButton(logitech_drive_right, 8);
		 //	logitech_right_button9 = new JoystickButton(logitech_drive_right, 9);
		 //	logitech_right_button10 = new JoystickButton(logitech_drive_right, 10);
		 //	logitech_right_button11 = new JoystickButton(logitech_drive_right, 11);
		 //	logitech_right_button12 = new JoystickButton(logitech_drive_right, 12);
				
		//Instantiating Commands
			//Drive
				manipulatewheelup = new ManipulateWheelUP();
				manipulatewheeldown = new ManipulateWheelDOWN();
				slowdrive = new SlowDrive();
				fastdrive = new FastDrive();
				reverse= new Reverse();
				
			//Manipulator
				collectcube= new CollectCube ();
				stopcubecontroller= new StopCubeController ();
				dispensecube= new DispenseCube ();
				climbup = new ClimbUp ();
				climbdown = new ClimbDown ();
				lowerclaw = new LowerClaw();
				extendarm = new ExtendArm();
				retractarm = new RetractArm();
				
		// Drive Controller Buttons
		//	xbox_drive_leftBumper.whenPressed(manipulatewheeldown);
		//	xbox_drive_leftBumper.whenReleased(manipulatewheelup);
			xbox_drive_rightBumper.whenPressed(slowdrive);
			xbox_drive_rightBumper.whenReleased(fastdrive);
			xbox_drive_buttonA.whenPressed(reverse);
				
		// Manipulator Buttons
			manipulator_buttonA.whileHeld(dispensecube);
			manipulator_buttonB.whenReleased(stopcubecontroller);
			manipulator_buttonY.whenPressed(collectcube);
			manipulator_rightBumper.whileHeld(climbup);
			manipulator_leftBumper.whileHeld(climbdown);
			manipulator_backButton.whenPressed(lowerclaw);
			manipulator_rightStickButton.whenPressed(extendarm);
			manipulator_startButton.whenPressed(retractarm);
		
		
		// Logitech Left Buttons
		//	logitech_left_button1.whenPressed(manipulatewheeldown);
		//	logitech_left_button1.whenReleased(manipulatewheelup);
		
		// Logitech Right Buttons
		//	logitech_right_button1.whenPressed(slowdrive);
		//	logitech_right_button1.whenReleased(fastdrive);
			
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
	
	// Methods to get the multiple axis on the Logitech Left Joystick
		public double getJoystickLogitechLeftYAxis() {
				
			return manipulator.getRawAxis(RobotMap.Y_AXIS_LOGITECH);
		}
			
		public double getJoystickLogitechLeftXAxis() {
				
			return manipulator.getRawAxis(RobotMap.X_AXIS_LOGITECH);
		}
			
		public double getJoystickLogitechLeftZAxis() {
			
			return manipulator.getRawAxis(RobotMap.Z_AXIS_LOGITECH);
		}
			
		public double getJoystickLogitechLeftSliderAxis() {
				
			return manipulator.getRawAxis(RobotMap.SLIDER_AXIS_LOGITECH);
		}
		
	// Methods to get the multiple axis on the Logitech Left Joystick
		public double getJoystickLogitechRightYAxis() {
				
			return manipulator.getRawAxis(RobotMap.Y_AXIS_LOGITECH);
		}
			
		public double getJoystickLogitechRightXAxis() {
				
			return manipulator.getRawAxis(RobotMap.X_AXIS_LOGITECH);
		}
			
		public double getJoystickLogitechRightZAxis() {
			
			return manipulator.getRawAxis(RobotMap.Z_AXIS_LOGITECH);
		}
			
		public double getJoystickLogitechRightSliderAxis() {
				
			return manipulator.getRawAxis(RobotMap.SLIDER_AXIS_LOGITECH);
		}	

}