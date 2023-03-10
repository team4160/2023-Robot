package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shoulder;
import frc.robot.subsystems.Wrist;

public class Zero extends CommandBase{
    private final Shoulder s_Shoulder;
    private final Wrist w_Wrist;

    public Zero(Shoulder subsystem, Wrist w_Wrist){
        this.s_Shoulder = subsystem;
        this.w_Wrist = w_Wrist;
        addRequirements(this.s_Shoulder);
        addRequirements(this.w_Wrist);
    }

    @Override
    public void initialize(){
        s_Shoulder.zero();
        w_Wrist.zero();
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}