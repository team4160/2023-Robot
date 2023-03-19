// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.util.PID.Gains;
import frc.lib.util.PID.PIDController;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

public class BalanceCommand extends CommandBase {
  protected Swerve swerve;
  protected PIDController balancePID = new PIDController();
  /** Creates a new BalanceCommand. */
  public BalanceCommand(Swerve swerve) {
    this.swerve = swerve;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(swerve);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Gains balanceGains = new Gains("balance gains", 0.02, 0.0001, 0.32);
    balancePID.setGains(balanceGains);
    balancePID.setTargetPosition(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Math.abs(swerve.gyro.getRoll()) > 2.5){
      swerve.drive(            
      new Translation2d(balancePID.getOutput(swerve.gyro.getRoll()), 0).times(Constants.Swerve.maxSpeed * 0.27), 
        0, 
        false, //Field oriented by the controller switch
      true
      );
    }
    else{
      swerve.setStop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    Joystick driver = new Joystick(0);
    return (Math.abs(driver.getRawAxis(XboxController.Axis.kLeftX.value)) > 0.25) || (Math.abs(driver.getRawAxis(XboxController.Axis.kLeftY.value)) > 0.25) || (Math.abs(driver.getRawAxis(XboxController.Axis.kRightX.value)) > 0.25);
  }
}