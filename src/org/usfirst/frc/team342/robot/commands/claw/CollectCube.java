package org.usfirst.frc.team342.robot.commands.claw;

import org.usfirst.frc.team342.robot.subsystems.CubeController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CollectCube extends Command {

	private CubeController cube_controller;

	private static final double COLLECTED_DISTANCE = 1.0;
	private static final double SPEED = 0.8;

	public CollectCube() {

		cube_controller = CubeController.getInstance();
		requires(cube_controller);
	}

	protected void initialize() {

		SmartDashboard.putString("Cube State:", "Collecting Cube");
	}

	protected void execute() {

		cube_controller.collectCube(SPEED);
	}

	@Override
	protected boolean isFinished() {

		if (cube_controller.getInfrared() >= COLLECTED_DISTANCE) {
			return true;
		} else {
			return false;
		}
	}

	protected void end() {

		cube_controller.closeClawAndStop();
		cube_controller.collectorStop();
	}

	@Override
	protected void interrupted() {

		end();
	}

}
