package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class SetIntake extends CommandBase{
    private final Intake intakeSubsystem;
    public boolean status;

    public SetIntake(Intake subsystem, boolean status){
        intakeSubsystem = subsystem;
        addRequirements(intakeSubsystem);
        this.status = status;
    }

    @Override
    public void initialize(){
        intakeSubsystem.setIntake(status ? Constants.Globals.intakePercentOutput : 0);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}