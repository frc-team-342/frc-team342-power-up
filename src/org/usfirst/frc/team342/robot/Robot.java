/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team342.robot;


import org.usfirst.frc.team342.robot.commands.autonomous.DriveForward;
import org.usfirst.frc.team342.robot.commands.autonomous.Scale;
import org.usfirst.frc.team342.robot.commands.autonomous.Switch;
import org.usfirst.frc.team342.robot.commands.drive.DriveWithJoystick;
import org.usfirst.frc.team342.robot.subsystems.CameraVisionSystem;
import org.usfirst.frc.team342.robot.subsystems.ClimbSystem;
import org.usfirst.frc.team342.robot.subsystems.CubeController;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;
import org.usfirst.frc.team342.robot.subsystems.LiftSystem;
import org.usfirst.frc.team342.robot.subsystems.LightsSubsystem;
import org.usfirst.frc.team342.robot.subsystems.PneumaticsResourceSystem;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	
	private static OI oi;
	private static CameraVisionSystem cameravisionsystem;
	private static ClimbSystem climbsystem;
	private static CubeController cubecontroller;
	private static DriveSystem drivesystem;
	private static LiftSystem liftsystem;
	private static LightsSubsystem lightssubsystem;
	private static PneumaticsResourceSystem pneumaticsresourcesystem;
	private static DriveWithJoystick drivewithjoystick;
	
	private Switch switchauto;
	private Scale scaleauto;
	private DriveForward driveforwardauto;
	
	private String gamedata;
	
	private static final String AUTO_MESSAGE_SWITCH_LEFT = "Attempting Switch On The Left From The Left Position.";
	private static final String AUTO_MESSAGE_SWITCH_CENTER_LEFT = "Attempting Switch On The Left From The Center Position.";
	private static final String AUTO_MESSAGE_SWITCH_CENTER_RIGHT = "Attempting Switch On The Right From The Center Position.";
	private static final String AUTO_MESSAGE_SWITCH_RIGHT = "Attempting Switch On The Right From The Right Position.";
	
	private static final String AUTO_MESSAGE_SCALE_LEFT = "Attempting Scale On The Left From The Left Position";
	private static final String AUTO_MESSAGE_SCALE_RIGHT = "Attempting Scale On The Right From The Right Position";
	
	private static final String AUTO_MESSAGE_DRIVE_FORWARD_LEFT = "Attempting To Drive Forward From The Left Position";
	private static final String AUTO_MESSAGE_DRIVE_FORWARD_CENTER = "Attempting To Drive Forward From The Center Position";
	private static final String AUTO_MESSAGE_DRIVE_FORWARD_RIGHT = "Attempting To Drive Forward From The Right Position";
	
	private static final String AUTO_MESSAGE_FAILURE = "THIS MESSAGE SHOULD NOT APPEAR, IF IT DOES AUTONOMOUS LOGIC IS BROKE";
	
	
	
	private static final int LEFT = 1;
	private static final int CENTER = 2;
	private static final int RIGHT = 3;
	private static final int SWITCH = 1;
	private static final int SCALE = 2;
	private static final int DRIVE_FORWARD = 3;
	
	SendableChooser<Integer> location = new SendableChooser<>();
	SendableChooser<Integer> action = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		oi = OI.getInstance();
		cameravisionsystem = CameraVisionSystem.getInstance();
		climbsystem = ClimbSystem.getInstance();
		cubecontroller = CubeController.getInstance();
		drivesystem = DriveSystem.getInstance();
		liftsystem = LiftSystem.getInstance();
		lightssubsystem = LightsSubsystem.getInstance();
		pneumaticsresourcesystem = PneumaticsResourceSystem.getInstance();
		drivewithjoystick = new DriveWithJoystick();
		
		location.addDefault("Left", LEFT);
		location.addObject("Center", CENTER);
		location.addObject("Right", RIGHT);
		
		action.addDefault("Switch", SWITCH);
		action.addDefault("Scale", SCALE);
		action.addDefault("Drive Forward", DRIVE_FORWARD);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		
		gamedata = DriverStation.getInstance().getGameSpecificMessage();
		
		SmartDashboard.putString("Game Message: ", gamedata);
		
		//This is used at the end of auto initialize and is used to determine what autonomous we should run
		int whattorun = 0;
		
		//Position on the Left
		if(location.getSelected() == 1) {
			
			//Switch is selected
			if(action.getSelected() == 1) {
				
				//If the switch is on the left
				if(gamedata.charAt(0) == 'L') {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SWITCH_LEFT);
					switchauto = new Switch('L', gamedata.charAt(0));
					whattorun = 1;
					
				//If the switch is not on the left but the scale is
				}else if(gamedata.charAt(0) != 'L' && gamedata.charAt(1) == 'L') {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SCALE_LEFT);
					scaleauto = new Scale('L');
					whattorun = 2;
					
				//If all else fails
				}else {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_LEFT);
					driveforwardauto = new DriveForward('L');
					whattorun = 3;
					
				}
			
			//Scale is selected
			}else if(action.getSelected() == 2) {
				
				//If the scale is on the left
				if(gamedata.charAt(1) == 'L') {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SCALE_LEFT);
					scaleauto = new Scale('L');
					whattorun = 2;
					
				//If the scale is not on the left but the switch is
				}else if(gamedata.charAt(1) != 'L' && gamedata.charAt(0) == 'L') {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SWITCH_LEFT);
					switchauto = new Switch('L', gamedata.charAt(0));
					whattorun = 1;
					
				//If all else fails
				}else {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_LEFT);
					driveforwardauto = new DriveForward('L');
					whattorun = 3;
					
				}
				
			//Drive Forward is selected
			}else if(action.getSelected() == 3) {
				
				SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_LEFT);
				driveforwardauto = new DriveForward('L');
				whattorun = 3;
				
			}
		
		//Position in the Center
		}else if(location.getSelected() == 2) {
			
			//Switch is selected
			if(action.getSelected() == 1) {
				
				//If the switch is on the left
				if(gamedata.charAt(0) == 'L') {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SWITCH_CENTER_LEFT);
					switchauto = new Switch('C', gamedata.charAt(0));
					whattorun = 1;
					
				//If the switch is on the right
				}else if(gamedata.charAt(0) == 'R') {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SWITCH_CENTER_RIGHT);
					switchauto = new Switch('C', gamedata.charAt(0));
					whattorun = 1;
					
				//If all else fails
				}else {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_CENTER);
					driveforwardauto = new DriveForward('C');
					whattorun = 3;
					
				}
			
			//Scale is selected
			}else if(action.getSelected() == 2) {
				
				//SCALE IS NOT PROGRAMMED TO BE ACCOMPLISHED WHEN WE ARE IN THE CENTER POSITION
				
				SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_CENTER);
				driveforwardauto = new DriveForward('C');
				whattorun = 3;
				
			//Drive Forward is selected
			}else if(action.getSelected() == 3) {
				
				SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_CENTER);
				driveforwardauto = new DriveForward('C');
				whattorun = 3;
				
			}
			
		//Position on the Right
		}else if(location.getSelected() == 3) {
			
			//Switch is selected
			if(action.getSelected() == 1) {
				
				//If the switch is on the left
				if(gamedata.charAt(0) == 'R') {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SWITCH_RIGHT);
					switchauto = new Switch('R', gamedata.charAt(0));
					whattorun = 1;
					
				//If the switch is not on the left but the scale is
				}else if(gamedata.charAt(0) != 'R' && gamedata.charAt(1) == 'R') {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SCALE_RIGHT);
					scaleauto = new Scale('R');
					whattorun = 2;
					
				//If all else fails
				}else {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_RIGHT);
					driveforwardauto = new DriveForward('R');
					whattorun = 3;
					
				}
			
			//Scale is selected
			}else if(action.getSelected() == 2) {
				
				//If the scale is on the left
				if(gamedata.charAt(1) == 'R') {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SCALE_RIGHT);
					scaleauto = new Scale('R');
					whattorun = 2;
					
				//If the scale is not on the left but the switch is
				}else if(gamedata.charAt(1) != 'R' && gamedata.charAt(0) == 'R') {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SWITCH_RIGHT);
					switchauto = new Switch('R', gamedata.charAt(0));
					whattorun = 1;
					
				//If all else fails
				}else {
					
					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_RIGHT);
					driveforwardauto = new DriveForward('R');
					whattorun = 3;
					
				}
				
			//Drive Forward is selected
			}else if(action.getSelected() == 3) {
				
				SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_RIGHT);
				driveforwardauto = new DriveForward('R');
				whattorun = 3;
				
			}
		}
		
		if(whattorun == 1) {
			switchauto.start();
		}else if (whattorun == 2) {
			scaleauto.start();
		}else if(whattorun == 3) {
			driveforwardauto.start();
		}else {
			SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_FAILURE);
		}
		
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		
		drivewithjoystick.start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
