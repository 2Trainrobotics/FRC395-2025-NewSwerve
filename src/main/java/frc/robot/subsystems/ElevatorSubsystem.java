package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
    private final SparkMax driveLeftElevator = new SparkMax(ElevatorConstants.kLeftElevatorCanId, MotorType.kBrushless);
    private final SparkMax driveRightElevator = new SparkMax(ElevatorConstants.kRightElevatorCanId, MotorType.kBrushless);

    public ElevatorSubsystem() {
        SparkMaxConfig driveLeftElevatorConfig = new SparkMaxConfig();
        SparkMaxConfig driveRightElevatorConfig = new SparkMaxConfig();

        driveLeftElevatorConfig
            .inverted(false)
            .idleMode(IdleMode.kBrake);
        driveLeftElevator.configure(driveLeftElevatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        driveRightElevatorConfig
            .inverted(true)
            .idleMode(IdleMode.kBrake)
            .follow(driveLeftElevator);
        driveRightElevator.configure(driveRightElevatorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // setDefaultCommand(
        // runOnce(
        //         () -> {
        //             driveLeftElevator.set(0);
        //         })
        //     .andThen(run(() -> {}))
        //     .withName("Idle"));

    }

    public void moveElevator(double elevatorSpeed) {
        driveLeftElevator.set(elevatorSpeed);
    }
}