package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shoulder;

public class PositionArm extends CommandBase{
    private Shoulder s_Shoulder;
    private double percentOutput;
   
    public PositionArm(Shoulder s_Shoulder, double percentOutput){
        this.s_Shoulder = s_Shoulder;
        addRequirements(s_Shoulder);
        
        this.percentOutput = percentOutput;
    }
   
    @Override
    public void execute() {
        s_Shoulder.manual(percentOutput);
    }
}
