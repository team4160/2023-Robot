package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    TalonFX intakeMotor_1;
    TalonFX intakeMotor_2;
    PneumaticHub revph;
    Solenoid intake_piston;

    public Intake(){
        intakeMotor_1 = new TalonFX(Constants.MotorConstants.intakeMotor_1_id);
        intakeMotor_2 = new TalonFX(Constants.MotorConstants.intakeMotor_2_id);

        intakeMotor_1.setNeutralMode(NeutralMode.Brake);
        intakeMotor_2.setNeutralMode(NeutralMode.Brake);

        intakeMotor_1.configFactoryDefault();
        intakeMotor_2.configFactoryDefault();

        intakeMotor_2.set(ControlMode.Follower, intakeMotor_1.getDeviceID());

        revph = new PneumaticHub(Constants.Globals.compressorID);
        setCompressor(true);
        intake_piston = new Solenoid(PneumaticsModuleType.REVPH, Constants.Globals.solenoidChannel);
    }

    public void setIntake(double percentOutput){
        intakeMotor_1.set(ControlMode.PercentOutput, percentOutput);
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
