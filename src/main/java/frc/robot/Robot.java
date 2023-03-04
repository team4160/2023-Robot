// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.SetIntake;
import frc.robot.commands.SetSolenoid;
import frc.robot.commands.ToggleIntake;
import frc.robot.commands.ToggleManual;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shoulder;
import frc.robot.subsystems.Wrist;

public class Robot extends TimedRobot {
  public static CTREConfigs ctreConfigs;

  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  public Wrist w_Wrist = new Wrist();
  public Shoulder s_Shoulder = new Shoulder();
  public Intake i_Intake = new Intake();

  private XboxController operator = new XboxController(1);
  //public XboxController driver = new XboxController(1);
    
  public JoystickButton toggle_wrist_manual = new JoystickButton(operator, XboxController.Button.kY.value); 
  public JoystickButton toggle_shoulder_manual = new JoystickButton(operator, XboxController.Button.kB.value);
  public JoystickButton toggle_intake_status = new JoystickButton(operator, XboxController.Button.kA.value); 
  public JoystickButton toggle_intake_direction = new JoystickButton(operator, XboxController.Button.kX.value);
  public JoystickButton fire_solenoid = new JoystickButton(operator, XboxController.Button.kLeftBumper.value);
  public JoystickButton retract_solenoid = new JoystickButton(operator, XboxController.Button.kRightBumper.value);

  private void configureButtonBindings() {
    toggle_wrist_manual.onTrue(new ToggleManual(s_Shoulder, 0));
    toggle_shoulder_manual.onTrue(new ToggleManual(s_Shoulder, 1));

    toggle_intake_direction.onTrue(new ToggleIntake(i_Intake));
    toggle_intake_status.whileTrue(new SetIntake(i_Intake, true)).onFalse(new SetIntake(i_Intake, false));

    fire_solenoid.onTrue(new SetSolenoid(i_Intake, true));
    retract_solenoid.onTrue(new SetSolenoid(i_Intake, false));
  }

  @Override
  public void robotInit() {
    ctreConfigs = new CTREConfigs();
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    configureButtonBindings();
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
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

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
