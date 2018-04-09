package org.usfirst.frc.team342.robot.subsystems;

import org.usfirst.frc.team342.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSystem extends Subsystem {

	// Instance of the class.
	private static final DriveSystem INSTANCE = new DriveSystem();

	// Motor Controllers.
	private TalonSRX leftMaster;
	private TalonSRX leftFollow_1;
	private TalonSRX leftFollow_2;
	private TalonSRX leftFollow_3;
	private TalonSRX rightMaster;
	private TalonSRX rightFollow_1;
	private TalonSRX rightFollow_2;
	private TalonSRX rightFollow_3;
	//private TalonSRX centerWheel;

	// Pneumatic Solenoid.
	private DoubleSolenoid pneumaticSuspension;

	// Ultrasonic Sensors.
	private AnalogInput ultrasonicOne;
	private AnalogInput ultrasonicTwo;

	// NavX.
	private AHRS navx;

	// Booleans for controlling the robot.
	private boolean front;
	private boolean slow;

	// Current variables.
	private static final int AMPS = 35;
	private static final int TIMEOUT_MS = 10;
	private static final int PEAK_DURATION = 200;
	private static final int AMPSCENTER = 35;
	private static final double RAMP_TIME = 0.2;
	private static final double SLOW_DOWN = 2.0;
	private static final int ZERO = 0;
	
	// Constants for the PID loop
	private static final double P = 0.01;
	private static final double I = 0.0;
	private static final double D = 0.0;
	private static final double F = 0.25;
	
	// Scale factor for calculating autonomous
	private static final double SCALE_FACTOR = (1 / 4096);

	public DriveSystem() {

		initializeDriveSystem();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	public static DriveSystem getInstance() {

		return INSTANCE;
	}

	private void initializeDriveSystem() {

		// Configures various booleans for drive.
		// EX: If slow is true, the robot goes slow.
		front = true;
		slow = false;

		// Instantiate Motor Controllers, Sensors, Pneumatics, and the NavX.
		leftMaster = new TalonSRX(RobotMap.LEFTMASTER);
		leftFollow_1 = new TalonSRX(RobotMap.LEFTFOLLOW_1);
		leftFollow_2 = new TalonSRX(RobotMap.LEFTFOLLOW_2);
		leftFollow_3 = new TalonSRX(RobotMap.LEFTFOLLOW_3);
		rightMaster = new TalonSRX(RobotMap.RIGHTMASTER);
		rightFollow_1 = new TalonSRX(RobotMap.RIGHTFOLLOW_1);
		rightFollow_2 = new TalonSRX(RobotMap.RIGHTFOLLOW_2);
		rightFollow_3 = new TalonSRX(RobotMap.RIGHTFOLLOW_3);
	//	centerWheel = new TalonSRX(RobotMap.CENTERWHEEL);
		pneumaticSuspension = new DoubleSolenoid(RobotMap.PNEUMATICWHEEL_UP, RobotMap.PNEUMATICWHEEL_DOWN);
		ultrasonicOne = new AnalogInput(RobotMap.ULTRASONIC_ONE);
		ultrasonicTwo = new AnalogInput(RobotMap.ULTRASONIC_TWO);
		navx = new AHRS(SPI.Port.kMXP);

		// Inverts the right set of motors to make sure the robot drives in the right
		// direction.
		leftMaster.setInverted(true);
		leftFollow_1.setInverted(true);
		leftFollow_2.setInverted(true);
		leftFollow_3.setInverted(true);

		// Configures the encoder onto the master motor controllers.
		leftMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		rightMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);

		// Controls the current. To change any of these change the constants at the top
		// of the class.
		leftMaster.configPeakCurrentLimit(ZERO, ZERO);
		leftMaster.configPeakCurrentDuration(ZERO, ZERO);
		leftMaster.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		leftMaster.enableCurrentLimit(true);

		leftFollow_1.configPeakCurrentLimit(ZERO, ZERO);
		leftFollow_1.configPeakCurrentDuration(ZERO, ZERO);
		leftFollow_1.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		leftFollow_1.enableCurrentLimit(true);
		
		leftFollow_2.configPeakCurrentLimit(ZERO, ZERO);
		leftFollow_2.configPeakCurrentDuration(ZERO, ZERO);
		leftFollow_2.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		leftFollow_2.enableCurrentLimit(true);
		
		leftFollow_3.configPeakCurrentLimit(ZERO, ZERO);
		leftFollow_3.configPeakCurrentDuration(ZERO, ZERO);
		leftFollow_3.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		leftFollow_3.enableCurrentLimit(true);
		
		rightMaster.configPeakCurrentLimit(ZERO, ZERO);
		rightMaster.configPeakCurrentDuration(ZERO, ZERO);
		rightMaster.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		rightMaster.enableCurrentLimit(true);

		rightFollow_1.configPeakCurrentLimit(ZERO, ZERO);
		rightFollow_1.configPeakCurrentDuration(ZERO, ZERO);
		rightFollow_1.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		rightFollow_1.enableCurrentLimit(true);

		rightFollow_2.configPeakCurrentLimit(ZERO, ZERO);
		rightFollow_2.configPeakCurrentDuration(ZERO, ZERO);
		rightFollow_2.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		rightFollow_2.enableCurrentLimit(true);

		rightFollow_3.configPeakCurrentLimit(ZERO, ZERO);
		rightFollow_3.configPeakCurrentDuration(ZERO, ZERO);
		rightFollow_3.configContinuousCurrentLimit(AMPS, TIMEOUT_MS);
		rightFollow_3.enableCurrentLimit(true);
	
		// Configures the open loop ramp to set the motor to ramp up to speed after a
		// specified time other than jerking to full speed.
		leftMaster.configOpenloopRamp(RAMP_TIME, 0);
		leftFollow_1.configOpenloopRamp(RAMP_TIME, 0);
		leftFollow_2.configOpenloopRamp(RAMP_TIME, 0);
		leftFollow_3.configOpenloopRamp(RAMP_TIME, 0);
		rightMaster.configOpenloopRamp(RAMP_TIME, 0);
		rightFollow_1.configOpenloopRamp(RAMP_TIME, 0);
		rightFollow_2.configOpenloopRamp(RAMP_TIME, 0);
		rightFollow_3.configOpenloopRamp(RAMP_TIME, 0);

		// Controls the current of the center wheel. To change any of these change the
		// constants at the top of the class.
		//	centerWheel.configPeakCurrentLimit(AMPSCENTER, TIMEOUT_MS);
		//	centerWheel.configPeakCurrentDuration(PEAK_DURATION, TIMEOUT_MS);
		//	centerWheel.configContinuousCurrentLimit(AMPSCENTER, TIMEOUT_MS);
		//	centerWheel.enableCurrentLimit(true);
		
		// Setting the PID loop for the master controllers
		rightMaster.config_kP(0, P, TIMEOUT_MS);
		rightMaster.config_kI(0, I, TIMEOUT_MS);
		rightMaster.config_kD(0, D, TIMEOUT_MS);
		rightMaster.config_kF(0, F, TIMEOUT_MS);
		
		leftMaster.config_kP(0, P, TIMEOUT_MS);
		leftMaster.config_kI(0, I, TIMEOUT_MS);
		leftMaster.config_kD(0, D, TIMEOUT_MS);
		leftMaster.config_kF(0, F, TIMEOUT_MS);
		
		leftMaster.set(ControlMode.PercentOutput, 0.0);
		leftFollow_1.set(ControlMode.PercentOutput, 0.0);
		leftFollow_2.set(ControlMode.PercentOutput, 0.0);
		leftFollow_3.set(ControlMode.PercentOutput, 0.0);
		leftFollow_1.follow(leftMaster);
		leftFollow_2.follow(leftMaster);
		leftFollow_3.follow(leftMaster);
		
		rightMaster.set(ControlMode.PercentOutput, 0.0);
		rightFollow_1.set(ControlMode.PercentOutput, 0.0);
		rightFollow_2.set(ControlMode.PercentOutput, 0.0);
		rightFollow_3.set(ControlMode.PercentOutput, 0.0);
		rightFollow_1.follow(rightMaster);
		rightFollow_2.follow(rightMaster);
		rightFollow_3.follow(rightMaster);
	}

	public void setslow(boolean slow) {

		this.slow = slow;
	}

	public void drive(double Left_Speed, double Right_Speed, double Center_Speed) {

		// Check if the slow boolean is set to true to cut the robot's speed in half.
		if (slow) {

			Left_Speed /= SLOW_DOWN;
			Right_Speed /= SLOW_DOWN; 
		}

		// Check if the front boolean is false to change the direction of the Inputs.
		if (front) {

			Left_Speed = Left_Speed * -1.0;
			Right_Speed = Right_Speed * -1.0;
			Center_Speed = Center_Speed * -1.0;
		}else {
			double temp;
			
			temp = Left_Speed;
			Left_Speed = Right_Speed;
			Right_Speed = temp;
		}
		
		rightMaster.set(ControlMode.PercentOutput, Right_Speed);
		leftMaster.set(ControlMode.PercentOutput, Left_Speed);

	//	centerWheel.set(ControlMode.PercentOutput, Center_Speed);
	}
	
	public void driveSetSpeed(double Left_Speed, double Right_Speed) {
		
		if (slow) {
			
			Right_Speed /= SLOW_DOWN;
			Left_Speed /= SLOW_DOWN;
		}
		
		if (front) {

			Right_Speed *= -1.0;
			Left_Speed *= -1.0;
		}
		
		// argument is in position change per 100ms
		rightMaster.set(ControlMode.Velocity, Right_Speed);
		leftMaster.set(ControlMode.Velocity, Left_Speed);
	}

	public double getGyro(boolean backwards) {
		
		double angle;
		
		if(backwards) {
			angle = (((((navx.getAngle() + 180)) % 360) + 360) % 360);
		} else {
			angle = ((((navx.getAngle()) % 360) + 360) % 360);
		}
		
		return angle;
	}
	
	public AHRS getNavX() {
		
		return navx;
	}

	public void resetGyro() {

		navx.reset();
	}

	public void wheelDown() {

		pneumaticSuspension.set(Value.kForward);
	}

	public void wheelUp() {

		pneumaticSuspension.set(Value.kReverse);
	}

	public boolean getWheelState() {

		if (pneumaticSuspension.get().equals(Value.kReverse)) {
			return true;
		} else {
			return false;
		}
	}

	public int getLeftMasterEncoder() {

		return leftMaster.getSensorCollection().getPulseWidthPosition();
	}

	public int getRightMasterEncoder() {

		return rightMaster.getSensorCollection().getPulseWidthPosition();
	}

	public double getUltrasonicOne() {

		// \/ Is this the right method to use to get an analog output? \/
		return ultrasonicOne.getAverageVoltage();
	}

	public double getUltrasonicTwo() {

		// \/ Is this the right method to use to get an analog output? \/
		return ultrasonicTwo.getAverageVoltage();
	}

	public void changeFront() {

		front = !front;
	}

	public boolean getFront() {

		return front;
	}

	public boolean getSlow() {
		
		return slow;
	}
	
	public double getDistance(double encoder) {

		return encoder * SCALE_FACTOR;
	}
	
	public void stopDrive() {

		rightMaster.set(ControlMode.PercentOutput, 0.0);
		leftMaster.set(ControlMode.PercentOutput, 0.0);
	//	centerWheel.set(ControlMode.PercentOutput, 0.0);
	}

	

}
