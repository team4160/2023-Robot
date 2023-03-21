package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Swerve;

public class ZeroGyro extends CommandBase{
    private final Swerve s_Swerve;

    public ZeroGyro(Swerve subsystem){
        this.s_Swerve = subsystem;
        addRequirements(this.s_Swerve);
    }

    @Override
    public void initialize(){
        s_Swerve.zeroGyro();
        // s_Swerve.resetOdometry(new Pose2d(0, 0, new Rotation2d(0)));
    }

    @Override
    public boolean isFinished(){
        return true;
    }
}