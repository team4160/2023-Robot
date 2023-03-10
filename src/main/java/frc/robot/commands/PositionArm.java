package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shoulder;
import frc.robot.subsystems.Wrist;

public class PositionArm extends CommandBase{
    private Shoulder s_Shoulder;
    private Wrist s_Wrist;
    private int height;
   
    public PositionArm(Shoulder s_Shoulder, Wrist s_Wrist, int height){
        this.s_Shoulder = s_Shoulder;
        this.s_Wrist = s_Wrist;
        addRequirements(this.s_Shoulder);
        addRequirements(this.s_Wrist);
        this.height = height;
    }
   
    @Override
    public void execute() {
        //stowed 
        if(height == 0){
            s_Shoulder.position(0);
            s_Wrist.position(0);
        }
        //low
        else if(height == 1){
            s_Shoulder.position(-60000);
            s_Wrist.position(30000);
        }
        //middle
        else if(height == 2){
            s_Shoulder.position(-100000);
            s_Wrist.position(32000);
        }
        //high
        else if(height == 3){
            s_Shoulder.position(-130000);
            s_Wrist.position(33000);
        }
    }
}
