package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shoulder extends SubsystemBase{
    TalonFX shoulderMotor_1;
    TalonFX shoulderMotor_2;

    public Shoulder(){
        shoulderMotor_1 = new TalonFX(Constants.MotorConstants.shoulderMotor_1_id);
        shoulderMotor_2 = new TalonFX(Constants.MotorConstants.shoulderMotor_2_id);

        shoulderMotor_1.configFactoryDefault();
        shoulderMotor_2.configFactoryDefault();

        shoulderMotor_1.setNeutralMode(NeutralMode.Brake);
        shoulderMotor_2.setNeutralMode(NeutralMode.Brake);

        shoulderMotor_2.set(ControlMode.Follower, shoulderMotor_1.getDeviceID());

        shoulderMotor_1.setInverted(true);
        shoulderMotor_2.setInverted(InvertType.FollowMaster);
    }

    public void manual(double percentOutput){
        shoulderMotor_1.set(ControlMode.PercentOutput, percentOutput);
    }
}
