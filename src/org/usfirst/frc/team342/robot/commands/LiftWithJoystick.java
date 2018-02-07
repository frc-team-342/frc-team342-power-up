package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.OI;
import org.usfirst.frc.team342.robot.subsystems.LiftSystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftWithJoystick extends Command {
	private LiftSystem LiftWithJoystick;
	private OI oi;
	
	private Joystick manipulator;
	private static final double DEADZONE = 0.2;
	private static final int AXIS =1;
	private static final double SPEED = 1.0;
    public LiftWithJoystick() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	oi = OI.getInstance();
    	LiftWithJoystick=LiftSystem.getInstance();
    	requires (LiftWithJoystick);
    	manipulator=oi.getJoystickManipulator();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    if (manipulator.getRawAxis(AXIS) > DEADZONE ) {
    	LiftWithJoystick.liftUp(SPEED);
    }else if (manipulator.getRawAxis(AXIS)< DEADZONE * -1.0) {
    	LiftWithJoystick.liftDown(SPEED);
    }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	LiftWithJoystick.liftStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	LiftWithJoystick.stopAll();
    }
}
