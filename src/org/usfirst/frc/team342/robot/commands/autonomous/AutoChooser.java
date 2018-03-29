package org.usfirst.frc.team342.robot.commands.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoChooser {

	private static final String AUTO_MESSAGE_SWITCH_LEFT = "Attempting Switch On The Left From The Left Position.";
	private static final String AUTO_MESSAGE_SWITCH_CENTER_LEFT = "Attempting Switch On The Left From The Center Position.";
	private static final String AUTO_MESSAGE_SWITCH_CENTER_RIGHT = "Attempting Switch On The Right From The Center Position.";
	private static final String AUTO_MESSAGE_SWITCH_RIGHT = "Attempting Switch On The Right From The Right Position.";

	private static final String AUTO_MESSAGE_SCALE_LEFT = "Attempting Scale On The Left From The Left Position";
	private static final String AUTO_MESSAGE_SCALE_RIGHT = "Attempting Scale On The Right From The Right Position";

	private static final String AUTO_MESSAGE_DRIVE_FORWARD_LEFT = "Attempting To Drive Forward From The Left Position";
	private static final String AUTO_MESSAGE_DRIVE_FORWARD_CENTER = "Attempting To Drive Forward From The Center Position";
	private static final String AUTO_MESSAGE_DRIVE_FORWARD_RIGHT = "Attempting To Drive Forward From The Right Position";

	public Scale scaleauto;
	public Switch switchauto;
	public DriveForward driveforwardauto;

	public AutoChooser() {
	}

	public int calculateWhatToDo(String gamedata, int location, int action, boolean second_cube) {

		SmartDashboard.putString("Game Message: ", gamedata);

		// This is used at the end of auto initialize and is used to determine what autonomous we should run
		int whattorun = 0;

		// Position on the Left
		if (location == 1) {

			// Switch is selected
			if (action == 1) {

				// If the switch is on the left
				if (gamedata.charAt(0) == 'L') {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SWITCH_LEFT);
					switchauto = new Switch('L', gamedata.charAt(0), second_cube);
					whattorun = 1;

					// If the switch is not on the left but the scale is
				}else if (gamedata.charAt(0) != 'L' && gamedata.charAt(1) == 'L') {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SCALE_LEFT);
					scaleauto = new Scale('L', second_cube);
					whattorun = 2;

					// If all else fails
				}else {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_LEFT);
					driveforwardauto = new DriveForward('L');
					whattorun = 3;

				}

				// Scale is selected
			} else if (action == 2) {

				// If the scale is on the left
				if (gamedata.charAt(1) == 'L') {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SCALE_LEFT);
					scaleauto = new Scale('L', second_cube);
					whattorun = 2;

					// If the scale is not on the left but the switch is
				} else if (gamedata.charAt(1) != 'L' && gamedata.charAt(0) == 'L') {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SWITCH_LEFT);
					switchauto = new Switch('L', gamedata.charAt(0), second_cube);
					whattorun = 1;

					// If all else fails
				} else {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_LEFT);
					driveforwardauto = new DriveForward('L');
					whattorun = 3;

				}

				// Drive Forward is selected
			} else if (action == 3) {

				SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_LEFT);
				driveforwardauto = new DriveForward('L');
				whattorun = 3;

			}

			// Position in the Center
		} else if (location == 2) {

			// Switch is selected
			if (action == 1) {

				// If the switch is on the left
				if (gamedata.charAt(0) == 'L') {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SWITCH_CENTER_LEFT);
					switchauto = new Switch('C', gamedata.charAt(0), second_cube);
					whattorun = 1;

					// If the switch is on the right
				} else if (gamedata.charAt(0) == 'R') {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SWITCH_CENTER_RIGHT);
					switchauto = new Switch('C', gamedata.charAt(0), second_cube);
					whattorun = 1;

					// If all else fails
				} else {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_CENTER);
					driveforwardauto = new DriveForward('C');
					whattorun = 3;

				}

				// Scale is selected
			} else if (action == 2) {

				// SCALE IS NOT PROGRAMMED TO BE ACCOMPLISHED WHEN WE ARE IN THE CENTER POSITION

				SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_CENTER);
				driveforwardauto = new DriveForward('C');
				whattorun = 3;

				// Drive Forward is selected
			} else if (action == 3) {

				SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_CENTER);
				driveforwardauto = new DriveForward('C');
				whattorun = 3;

			}

			// Position on the Right
		} else if (location == 3) {

			// Switch is selected
			if (action == 1) {

					// If the switch is on the left
				if (gamedata.charAt(0) == 'R') {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SWITCH_RIGHT);
					switchauto = new Switch('R', gamedata.charAt(0), second_cube);
					whattorun = 1;

					// If the switch is not on the left but the scale is
				} else if (gamedata.charAt(0) != 'R' && gamedata.charAt(1) == 'R') {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SCALE_RIGHT);
					scaleauto = new Scale('R', second_cube);
					whattorun = 2;

					// If all else fails
				}else {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_RIGHT);
					driveforwardauto = new DriveForward('R');
					whattorun = 3;

				}

				// Scale is selected
			} else if (action == 2) {

				// If the scale is on the left
				if (gamedata.charAt(1) == 'R') {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SCALE_RIGHT);
					scaleauto = new Scale('R', second_cube);
					whattorun = 2;

					// If the scale is not on the left but the switch is
				} else if (gamedata.charAt(1) != 'R' && gamedata.charAt(0) == 'R') {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_SWITCH_RIGHT);
					switchauto = new Switch('R', gamedata.charAt(0), second_cube);
					whattorun = 1;

					// If all else fails
				} else {

					SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_RIGHT);
					driveforwardauto = new DriveForward('R');
					whattorun = 3;

				}

				// Drive Forward is selected
			} else if (action == 3) {

				SmartDashboard.putString("Autonomous Status: ", AUTO_MESSAGE_DRIVE_FORWARD_RIGHT);
				driveforwardauto = new DriveForward('R');
				whattorun = 3;

			}
		}

		return whattorun;
	}

}
