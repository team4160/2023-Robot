package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wrist;

public class PositionWrist extends CommandBase{
    private Wrist w_Wrist;
    private double percentOutput;
   
    public PositionWrist(Wrist w_Wrist, double percentOutput){
        this.w_Wrist = w_Wrist;
        addRequirements(w_Wrist);
        
        this.percentOutput = percentOutput;
    }
   
    @Override
    public void execute() {
        w_Wrist.manual(percentOutput);
    }
}
