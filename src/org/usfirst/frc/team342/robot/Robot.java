/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team342.robot;

import org.usfirst.frc.team342.robot.commands.autonomous.AutoChooser;
import org.usfirst.frc.team342.robot.commands.drive.DriveWithJoystick;
import org.usfirst.frc.team342.robot.commands.drive.DriveWithLogitech;
import org.usfirst.frc.team342.robot.commands.lift.LiftWithJoystick;
import org.usfirst.frc.team342.robot.subsystems.CameraVisionSystem;
import org.usfirst.frc.team342.robot.subsystems.ClimbSystem;
import org.usfirst.frc.team342.robot.subsystems.CubeController;
import org.usfirst.frc.team342.robot.subsystems.DriveSystem;
import org.usfirst.frc.team342.robot.subsystems.LiftSystem;
import org.usfirst.frc.team342.robot.subsystems.LightsSubsystem;

import edu.wpi.cscore.VideoMode.PixelFormat;
import edu.wpi.first.wpilibj.CameraServer;
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
	private static DriveWithJoystick drivewithjoystick;
	private static LiftWithJoystick liftwithjoystick;
	private static DriveWithLogitech drivewithlogitech;

	private AutoChooser chooser;

	private static final String AUTO_MESSAGE_FAILURE = "THIS MESSAGE SHOULD NOT APPEAR, IF IT DOES AUTONOMOUS LOGIC IS BROKE";

	private String gamedata;

	private static final int LEFT = 1;
	private static final int CENTER = 2;
	private static final int RIGHT = 3;
	private static final int SWITCH = 1;
	private static final int SCALE = 2;
	private static final int DRIVE_FORWARD = 3;

	SendableChooser<Integer> location = new SendableChooser<>();
	SendableChooser<Integer> action = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be used
	 * for any initialization code.
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
		drivewithjoystick = new DriveWithJoystick();
		liftwithjoystick = new LiftWithJoystick();
		drivewithlogitech = new DriveWithLogitech();

		chooser = new AutoChooser();

		location.addDefault("Left", LEFT);
		location.addObject("Center", CENTER);
		location.addObject("Right", RIGHT);

		action.addDefault("Switch", SWITCH);
		action.addDefault("Scale", SCALE);
		action.addDefault("Drive Forward", DRIVE_FORWARD);

		SmartDashboard.putData("Location: ", location);
		SmartDashboard.putData("Auto Action: ", action);
		
		SmartDashboard.putData("Gyro: ", drivesystem.getNavX());
		
		CameraServer.getInstance().startAutomaticCapture().setVideoMode(PixelFormat.kMJPEG, 320, 240, 30);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode. You
	 * can use it to reset any subsystem information you want to clear when the
	 * robot is disabled.
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
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		gamedata = DriverStation.getInstance().getGameSpecificMessage();

		int whattodo = chooser.calculateWhatToDo(gamedata, location.getSelected(), action.getSelected());
		// He's not the messiah, he's a very naughty boy!
		if (whattodo == 1) {
			chooser.switchauto.start();
		} else if (whattodo == 2) {
			chooser.scaleauto.start();
		} else if (whattodo == 3) {
			chooser.driveforwardauto.start();
		} else {
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
		liftwithjoystick.start();
		//drivewithlogitech.start();
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		
		double[] values = liftsystem.getLiftEncoder();
		
		SmartDashboard.putNumber("getSelectedSensorPosition", values[0]);
		SmartDashboard.putNumber("AnalogIn", values[1]);
		SmartDashboard.putNumber("AnalogInRaw", values[2]);
		SmartDashboard.putNumber("PulseWidthPosition", values[3]);
		SmartDashboard.putNumber("QuadraturePosition", values[4]);
		
		double[] values1 = liftsystem.getLiftEncoderS();
		
		SmartDashboard.putNumber("getSelectedSensorPosition - s", values1[0]);
		SmartDashboard.putNumber("AnalogIn - s", values1[1]);
		SmartDashboard.putNumber("AnalogInRaw - s", values1[2]);
		SmartDashboard.putNumber("PulseWidthPosition - s", values1[3]);
		SmartDashboard.putNumber("QuadraturePosition - s", values1[4]);
		Scheduler.getInstance().run();
	
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}
}
