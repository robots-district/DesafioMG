// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.subsystem.DeployerSub.DeployIntakePiece;
import frc.robot.commands.subsystem.DeployerSub.StopDeployer;
import frc.robot.commands.subsystem.PivotSub.MovePivotCommand;
import frc.robot.commands.subsystem.PivotSub.MovePivotManual;
import frc.robot.constants.SubConstants;
import frc.robot.subsystems.DeployerSub;
import frc.robot.subsystems.PivotSub;

public class RobotContainer {
  public final static PivotSub pivotSub = new PivotSub();
  public final static DeployerSub deployerSub = new DeployerSub();

  public CommandXboxController joy = new CommandXboxController(0);

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {

    joy.button(4).onTrue(new ParallelCommandGroup(new StopDeployer(deployerSub),new MovePivotCommand(pivotSub, SubConstants.PivotState.DefaultPos.position)));
    joy.button(2).onTrue(new ParallelCommandGroup(new StopDeployer(deployerSub),new MovePivotCommand(pivotSub, SubConstants.PivotState.DeployPos.position)));
    joy.button(1).onTrue(new ParallelCommandGroup(new DeployIntakePiece(deployerSub, SubConstants.DeployerState.Intake.velocity),new MovePivotCommand(pivotSub, SubConstants.PivotState.IntakePos.position)));

    joy.button(5).whileTrue(new MovePivotManual(pivotSub, -0.1));
    joy.button(6).whileTrue(new MovePivotManual(pivotSub, 0.1));

    joy.rightTrigger().whileTrue(new DeployIntakePiece(deployerSub, SubConstants.DeployerState.Deploy.velocity));
    joy.leftTrigger().whileTrue(new DeployIntakePiece(deployerSub, SubConstants.DeployerState.Intake.velocity));

    // joy.button(5).onTrue(new SequentialCommandGroup(
    //   new MovePivotCommand(pivotSub, SubConstants.PivotState.Pos1.position),
    //   new WaitCommand(2),
    //   new MovePivotCommand(pivotSub, SubConstants.PivotState.Pos2.position)));
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
