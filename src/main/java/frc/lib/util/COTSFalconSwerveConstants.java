package frc.lib.util;

import edu.wpi.first.math.util.Units;

/* Contains values and required settings for common COTS swerve modules. */
public class COTSFalconSwerveConstants {
    public final double wheelDiameter;
    public final double wheelCircumference;
    public final double angleGearRatio;
    public final double driveGearRatio;
    public final double angleKP;
    public final double angleKI;
    public final double angleKD;
    public final double angleKF;
    public final boolean driveMotorInvert;
    public final boolean angleMotorInvert;
    public final boolean canCoderInvert;

    public COTSFalconSwerveConstants(double wheelDiameter, double angleGearRatio, double driveGearRatio, double angleKP, double angleKI, double angleKD, double angleKF, boolean driveMotorInvert, boolean angleMotorInvert, boolean canCoderInvert){
        this.wheelDiameter = wheelDiameter;
        this.wheelCircumference = wheelDiameter * Math.PI;
        this.angleGearRatio = angleGearRatio;
        this.driveGearRatio = driveGearRatio;
        this.angleKP = angleKP;
        this.angleKI = angleKI;
        this.angleKD = angleKD;
        this.angleKF = angleKF;
        this.driveMotorInvert = driveMotorInvert;
        this.angleMotorInvert = angleMotorInvert;
        this.canCoderInvert = canCoderInvert;
    }
    
    /** WESTCOAST PRODUCT - Swerve X inverted NEO/CIM/775 steered */
    public static COTSFalconSwerveConstants SwerveXiN(double driveGearRatio){
        double wheelDiameter = Units.inchesToMeters(4.0);
 
        double angleGearRatio = 11.14285714285714;
 
        double angleKP = 1.0;
        double angleKI = 0.0;  // Always leave I at 0
        double angleKD = 0.0;
        double angleKF = 0.0;
 
        boolean driveMotorInvert = false;
        boolean angleMotorInvert = true;
        boolean canCoderInvert = true;
        return new COTSFalconSwerveConstants(wheelDiameter, angleGearRatio, driveGearRatio, angleKP, angleKI, angleKD, angleKF, driveMotorInvert, angleMotorInvert, canCoderInvert);
    }

    /** WESTCOAST PRODUCT - Swerve X inverted Falcon steered */
    public static COTSFalconSwerveConstants SwerveXiF(double driveGearRatio){
        double wheelDiameter = Units.inchesToMeters(4.0);
 
        double g1 = 9; // pinon on motor 9T on Falcon, 8T on CIM/NEO/775
        double g2 = 32;
        double g3 = 24;
        double g4 = 14;
        double g5 = 72;
        double angleGearRatio = (g2 / g1) * (g3 / g2) * (g4 / g3) * (g5 / g4);
 
        double angleKP = 0.3;
        double angleKI = 0.0;
        double angleKD = 0.0;
        double angleKF = 0.0;
 
        boolean driveMotorInvert = false;
        boolean angleMotorInvert = true;
        boolean canCoderInvert = true;
        return new COTSFalconSwerveConstants(wheelDiameter, angleGearRatio, driveGearRatio, angleKP, angleKI, angleKD, angleKF, driveMotorInvert, angleMotorInvert, canCoderInvert);
    }
    
    /** Swerve Drive Specialties - MK3 Module*/
    public static COTSFalconSwerveConstants SDSMK3(double driveGearRatio){
        double wheelDiameter = Units.inchesToMeters(4.0);
 
        /** 12.8 : 1 */
        double angleGearRatio = (12.8 / 1.0);
 
        double angleKP = 0.2;
        double angleKI = 0.0;
        double angleKD = 0.0;
        double angleKF = 0.0;
 
        boolean driveMotorInvert = false;
        boolean angleMotorInvert = false;
        boolean canCoderInvert = false;
        return new COTSFalconSwerveConstants(wheelDiameter, angleGearRatio, driveGearRatio, angleKP, angleKI, angleKD, angleKF, driveMotorInvert, angleMotorInvert, canCoderInvert);
    }

    /** Swerve Drive Specialties - MK4 Module*/
    public static COTSFalconSwerveConstants SDSMK4(double driveGearRatio){
        double wheelDiameter = Units.inchesToMeters(4.0);
 
        /** 12.8 : 1 */
        double angleGearRatio = (12.8 / 1.0);
 
        double angleKP = 0.2;
        double angleKI = 0.0;
        double angleKD = 0.0;
        double angleKF = 0.0;
 
        boolean driveMotorInvert = false;
        boolean angleMotorInvert = false;
        boolean canCoderInvert = false;
        return new COTSFalconSwerveConstants(wheelDiameter, angleGearRatio, driveGearRatio, angleKP, angleKI, angleKD, angleKF, driveMotorInvert, angleMotorInvert, canCoderInvert);
    }

    /** Swerve Drive Specialties - MK4i Module*/
    public static COTSFalconSwerveConstants SDSMK4i(double driveGearRatio){
        double wheelDiameter = Units.inchesToMeters(4.0);

        /** (150 / 7) : 1 */
        double angleGearRatio = ((150.0 / 7.0) / 1.0);

        double angleKP = 0.3;
        double angleKI = 0.0;
        double angleKD = 0.0;
        double angleKF = 0.0;

        boolean driveMotorInvert = false;
        boolean angleMotorInvert = true;
        boolean canCoderInvert = false;
        return new COTSFalconSwerveConstants(wheelDiameter, angleGearRatio, driveGearRatio, angleKP, angleKI, angleKD, angleKF, driveMotorInvert, angleMotorInvert, canCoderInvert);
    }

    /* Drive Gear Ratios for all supported modules */
    public class driveGearRatios{
        /* SwerveX */
        /** SwerveX Belt - 5.5 : 1 */
        public static final double SWERVEX_P1 = (5.50 / 1.0);
        /** SwerveX Belt - 6.55 : 1 */
        public static final double SWERVEX_P2 = (6.55 / 1.0);
        /** SwerveX Belt - 7.8 : 1 */
        public static final double SWERVEX_P3 = (7.80 / 1.0);
        /** SwerveX Gear - 6.75 : 1 */
        public static final double SWERVEX_G1 = (6.75 / 1.0);
        /** SwerveX Gear - 7.36 : 1 */
        public static final double SWERVEX_G2 = (7.36 / 1.0);
        /** SwerveX Gear - 8.10 : 1 */
        public static final double SWERVEX_G3 = (8.10 / 1.0);

        /* SDS MK3 */
        /** SDS MK3 - 8.16 : 1 */
        public static final double SDSMK3_Standard = (8.16 / 1.0);
        /** SDS MK3 - 6.86 : 1 */
        public static final double SDSMK3_Fast = (6.86 / 1.0);

        /* SDS MK4 */
        /** SDS MK4 - 8.14 : 1 */
        public static final double SDSMK4_L1 = (8.14 / 1.0);
        /** SDS MK4 - 6.75 : 1 */
        public static final double SDSMK4_L2 = (6.75 / 1.0);
        /** SDS MK4 - 6.12 : 1 */
        public static final double SDSMK4_L3 = (6.12 / 1.0);
        /** SDS MK4 - 5.14 : 1 */
        public static final double SDSMK4_L4 = (5.14 / 1.0);
        
        /* SDS MK4i */
        /** SDS MK4i - 8.14 : 1 */
        public static final double SDSMK4i_L1 = (8.14 / 1.0);
        /** SDS MK4i - 6.75 : 1 */
        public static final double SDSMK4i_L2 = (6.75 / 1.0);
        /** SDS MK4i - 6.12 : 1 */
        public static final double SDSMK4i_L3 = (6.12 / 1.0);
    }
}
