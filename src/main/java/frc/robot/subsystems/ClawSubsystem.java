package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClawConstants;

public class ClawSubsystem extends SubsystemBase {
    private final SparkMax clawSwingMotor = new SparkMax(ClawConstants.kSwingClawCanId, MotorType.kBrushless);
    private final SparkMax clawShootMotor = new SparkMax(ClawConstants.kShootClawCanId, MotorType.kBrushless);

    public ClawSubsystem() {
        SparkMaxConfig clawSwingMotorConfig = new SparkMaxConfig();
        SparkMaxConfig clawShootMotorConfig = new SparkMaxConfig();

        clawSwingMotorConfig
            .inverted(false)
            .idleMode(IdleMode.kBrake);
        clawSwingMotor.configure(clawSwingMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        clawShootMotorConfig
            .inverted(false)
            .idleMode(IdleMode.kBrake);
        clawShootMotor.configure(clawShootMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public void moveSwing(double swingSpeed) {
        clawSwingMotor.set(swingSpeed);
    }

    public void moveShooter(double shooterSpeed) {
        clawShootMotor.set(shooterSpeed);
    }
}
