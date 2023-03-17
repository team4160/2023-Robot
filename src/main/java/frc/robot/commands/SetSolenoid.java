package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class SetSolenoid extends CommandBase{
    private final Intake intakeSubsystem;
    public boolean on;

    public SetSolenoid(Intake subsystem, boolean fire){
        subsystem.setCompressor(true);
        this.intakeSubsystem = subsystem;
        addRequirements(this.intakeSubsystem);
        this.on = fire;
    }

    @Override
    public void initialize(){
        intakeSubsystem.fire(on);
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}