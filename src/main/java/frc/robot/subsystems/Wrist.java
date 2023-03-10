package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import java.util.Map;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifierConfiguration;
import com.ctre.phoenix.CANifierJNI;
import com.ctre.phoenix.ParamEnum;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.math.Conversions;
import frc.robot.Constants;

public class Wrist extends SubsystemBase{
    TalonFX wristMotor_1;
    TalonFX wristMotor_2;
    CANifier caNifier;

    public Wrist(){
        wristMotor_1 = new TalonFX(Constants.MotorConstants.wristMotor_1_id);
        wristMotor_2 = new TalonFX(Constants.MotorConstants.wristMotor_2_id);

        //wristMotor_1.configFactoryDefault();
        //wristMotor_2.configFactoryDefault();

        wristMotor_1.setNeutralMode(NeutralMode.Brake);
        wristMotor_2.setNeutralMode(NeutralMode.Brake);

        wristMotor_1.config_kP(0, 0.014);
        wristMotor_1.config_kD(0, 0.69);

        wristMotor_2.set(ControlMode.Follower, wristMotor_1.getDeviceID());

        wristMotor_1.setInverted(false);
        wristMotor_2.setInverted(InvertType.FollowMaster);
        caNifier = new CANifier(0);

        double[] _dutyCycleAndPeriod = new double[]{0, 0};
		caNifier.getPWMInput(CANifier.PWMChannel.PWMChannel1, _dutyCycleAndPeriod);
        if (_dutyCycleAndPeriod[0] < 200)
            _dutyCycleAndPeriod[0] += 1035;
        wristMotor_1.setSelectedSensorPosition(Conversions.Map(_dutyCycleAndPeriod[0], 303, 580, 0, 41373));
        // wristMotor_1.setSelectedSensorPosition(0);
    }

    @Override
    public void periodic(){
        double[] _dutyCycleAndPeriod = new double[]{0, 0};
       
		caNifier.getPWMInput(CANifier.PWMChannel.PWMChannel1, _dutyCycleAndPeriod);
        if (_dutyCycleAndPeriod[0] < 200)
            _dutyCycleAndPeriod[0] += 1035;

        SmartDashboard.putNumber("canifier 2", _dutyCycleAndPeriod[0]);
        SmartDashboard.putNumber("wrist pos", wristMotor_1.getSelectedSensorPosition());
    }

    public void manual(double percentOutput){
        wristMotor_1.set(ControlMode.PercentOutput, percentOutput);
    }

    public void position(double position){
        wristMotor_1.set(ControlMode.Position, position);
    }

    public void zero(){
        wristMotor_1.setSelectedSensorPosition(0);
    }
}
