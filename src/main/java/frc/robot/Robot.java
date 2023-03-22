// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.server.PathPlannerServer;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.PositionArm;
import frc.robot.commands.PositionWrist;
import frc.robot.commands.SetIntake;
import frc.robot.commands.SetSolenoid;
import frc.robot.commands.ToggleManual;
import frc.robot.commands.Zero;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shoulder;
import frc.robot.subsystems.Wrist;

public class Robot extends TimedRobot {
  public static CTREConfigs ctreConfigs;

  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private static final String kBalance = "Balance";
  private static final String kBackup = "Backup";
  private String m_autoSelected;

  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  public Wrist w_Wrist = new Wrist();
  public Shoulder s_Shoulder = new Shoulder();
  public Intake i_Intake = new Intake();

  //PS4 Setup
  /*
  public PS4Controller operator = new PS4Controller(1);
  public JoystickButton toggle_wrist_manual = new JoystickButton(operator, PS4Controller.Button.kTriangle.value); 
  public JoystickButton toggle_shoulder_manual = new JoystickButton(operator, PS4Controller.Button.kCircle.value);
  public JoystickButton toggle_intake_status = new JoystickButton(operator, PS4Controller.Button.kCross.value); 
  public JoystickButton toggle_intake_direction = new JoystickButton(operator, PS4Controller.Button.kSquare.value);
  public JoystickButton fire_solenoid = new JoystickButton(operator, PS4Controller.Button.kL1.value);
  public JoystickButton retract_solenoid = new JoystickButton(operator, PS4Controller.Button.kR1.value);
  */
  
  //XBOX Setup
  private XboxController operator = new XboxController(1);
  public JoystickButton toggle_wrist_manual = new JoystickButton(operator, XboxController.Button.kY.value); 
  public JoystickButton toggle_shoulder_manual = new JoystickButton(operator, XboxController.Button.kB.value);
  public JoystickButton toggle_outake = new JoystickButton(operator, XboxController.Button.kA.value); 
  public JoystickButton toggle_intake = new JoystickButton(operator, XboxController.Button.kX.value);
  public JoystickButton fire_solenoid = new JoystickButton(operator, XboxController.Button.kLeftBumper.value);
  public JoystickButton retract_solenoid = new JoystickButton(operator, XboxController.Button.kRightBumper.value);

  public JoystickButton zeroEncoders = new JoystickButton(operator, XboxController.Button.kStart.value);

  public POVButton setStowed = new POVButton(operator, 0);
  public POVButton setFirstCone = new POVButton(operator, 90);
  public POVButton setFirstCube = new POVButton(operator, 180);
  public POVButton setSecondCube = new POVButton(operator, 270);

  public JoystickButton setPlayer = new JoystickButton(operator, XboxController.Button.kRightStick.value);
  public JoystickButton setGround = new JoystickButton(operator, XboxController.Button.kLeftStick.value);

  private void configureButtonBindings() {
    toggle_wrist_manual.onTrue(new ToggleManual(s_Shoulder, 0));
    toggle_shoulder_manual.onTrue(new ToggleManual(s_Shoulder, 1));

    toggle_intake.onTrue(new SetIntake(i_Intake, true, true)).onFalse(new SetIntake(i_Intake, false, true));
    toggle_outake.onTrue(new SetIntake(i_Intake, true, false)).onFalse(new SetIntake(i_Intake, false, false));

    fire_solenoid.onTrue(new SetSolenoid(i_Intake, true));
    retract_solenoid.onTrue(new SetSolenoid(i_Intake, false));

    setStowed.onTrue(new PositionArm(s_Shoulder, w_Wrist, 0));
    setFirstCone.onTrue(new PositionArm(s_Shoulder, w_Wrist, 1)).onFalse(new PositionWrist(w_Wrist, 1));
    setFirstCube.onTrue(new PositionArm(s_Shoulder, w_Wrist, 2)).onFalse(new PositionWrist(w_Wrist, 2));
    setSecondCube.onTrue(new PositionArm(s_Shoulder, w_Wrist, 3)).onFalse(new PositionWrist(w_Wrist, 3));
    setPlayer.onTrue(new PositionArm(s_Shoulder, w_Wrist, 4)).onFalse(new PositionWrist(w_Wrist, 4));
    setGround.onTrue(new PositionArm(s_Shoulder, w_Wrist, 5)).onFalse(new PositionWrist(w_Wrist, 5));

    zeroEncoders.onTrue(new Zero(s_Shoulder, w_Wrist));
  }

  @Override
  public void robotInit() {
    ctreConfigs = new CTREConfigs();
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer(i_Intake, s_Shoulder, w_Wrist);
    configureButtonBindings();
    PathPlannerServer.startServer(5811);

    m_chooser.setDefaultOption("Score Cube + Balance Auto", kBalance);
    m_chooser.addOption("Score Cube + Backup Auto", kBackup);
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    System.out.println("Auto selected: " + m_autoSelected);

    switch (m_autoSelected) {
      case kBackup:
        m_autonomousCommand = m_robotContainer.getAutonomousCommand(kBackup);
        m_autonomousCommand.schedule();
        break;

      case kBalance:
        m_autonomousCommand = m_robotContainer.getAutonomousCommand(kBalance);
        m_autonomousCommand.schedule();
        break;
        
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() {

    if(Constants.Globals.wrist_manual){
      w_Wrist.manual(operator.getLeftY() * .25);
    }
    if(Constants.Globals.shoulder_manual){
      s_Shoulder.manual(operator.getRightY() * .25);
    }

  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}
}
