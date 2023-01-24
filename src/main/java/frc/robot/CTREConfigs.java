package frc.robot;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.sensors.AbsoluteSensorRange;
import com.ctre.phoenix.sensors.CANCoderConfiguration;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;
import com.ctre.phoenix.sensors.SensorTimeBase;

public final class CTREConfigs {
    // public TalonFXConfiguration swerveAngleFXConfig;
    public TalonSRXConfiguration swerveAngleFXConfig;
    public TalonFXConfiguration swerveDriveFXConfig;
    public CANCoderConfiguration swerveCanCoderConfig;

    public CTREConfigs(){
        // swerveAngleFXConfig = new TalonFXConfiguration();
        swerveAngleFXConfig = new TalonSRXConfiguration();
        swerveDriveFXConfig = new TalonFXConfiguration();
        swerveCanCoderConfig = new CANCoderConfiguration();

        /* Swerve Angle Motor Configurations */
        swerveAngleFXConfig.slot0.kP = Constants.Swerve.angleKP;
        swerveAngleFXConfig.slot0.kI = Constants.Swerve.angleKI;
        swerveAngleFXConfig.slot0.kD = Constants.Swerve.angleKD;
        swerveAngleFXConfig.slot0.kF = Constants.Swerve.angleKF;
        // swerveAngleFXConfig.supplyCurrLimit = angleSupplyLimit;
        if (Constants.Swerve.angleEnableCurrentLimit) {
            swerveAngleFXConfig.continuousCurrentLimit = Constants.Swerve.angleContinuousCurrentLimit;
            swerveAngleFXConfig.peakCurrentLimit = Constants.Swerve.anglePeakCurrentLimit;
            swerveAngleFXConfig.peakCurrentDuration = (int) Constants.Swerve.anglePeakCurrentDuration * 1000;
        }
        else {
            swerveAngleFXConfig.continuousCurrentLimit = 0;
            swerveAngleFXConfig.peakCurrentLimit = 0;
            swerveAngleFXConfig.peakCurrentDuration = 0;
        }

        /* Swerve Drive Motor Configuration */
        SupplyCurrentLimitConfiguration driveSupplyLimit = new SupplyCurrentLimitConfiguration(
            Constants.Swerve.driveEnableCurrentLimit, 
            Constants.Swerve.driveContinuousCurrentLimit, 
            Constants.Swerve.drivePeakCurrentLimit, 
            Constants.Swerve.drivePeakCurrentDuration);

        swerveDriveFXConfig.slot0.kP = Constants.Swerve.driveKP;
        swerveDriveFXConfig.slot0.kI = Constants.Swerve.driveKI;
        swerveDriveFXConfig.slot0.kD = Constants.Swerve.driveKD;
        swerveDriveFXConfig.slot0.kF = Constants.Swerve.driveKF;        
        swerveDriveFXConfig.supplyCurrLimit = driveSupplyLimit;
        swerveDriveFXConfig.openloopRamp = Constants.Swerve.openLoopRamp;
        swerveDriveFXConfig.closedloopRamp = Constants.Swerve.closedLoopRamp;
        
        /* Swerve CANCoder Configuration */
        swerveCanCoderConfig.absoluteSensorRange = AbsoluteSensorRange.Unsigned_0_to_360;
        swerveCanCoderConfig.sensorDirection = Constants.Swerve.canCoderInvert;
        swerveCanCoderConfig.initializationStrategy = SensorInitializationStrategy.BootToAbsolutePosition;
        swerveCanCoderConfig.sensorTimeBase = SensorTimeBase.PerSecond;
    }
}