package frc.robot.subsystems;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.math.Conversions;
import frc.robot.Constants;

public class Shoulder extends SubsystemBase{
    TalonFX shoulderMotor_1;
    TalonFX shoulderMotor_2;
    CANifier caNifier;

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

        shoulderMotor_1.config_kP(0, 0.014);
        shoulderMotor_1.config_kD(0, 0.69);

        caNifier = new CANifier(0);

        double[] _dutyCycleAndPeriod = new double[]{0, 0};
		caNifier.getPWMInput(CANifier.PWMChannel.PWMChannel1, _dutyCycleAndPeriod);
        if (_dutyCycleAndPeriod[0] < 200)
            _dutyCycleAndPeriod[0] += 1035;
        shoulderMotor_1.setSelectedSensorPosition(Conversions.Map(_dutyCycleAndPeriod[0], 309, 94, 0, 17435));
    }

    public void manual(double percentOutput){
        shoulderMotor_1.set(ControlMode.PercentOutput, percentOutput);
    }

    public void position(double position){
        shoulderMotor_1.set(ControlMode.Position, position);
    }

    public void zero(){
        shoulderMotor_1.setSelectedSensorPosition(0);
    }

    @Override
    public void periodic(){
        double[] _dutyCycleAndPeriod = new double[]{0, 0};
       
		caNifier.getPWMInput(CANifier.PWMChannel.PWMChannel0, _dutyCycleAndPeriod);
        if (_dutyCycleAndPeriod[0] < 200)
            _dutyCycleAndPeriod[0] += 1035;

        SmartDashboard.putNumber("canifier 1", _dutyCycleAndPeriod[0]);
        SmartDashboard.putNumber("shoulder pos", shoulderMotor_1.getSelectedSensorPosition());
    }    
}
