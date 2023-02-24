package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeSpeed extends CommandBase{
    private Intake i_Intake;
    private double percentOutput;
   
    public IntakeSpeed(Intake i_Intake, double percentOutput){
        this.i_Intake = i_Intake;
        addRequirements(i_Intake);
        
        this.percentOutput = percentOutput;
    }
   
    @Override
    public void execute() {
        i_Intake.setIntake(percentOutput);
    }
}
