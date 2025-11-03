// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.SubConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PivotSub extends SubsystemBase {
  public SparkMax pivotMotor = new SparkMax(SubConstants.PivotConstants.pivotMotorID, MotorType.kBrushless);
  public SparkMax pivotFollowerMotor = new SparkMax(SubConstants.PivotConstants.pivotFollowerMotorID, MotorType.kBrushless);

  private RelativeEncoder pivotEncoder = pivotMotor.getEncoder();
  private final SparkClosedLoopController pidController;

  SparkMaxConfig configSparkPivotMotor = new SparkMaxConfig();
  SparkMaxConfig configSparkFollowerPivotMotor = new SparkMaxConfig();

  public PivotSub() {
    configSparkPivotMotor
      .inverted(true)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(30);
    
    configSparkFollowerPivotMotor
      .inverted(true)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(30)
      .follow(SubConstants.PivotConstants.pivotMotorID, true);

    configSparkPivotMotor.closedLoop
      .p(SubConstants.PivotConstants.kP)
      .i(SubConstants.PivotConstants.kI)
      .d(SubConstants.PivotConstants.kD);

    // configSparkPivotMotor.closedLoop.maxMotion
    //   .allowedClosedLoopError(SubConstants.PivotConstants.PID_TOLERANCE)
    //   .maxVelocity(SubConstants.PivotConstants.maxV)
    //   .maxAcceleration(SubConstants.PivotConstants.maxA);

    pivotMotor.configure(configSparkPivotMotor, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    pivotFollowerMotor.configure(configSparkFollowerPivotMotor, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    pidController = pivotMotor.getClosedLoopController();
    zeroPivot();
  }

  public void periodic() {
    SmartDashboard.putNumber("Encoder pivot", getPivotEncoderPosition());
    SmartDashboard.putNumber("RPM Motor pivot", pivotEncoder.getVelocity());
    SmartDashboard.putNumber("RPS Motor pivot", pivotEncoder.getVelocity() / 60.0);
    SmartDashboard.putNumber("Current Motor pivot", pivotMotor.getOutputCurrent());
    // pivotMotor.setVoltage(-0.4);
  }

  public void stopPivot() {
    pivotMotor.stopMotor();
  }

  public double getPivotEncoderPosition() {
    return pivotEncoder.getPosition();
  }

  public void zeroPivot(){
    pivotEncoder.setPosition(0);
  }

  public void setPosition(double position) {
    pidController.setReference(position, SparkMax.ControlType.kPosition, ClosedLoopSlot.kSlot0, SubConstants.PivotConstants.arbFF);
  }
}
