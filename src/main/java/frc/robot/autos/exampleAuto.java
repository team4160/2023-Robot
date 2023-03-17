package frc.robot.autos;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.PositionArm;
import frc.robot.commands.PositionWrist;
import frc.robot.commands.SetIntake;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shoulder;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Wrist;

public class exampleAuto extends SequentialCommandGroup {
    public exampleAuto(Swerve s_Swerve, Intake i_Intake, Shoulder s_Shoulder, Wrist w_Wrist){
        var thetaController =
            new ProfiledPIDController(
                Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        PathPlannerTrajectory examplePath = PathPlanner.loadPath("Ventura", new PathConstraints(4, 3));

        System.out.println(examplePath);
        s_Swerve.getField().getObject("traj").setTrajectory(examplePath);

        SwerveControllerCommand swerveControllerCommand =
            new SwerveControllerCommand(
                examplePath,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);

        addCommands(
            
            new InstantCommand(() -> s_Swerve.resetOdometry(examplePath.getInitialPose())),
            // new PositionArm(s_Shoulder, w_Wrist, 2),
            new WaitCommand(1),
            // new PositionWrist(w_Wrist, 2),
            // new WaitCommand(1),
            // new SetIntake(i_Intake, true, false),
            // new WaitCommand(0.5),
            // new SetIntake(i_Intake, false, false),
            // new WaitCommand(0.2),
            // new PositionArm(s_Shoulder, w_Wrist, 0),
            // new WaitCommand(5),
            swerveControllerCommand
        );
    }
}