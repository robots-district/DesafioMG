// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

/**
 * Classe de constantes
 */
public final class SubConstants {
  public static class PivotConstants {
    public static int pivotMotorID = 2;
    public static int pivotFollowerMotorID = 1;

    public static final double PID_TOLERANCE = 0.1;

    public static final double kP = 0.032;
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double arbFF = 0;
    
    public static final double maxV = 2000;
    public static final double maxA = 6000;
  }

  public static class DeployerConstants {
    public static int deployerMotorID = 3;
    public static int deployerMotorFollowerID = 4;
  }

  public static enum PivotState {
    DefaultPos(0), DeployPos(-6.2), IntakePos(-20);
    public final double position;
      
    private PivotState(double position){
      this.position = position;
    }
  }

  public static enum DeployerState {
    Intake(-0.5), Deploy(0.4);
    public final double velocity; 
      
    private DeployerState(double velocity){
      this.velocity = velocity;
    }
  }
}
  
