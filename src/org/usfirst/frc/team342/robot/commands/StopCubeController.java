package org.usfirst.frc.team342.robot.commands;

import org.usfirst.frc.team342.robot.subsystems.CubeController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StopCubeController extends Command {

	private CubeController cube_controller;

	public StopCubeController() {
		
		cube_controller = CubeController.getInstance();
		requires(cube_controller);
	}

	protected void initialize() {
		SmartDashboard.putString("Cube State:", "Stopping Cube Collector");
	}

	protected void execute() {
		cube_controller.collectorStop();
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		cube_controller.collectorStop();
	}

	@Override
	protected void interrupted() {
		cube_controller.stopAll();
	}

}
