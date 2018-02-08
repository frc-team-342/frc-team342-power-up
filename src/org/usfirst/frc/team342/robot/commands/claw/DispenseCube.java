package org.usfirst.frc.team342.robot.commands.claw;

import org.usfirst.frc.team342.robot.subsystems.CubeController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DispenseCube extends Command {

	private CubeController cube_controller;

	private static final double SPEED = 1.0;

	public DispenseCube() {

		cube_controller = CubeController.getInstance();
		requires(cube_controller);
	}

	protected void initialize() {

		SmartDashboard.putString("Cube State:", "Dispensing Cube");
	}

	protected void execute() {

		cube_controller.dispenseCube(SPEED);
	}

	@Override
	protected boolean isFinished() {

		return false;
	}

	protected void end() {

		cube_controller.closeClawAndStop();
		cube_controller.collectorStop();
	}

	@Override
	protected void interrupted() {

		cube_controller.stopAll();
	}

}
