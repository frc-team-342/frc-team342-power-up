/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team342.robot;

	/**
	 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Joystick Axis
	public static final int X_LEFT_AXIS = 0;
	public static final int Y_LEFT_AXIS = 1;
	public static final int X_RIGHT_AXIS = 4;
	public static final int Y_RIGHT_AXIS = 5;
	public static final int X_AXIS_LOGITECH = 0;
	public static final int Y_AXIS_LOGITECH = 1;
	public static final int Z_AXIS_LOGITECH = 2;
	public static final int SLIDER_AXIS_LOGITECH = 3;
	
	//Logitech Axis
	public static final int X_AXIS = 0;
	public static final int Y_AXIS = 1;
	
	// Analog Sensors
	// ULTRASONIC_ONE: sensors that detect distance
	//INFRAREDSENSOR: to detect if we have a power cube in the claw at the moment
	public static final int INFRAREDSENSOR = 0;
	public static final int ULTRASONIC_ONE = 1;
	public static final int ULTRASONIC_TWO = 2;

	// Lights Addresses (PWM)
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int TWELVEVOLTSONE = 3;
	public static final int TWELVEVOLTSTWO = 4;
	public static final int FIVEVOLTS = 5;

	// MotorControllers
		/*RIGHTMASTER & RIGHTFOLLOW: drives the right side of the robot
		 *LEFTMASTER & LEFTFOLLOW: drives the left side of the robot
		 *CENTERWHEEL: allows us to go side to side with the wheel in the center
		 *INTAKEMASTER & INTAKEFOLLOW: brings in the power cubes with extra power 
		 *LIFTMASTER & LIFTFOLLOW: brings the power cubes up and down 
		 *LIFTMASTER & LIFTFOLLOW: prevent elevator from going to far up or down
		 */	
	
		// Pratice Bot IDs
		/*	
		public static final int RIGHTMASTER = 13;
		public static final int RIGHTFOLLOW = 8;
		public static final int LEFTMASTER = 19;
		public static final int LEFTFOLLOW = 21;
		public static final int CENTERWHEEL = 15;
		public static final int CLIMBMASTER = 18;
		public static final int CLIMBFOLLOW = 20;
		public static final int LIFTMASTER = 22;
		public static final int LIFTFOLLOW = 14;
		public static final int INTAKEMASTER = 0;
		public static final int INTAKEFOLLOW = 1;
		*/
		
		// Competition Bot IDs
		public static final int RIGHTMASTER = 1;
		public static final int RIGHTFOLLOW = 2;
		public static final int LEFTMASTER = 3;
		public static final int LEFTFOLLOW = 4;
		public static final int CENTERWHEEL = 5;
		public static final int CLIMBMASTER = 6;
		public static final int CLIMBFOLLOW = 7;
		public static final int LIFTMASTER = 8;
		public static final int LIFTFOLLOW = 9;
		public static final int INTAKEMASTER = 0;
		public static final int INTAKEFOLLOW = 1;
		
	
	// Pneumatics
		/*PNEUMATICWHEEL_DOWN: brings center wheel down 
		PNEUMATICWHEEL_UP: brings center wheel up
		PNEUMATICCLAW_OPEN: opens the claw to allow the power cube in 
		PNEUMATICCLAW_CLOSED:closes the claw the prevent the power cube from leaving 
		*/
	public static final int PNEUMATICWHEEL_DOWN = 0;
	public static final int PNEUMATICWHEEL_UP = 1;
	public static final int PNEUMATICCLAW_OPEN = 2;
	public static final int PNEUMATICCLAW_CLOSED = 3;
	public static final int PNEUMATICCLAW_RELEASE_OPEN = 4;
	public static final int PNEUMATICCLIMB_EXTEND = 5;
	public static final int PNEUMATICCLIMB_RETRACT = 6;
	
	// CANifier
	public static final int CANIFIER = 14;

}
