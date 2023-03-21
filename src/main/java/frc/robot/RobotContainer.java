package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.lib.math.Conversions;
import frc.robot.autos.pathplanner;
import frc.robot.commands.BalanceCommand;
import frc.robot.commands.TeleopSwerve;
import frc.robot.commands.ZeroGyro;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shoulder;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Wrist;

public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);

    /* Drive Controls */
    /*
    private final int translationAxis = PS4Controller.Axis.kLeftY.value;
    private final int strafeAxis = PS4Controller.Axis.kLeftX.value;
    private final int rotationAxis = PS4Controller.Axis.kRightX.value;
    */

    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

    /* Driver Buttons */
    //private final JoystickButton robotCentric = new JoystickButton(driver, PS4Controller.Button.kR2.value);
    public JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kStart.value);
    private final JoystickButton balanceButton = new JoystickButton(driver, XboxController.Button.kLeftStick.value);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final Intake i_Intake;
    private final Shoulder s_Shoulder;
    private final Wrist w_Wrist;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer(Intake i_Intake, Shoulder s_Shoulder, Wrist w_Wrist) {
        this.i_Intake = i_Intake;
        this.s_Shoulder = s_Shoulder;
        this.w_Wrist = w_Wrist;

        zeroGyro.onTrue(new ZeroGyro(s_Swerve));
        balanceButton.onTrue(new BalanceCommand(s_Swerve));

        double sensitivity = 0.6;
        
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve,
                () -> Conversions.joystickSensitivity(sensitivity, -driver.getRawAxis(translationAxis)),
                () -> Conversions.joystickSensitivity(sensitivity, -driver.getRawAxis(strafeAxis)),
                () -> Conversions.joystickSensitivity(sensitivity, -driver.getRawAxis(rotationAxis)),
                () -> robotCentric.getAsBoolean()
            )
        );

    }

    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        // return new exampleAuto(s_Swerve, i_Intake, s_Shoulder, w_Wrist);
        //https://docs.wpilib.org/en/stable/docs/software/dashboards/smartdashboard/choosing-an-autonomous-program-from-smartdashboard.html
        return new pathplanner(s_Swerve, i_Intake, s_Shoulder, w_Wrist);
    }

    public Joystick getDriver(){
        return driver;
    }
}