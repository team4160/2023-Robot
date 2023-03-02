package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Wrist extends SubsystemBase{
    TalonFX wristMotor_1;
    TalonFX wristMotor_2;

    public Wrist(){
        wristMotor_1 = new TalonFX(Constants.MotorConstants.wristMotor_1_id);
        wristMotor_2 = new TalonFX(Constants.MotorConstants.wristMotor_2_id);

        wristMotor_1.configFactoryDefault();
        wristMotor_2.configFactoryDefault();

        wristMotor_1.setNeutralMode(NeutralMode.Brake);
        wristMotor_2.setNeutralMode(NeutralMode.Brake);

        wristMotor_2.set(ControlMode.Follower, wristMotor_1.getDeviceID());

        wristMotor_1.setInverted(true);
        wristMotor_2.setInverted(InvertType.FollowMaster);

    }  
    
    public void manual(double percentOutput){
        wristMotor_1.set(ControlMode.PercentOutput, percentOutput);
    }
}
