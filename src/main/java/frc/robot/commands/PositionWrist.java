package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wrist;

public class PositionWrist extends CommandBase{
    private Wrist s_Wrist;
    private int height;
   
    public PositionWrist(Wrist s_Wrist, int height){
        this.s_Wrist = s_Wrist;
        addRequirements(this.s_Wrist);
        this.height = height;
    }
   
    @Override
    public void execute() {
        //stowed 
        if(height == 0){
            s_Wrist.position(0);
        }
        //first cone
        else if(height == 1){
            s_Wrist.position(8000);
        }
        //first cube
        else if(height == 2){
            s_Wrist.position(14000);
        }
        //second cube
        else if(height == 3){
            s_Wrist.position(17022);
        }
        //player
        else if(height == 4){
            s_Wrist.position(32000);
        }
        //ground
        else if(height == 5){
            s_Wrist.position(38832);
        }
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
