// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.subsystems.DriveTrainSystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class DriveWithJoystick extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final DriveTrainSystem driveTrainSystem;
  double drive;
  double turn;
  double velocidade;
  boolean definirModos;

  private Joystick joystick1;
  
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveWithJoystick(DriveTrainSystem driveTrainSystem, Joystick joy1) {
    this.driveTrainSystem = driveTrainSystem;
    this.joystick1 = joy1;

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrainSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize()
   {
    drive = 0;
    turn = 0;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if(joystick1.getRawButton(5)){
      velocidade = 0.5;

    } else if (joystick1.getRawButton(6)){
      velocidade = 0.7;
    } else if (joystick1.getRawButton(1)){
      velocidade = 0;
    } 
    turn = joystick1.getRawAxis(Constants.ControlsJoystick.leftMotors) * velocidade;
    drive =  joystick1.getRawAxis(Constants.ControlsJoystick.rightMotors) * velocidade;
    
    driveTrainSystem.tankmode(-drive, -turn);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
