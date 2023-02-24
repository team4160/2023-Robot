package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    TalonFX intakeMotor_1;
    TalonFX intakeMotor_2;

    public Intake(){
        intakeMotor_1 = new TalonFX(Constants.MotorConstants.intakeMotor_1_id);
        intakeMotor_2 = new TalonFX(Constants.MotorConstants.intakeMotor_2_id);

        intakeMotor_1.setNeutralMode(NeutralMode.Brake);
        intakeMotor_2.setNeutralMode(NeutralMode.Brake);

        intakeMotor_2.set(ControlMode.Follower, intakeMotor_1.getDeviceID());
    }

    public void setIntake(double percentOutput){
        intakeMotor_1.set(ControlMode.PercentOutput, percentOutput);
    }
    
}
