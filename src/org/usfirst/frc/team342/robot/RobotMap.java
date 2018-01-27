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
	
	//Analog Sensors
	public static final int INFRAREDSENSOR = 0;
	public static final int ULTRASONIC_ONE = 1;
	public static final int ULTRASONIC_TWO = 2;
	public static final int POTENTIOMETER = 3;
	
	//Lights Addresses (PWM)
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	
	//DigitalInputs
	public static final int LIFTLOWERLIMIT = 0;
	public static final int LIFTUPPERLIMIT = 1;
	
	//MotorControllers
	public static final int RIGHTMASTER = 1;
	public static final int RIGHTFOLLOW = 2;
	public static final int LEFTMASTER = 3;
	public static final int LEFTFOLLOW = 4;
	public static final int CENTERWHEEL = 5;
	public static final int INTAKEMASTER = 6;
	public static final int INTAKEFOLLOW = 7;
	public static final int LIFTMASTER = 8;
	public static final int LIFTFOLLOW = 9;
	
	//Pneumatics
	public static final int PNEUMATICWHEEL = 0;
	public static final int PNEUMATICCLAW = 1;
}
