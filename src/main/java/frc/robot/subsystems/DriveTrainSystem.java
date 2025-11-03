// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.DriveConstants;

import com.revrobotics.spark.SparkBase.ResetMode;


public class DriveTrainSystem extends SubsystemBase {

  SparkMax rightMotorFront = new SparkMax(DriveConstants.DriveTrainConstants.rightFrontMotorID, MotorType.kBrushed);
  SparkMax rightMotorBack = new SparkMax(DriveConstants.DriveTrainConstants.rightBackMotorID, MotorType.kBrushed);
  SparkMax leftMotorback = new SparkMax(DriveConstants.DriveTrainConstants.leftFrontMotorID, MotorType.kBrushed);
  SparkMax leftMotorFront = new SparkMax(DriveConstants.DriveTrainConstants.leftBackMotorID, MotorType.kBrushed);

  SparkMaxConfig configSparkMotorEsquerda = new SparkMaxConfig();
  SparkMaxConfig configSparkMotorDireita = new SparkMaxConfig();


  @SuppressWarnings("removal")
  MotorControllerGroup leftMotorControllerGroup = new MotorControllerGroup(leftMotorFront, leftMotorback);

  @SuppressWarnings("removal")
  MotorControllerGroup rightMotorControllerGroup = new MotorControllerGroup(rightMotorFront, rightMotorBack);

  DifferentialDrive differentialDrive = new DifferentialDrive(leftMotorControllerGroup, rightMotorControllerGroup);

  

  public DriveTrainSystem() {
   configSparkMotorDireita
      .inverted(false)
      .idleMode(IdleMode.kBrake);

    configSparkMotorDireita.smartCurrentLimit(60);

    rightMotorFront.configure(configSparkMotorDireita, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    rightMotorBack.configure(configSparkMotorDireita, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    configSparkMotorEsquerda
      .inverted(true)
      .idleMode(IdleMode.kBrake);

    configSparkMotorEsquerda.smartCurrentLimit(60);

    leftMotorFront.configure(configSparkMotorEsquerda, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    leftMotorback.configure(configSparkMotorEsquerda, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  }


  @Override
  public void periodic() {
    
  }

  public void arcadeMode(double drive, double turn){
    differentialDrive.arcadeDrive(drive, turn);
  }

  public void tankmode(double left, double right){
    differentialDrive.tankDrive(left, right);
  }
  public void stop(){
    differentialDrive.stopMotor(); 
  }

}
