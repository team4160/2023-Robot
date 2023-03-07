package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.CANifierConfiguration;
import com.ctre.phoenix.CANifierJNI;
import com.ctre.phoenix.ParamEnum;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
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

        wristMotor_2.set(ControlMode.Follower, wristMotor_1.getDeviceID());

        wristMotor_1.setInverted(false);
        wristMotor_2.setInverted(InvertType.FollowMaster);
        caNifier = new CANifier(0);
    }

    // @Override
    // public void periodic(){
    //     double[][] _dutyCycleAndPeriods = new double[][]{new double[]{0, 0}, new double[]{0, 0},
    //                                                  new double[]{0, 0}, new double[]{0, 0}};
       

    //     caNifier.getPWMInput(CANifier.PWMChannel.PWMChannel0, _dutyCycleAndPeriods[0]);
	// 	caNifier.getPWMInput(CANifier.PWMChannel.PWMChannel1, _dutyCycleAndPeriods[1]);
	// 	caNifier.getPWMInput(CANifier.PWMChannel.PWMChannel2, _dutyCycleAndPeriods[2]);
	// 	caNifier.getPWMInput(CANifier.PWMChannel.PWMChannel3, _dutyCycleAndPeriods[3]);

    //     System.out.println("TaskMeasurePulseSensors:"   + _dutyCycleAndPeriods[0][0] + " :"
    //     + _dutyCycleAndPeriods[1][0] + " :" 
    //     + _dutyCycleAndPeriods[2][0] + " :" 
    //     + _dutyCycleAndPeriods[3][0]);
    // }
    
    public void manual(double percentOutput){
        wristMotor_1.set(ControlMode.PercentOutput, percentOutput);
    }
}
