// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.swing.plaf.basic.BasicTreeUI.SelectionModelPropertyChangeHandler;

import com.pathplanner.lib.server.PathPlannerServer;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.PositionArm;
import frc.robot.commands.SetIntake;
import frc.robot.commands.SetSolenoid;
import frc.robot.commands.ToggleIntake;
import frc.robot.commands.ToggleManual;
import frc.robot.commands.Zero;
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
  public JoystickButton toggle_intake_status = new JoystickButton(operator, XboxController.Button.kA.value); 
  public JoystickButton toggle_intake_direction = new JoystickButton(operator, XboxController.Button.kX.value);
  public JoystickButton fire_solenoid = new JoystickButton(operator, XboxController.Button.kLeftBumper.value);
  public JoystickButton retract_solenoid = new JoystickButton(operator, XboxController.Button.kRightBumper.value);
  public JoystickButton zero = new JoystickButton(operator, XboxController.Button.kStart.value);

  public POVButton setStowed = new POVButton(operator, 0);
  public POVButton setLow = new POVButton(operator, 90);
  public POVButton setMiddle = new POVButton(operator, 180);
  public POVButton setHigh = new POVButton(operator, 270);

  private void configureButtonBindings() {
    toggle_wrist_manual.onTrue(new ToggleManual(s_Shoulder, 0));
    toggle_shoulder_manual.onTrue(new ToggleManual(s_Shoulder, 1));

    toggle_intake_direction.onTrue(new ToggleIntake(i_Intake));
    toggle_intake_status.onTrue(new SetIntake(i_Intake, true)).onFalse(new SetIntake(i_Intake, false));

    fire_solenoid.onTrue(new SetSolenoid(i_Intake, true));
    retract_solenoid.onTrue(new SetSolenoid(i_Intake, false));

    setStowed.onTrue(new PositionArm(s_Shoulder, w_Wrist, 0));
    setLow.onTrue(new PositionArm(s_Shoulder, w_Wrist, 1));
    setMiddle.onTrue(new PositionArm(s_Shoulder, w_Wrist, 2));
    setHigh.onTrue(new PositionArm(s_Shoulder, w_Wrist, 3));

    zero.onTrue(new Zero(s_Shoulder, w_Wrist));
  }

  @Override
  public void robotInit() {
    ctreConfigs = new CTREConfigs();
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    configureButtonBindings();
    PathPlannerServer p = new PathPlannerServer();
    p.startServer(5811);
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
