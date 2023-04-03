package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class SetIntake extends CommandBase{
    private final Intake intakeSubsystem;
    public boolean status;
    public boolean direction;

    public SetIntake(Intake subsystem, boolean status, boolean direction){
        intakeSubsystem = subsystem;
        addRequirements(intakeSubsystem);
        this.status = status;
        this.direction = direction;
    }

    @Override
    public void initialize(){
        //intake
        if(direction){
            intakeSubsystem.setIntake(status ? Constants.Globals.intakePercentOutput : 0);
        }
        //outtake
        else{
            intakeSubsystem.setIntake(status ? -Constants.Globals.outakePercentOutput : 0);
        }
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}