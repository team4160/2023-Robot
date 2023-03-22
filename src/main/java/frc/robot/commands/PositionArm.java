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
        //first cone
        else if(height == 1){
            s_Shoulder.position(-57000);
        }
        //first cube
        else if(height == 2){
            s_Shoulder.position(-28806);
        }
        //second cube
        else if(height == 3){
            s_Shoulder.position(-70000);
        }
        //player
        else if(height == 4){
            s_Shoulder.position(-105000);
        }
        //ground
        else if(height == 5){
            s_Shoulder.position(-22332);
        }
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}
