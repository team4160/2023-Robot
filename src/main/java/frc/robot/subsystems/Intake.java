package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxAbsoluteEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    CANSparkMax intakeMotor_1;
    CANSparkMax intakeMotor_2;
    PneumaticHub revph;
    Solenoid intake_piston;

    public Intake(){
        intakeMotor_1 = new CANSparkMax(Constants.MotorConstants.intakeMotor_1_id, MotorType.kBrushless);
        intakeMotor_2 = new CANSparkMax(Constants.MotorConstants.intakeMotor_2_id, MotorType.kBrushless);

        intakeMotor_1.setIdleMode(IdleMode.kBrake);
        intakeMotor_2.setIdleMode(IdleMode.kBrake); 
        
        intakeMotor_2.follow(intakeMotor_1, true);

        revph = new PneumaticHub(Constants.Globals.compressorID);
        setCompressor(true);
        intake_piston = new Solenoid(PneumaticsModuleType.REVPH, Constants.Globals.solenoidChannel);
    }

    public void setIntake(double percentOutput){
        intakeMotor_1.set(percentOutput);

    }

    public void fire(boolean on){
        intake_piston.set(on);
    }

    public void setCompressor(boolean enable){
        if(enable){
            revph.enableCompressorAnalog(Constants.Globals.compressor_min, Constants.Globals.compressor_max);
        } else{
            revph.disableCompressor();
        }
    }
    
}
