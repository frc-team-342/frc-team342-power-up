package org.usfirst.frc.team342.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSystem extends Subsystem {

	private boolean wheel_down;
	
	private WPI_TalonSRX left_one;
	private WPI_TalonSRX left_two;
	private WPI_TalonSRX right_one;
	private WPI_TalonSRX right_two;
	private WPI_TalonSRX middle;
	
	public DriveSystem() {
		// TODO Auto-generated constructor stub
	}

	public DriveSystem(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}
	
	private void initializeDriveSystem() {
		right_one = new WPI_TalonSRX(1);
		right_two = new WPI_TalonSRX(16);
		left_one = new WPI_TalonSRX(3);
		left_two = new WPI_TalonSRX(4);
		middle = new WPI_TalonSRX(5);
		
		left_one.setInverted(true);
		left_two.setInverted(true);
		
		left_two.follow(left_one);
		right_two.follow(right_one);
		
		wheel_down = false;
	}
	
	public void drive(double X, double Y, double rot) {
			double right_oneoutput;
			double left_oneoutput;
			
		  double maxInput = Math.copySign(Math.max(Math.abs(Y), Math.abs(rot)), Y);
		  
		middle.set(X);
		
		if (Y>=0) {
			if (rot>=0) {
				left_oneoutput=maxInput;
				 right_oneoutput = Y - rot;
			}else {
				 left_oneoutput = Y + rot;
			       right_oneoutput = maxInput;
			}
		}else {
			if (rot>=0) {
				 left_oneoutput = Y + rot;
			        right_oneoutput = maxInput;
			}else {
		        left_oneoutput = maxInput;
		        right_oneoutput = Y - rot;
		      }
		    }
	}
	


	public void driveKeepHeading(double X, double Y, double rot) {
		// TODO Add Code
	}
	
	public double getGyro() {
		// TODO Add Code
		return 0.0;
	}
	
	public void resetGyro() {
		// TODO Add Code
	}
	
	public void wheelDown() {
		// TODO Add Code
		wheel_down = true;
	}
	
	public void wheelUp() {
		// TODO Add Code
		wheel_down = false;
	}
	
	public boolean getWheelDown() {
		return wheel_down;
	}
	
	public void changeFront() {
		// TODO Add Code
	}
	
	public void stopDrive() {
		// TODO Add Code
	}
	
	public void stopAll() {
		// TODO Add Code
	}

}
