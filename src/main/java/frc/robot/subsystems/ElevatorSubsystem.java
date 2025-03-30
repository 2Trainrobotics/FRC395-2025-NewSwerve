package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class ElevatorSubsystem extends SubsystemBase {
    private final SparkMax leftElevatorMotor = new SparkMax(ElevatorConstants.kLeftElevatorCanId, MotorType.kBrushless);
    private final SparkMax rightElevatorMotor = new SparkMax(ElevatorConstants.kRightElevatorCanId, MotorType.kBrushless);
    // private final RelativeEncoder leftElevatorEncoder = leftElevatorMotor.getEncoder();
    private final RelativeEncoder rightElevatorEncoder = rightElevatorMotor.getEncoder();
    private boolean enableGuards = true;

    public ElevatorSubsystem() {
        SparkMaxConfig leftElevatorMotorConfig = new SparkMaxConfig();
        SparkMaxConfig rightElevatorMotorConfig = new SparkMaxConfig();

        leftElevatorMotorConfig
            .inverted(false)
            .idleMode(IdleMode.kBrake);
        leftElevatorMotor.configure(leftElevatorMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightElevatorMotorConfig
            .inverted(true)
            .idleMode(IdleMode.kBrake)
            .follow(leftElevatorMotor);
        rightElevatorMotor.configure(rightElevatorMotorConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // leftElevatorEncoder.setPosition(0);
        rightElevatorEncoder.setPosition(0);

        // setDefaultCommand(
        // runOnce(
        //         () -> {
        //             leftElevatorMotor.set(0);
        //         })
        //     .andThen(run(() -> {}))
        //     .withName("Idle"));

    }

    public void moveElevatorUp() {
        double rightElevatorPosition = rightElevatorEncoder.getPosition();
        // SmartDashboard.putNumber("Left Elevator Position", leftElevatorEncoder.getPosition());
        SmartDashboard.putNumber("Right Elevator Position", rightElevatorEncoder.getPosition());
        // leftElevatorMotor.set(ElevatorConstants.kElevatorUpSpeed);
        if (enableGuards) {
            // As the elevator climbs, the encoder (position) reads a lower number.
            //     Ergo, if the current position is higher than the max allowed, we are still
            //     allowed to climb higher.
            if (rightElevatorPosition > ElevatorConstants.kElevatorMaxHighPosition) {
                leftElevatorMotor.set(ElevatorConstants.kElevatorUpSpeed);
            } else {
                leftElevatorMotor.set(ElevatorConstants.kElevatorMaintainPositionSpeed);

            }
        } else {
            leftElevatorMotor.set(ElevatorConstants.kElevatorUpSpeed);
        }
    }

    public void moveElevatorDown() {
        double rightElevatorPosition = rightElevatorEncoder.getPosition();
        // SmartDashboard.putNumber("Left Elevator Position", leftElevatorEncoder.getPosition());
        SmartDashboard.putNumber("Right Elevator Position", rightElevatorEncoder.getPosition());
        // leftElevatorMotor.set(ElevatorConstants.kElevatorUpSpeed);
        if (enableGuards) {
            // As the elevator descends, the encoder (position) reads a higher number.
            //     Ergo, if the current position is lower than the min allowed, we are still
            //     allowed to descend.
            if (rightElevatorPosition < ElevatorConstants.kElevatorMinLowPosition) {
                leftElevatorMotor.set(ElevatorConstants.kElevatorDownSpeed);
            } else {
                leftElevatorMotor.set(ElevatorConstants.kElevatorMaintainPositionSpeed);

            }
        } else {
            leftElevatorMotor.set(ElevatorConstants.kElevatorDownSpeed);
        }
    }

    public void maintainElevatorPosition() {
        double rightElevatorPosition = rightElevatorEncoder.getPosition();
        // SmartDashboard.putNumber("Left Elevator Position", leftElevatorEncoder.getPosition());
        SmartDashboard.putNumber("Right Elevator Position", rightElevatorEncoder.getPosition());
        leftElevatorMotor.set(ElevatorConstants.kElevatorMaintainPositionSpeed);
    }

    public void toggleGuard() {
        enableGuards = !enableGuards;
    }
}