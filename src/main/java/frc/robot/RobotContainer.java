// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

//import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.DeployerSub.DeployIntakePiece;
import frc.robot.commands.DeployerSub.StopDeployer;
import frc.robot.commands.PivotSub.IntakePivotCommand;
import frc.robot.commands.PivotSub.MovePivotCommand;
import frc.robot.commands.PivotSub.MovePivotManual;
import frc.robot.constants.SubConstants;
import frc.robot.subsystems.DeployerSub;
import frc.robot.subsystems.DriveTrainSystem;
import frc.robot.subsystems.PivotSub;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrainSystem driveTrainSystem = new DriveTrainSystem();
  public final static PivotSub pivotSub = new PivotSub();
  public final static DeployerSub deployerSub = new DeployerSub();

  private Joystick joystick1 = new Joystick(0);
  public CommandXboxController joystick2 = new CommandXboxController(1);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  //private final CommandXboxController m_driverController =
      //new CommandXboxController(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    defaultcommands();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    joystick2.button(4).onTrue(new ParallelCommandGroup(new StopDeployer(deployerSub),new MovePivotCommand(pivotSub, SubConstants.PivotState.DefaultPos.position)));
    joystick2.button(2).onTrue(new ParallelCommandGroup(new StopDeployer(deployerSub),new MovePivotCommand(pivotSub, SubConstants.PivotState.DeployPos.position)));
    joystick2.button(1).onTrue(new IntakePivotCommand(pivotSub, deployerSub));

    joystick2.rightTrigger().whileTrue(new MovePivotManual(pivotSub, -0.15));
    joystick2.leftTrigger().whileTrue(new MovePivotManual(pivotSub, 0.15));

    // joystick2.button(6).whileTrue(new DeployIntakePiece(deployerSub, SubConstants.DeployerState.Deploy.velocity)).onFalse(new MovePivotCommand(pivotSub, SubConstants.PivotState.DefaultPos.position));
    
    joystick2.button(6).whileTrue(new DeployIntakePiece(deployerSub, SubConstants.DeployerState.Deploy.velocity));
    joystick2.button(5).whileTrue(new DeployIntakePiece(deployerSub, SubConstants.DeployerState.Intake.velocity));
  }
  private void defaultcommands(){
    driveTrainSystem.setDefaultCommand(new DriveWithJoystick(driveTrainSystem, joystick1));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //public Command getAutonomousCommand() {

    //return 

  //}
}
