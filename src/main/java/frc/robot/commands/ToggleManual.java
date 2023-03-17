package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shoulder;

public class ToggleManual extends CommandBase{
    private final Shoulder driveSubsystem;
    private final int manual_selection;

    public ToggleManual(Shoulder subsystem, int manual_selection){
        //0 for changing wrist to manual and 1 for changing shoulder to manual
        driveSubsystem = subsystem;
        addRequirements(driveSubsystem);
        this.manual_selection = manual_selection;
    }

    @Override
    public void initialize(){
        //toggling wrist
        if(manual_selection == 0){
            Constants.Globals.wrist_manual = !Constants.Globals.wrist_manual;
        }
        //toggling shoulder
        else if(manual_selection == 1){
            Constants.Globals.shoulder_manual = !Constants.Globals.shoulder_manual;
        }
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}