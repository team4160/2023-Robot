package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class ToggleIntake extends CommandBase{
    private final Intake intakeSubsystem;

    public ToggleIntake(Intake subsystem){
        intakeSubsystem = subsystem;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize(){
        Constants.Globals.intakePercentOutput = -Constants.Globals.intakePercentOutput;
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}