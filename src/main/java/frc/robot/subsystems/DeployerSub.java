// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.SubConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DeployerSub extends SubsystemBase {
  public SparkMax deployerMotor = new SparkMax(SubConstants.DeployerConstants.deployerMotorID, MotorType.kBrushless);
  public SparkMax deployerMotorFollower = new SparkMax(SubConstants.DeployerConstants.deployerMotorFollowerID, MotorType.kBrushless);

  private RelativeEncoder deployerEncoder = deployerMotor.getEncoder();

  SparkMaxConfig configSparkDeployerMotor = new SparkMaxConfig();
  SparkMaxConfig configSparkFollowerDeployerMotor = new SparkMaxConfig();

  public DigitalInput deployerSensor;

  public DeployerSub() {
    configSparkDeployerMotor
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(60);
    
    configSparkFollowerDeployerMotor
      .inverted(false)
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(30)
      .follow(SubConstants.DeployerConstants.deployerMotorID, false);

    deployerMotor.configure(configSparkDeployerMotor, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    deployerMotorFollower.configure(configSparkFollowerDeployerMotor, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    deployerSensor = new DigitalInput(1);
    
    zeroDeployer();
  }

  public void periodic() {
    SmartDashboard.putBoolean("DeployerSensor", deployerSensor.get());

    SmartDashboard.putNumber("Encoder deployer", getDeployerEncoderPosition());
    SmartDashboard.putNumber("RPM Motor deployer", deployerEncoder.getVelocity());
    SmartDashboard.putNumber("RPS Motor deployer", deployerEncoder.getVelocity() / 60.0);
    SmartDashboard.putNumber("Current Motor deployer", deployerMotor.getOutputCurrent());
    // neoMotor.setVoltage(0.125);
  }

  public void stopDeployer() {
    deployerMotor.stopMotor();
  }

  public double getDeployerEncoderPosition() {
    return deployerEncoder.getPosition();
  }

  public void zeroDeployer(){
    deployerEncoder.setPosition(0);
  }
}
