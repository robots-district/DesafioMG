package frc.robot.commands.PivotSub;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.DeployerSub.DeployIntakePiece;
import frc.robot.commands.DeployerSub.StopDeployer;
import frc.robot.constants.SubConstants;
import frc.robot.subsystems.DeployerSub;
import frc.robot.subsystems.PivotSub;

public class IntakePivotCommand extends Command
{
    private PivotSub pivotSub;
    private DeployerSub deployerSub;

    public IntakePivotCommand(PivotSub pivotSub, DeployerSub deployerSub)
    {
        this.pivotSub = pivotSub;
        this.deployerSub = deployerSub;
        addRequirements(pivotSub);
    }

    @Override
    public void initialize() {
        SmartDashboard.putNumber("Position pivot", SubConstants.PivotState.IntakePos.position);
        pivotSub.setPosition(SubConstants.PivotState.IntakePos.position);

        new DeployIntakePiece(deployerSub, SubConstants.DeployerState.Intake.velocity).schedule();
    }

    @Override
    public void execute()
    {}
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putNumber("Position pivot", SubConstants.PivotState.DefaultPos.position);
        pivotSub.setPosition(SubConstants.PivotState.DefaultPos.position);

        new StopDeployer(deployerSub).schedule();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return !deployerSub.deployerSensor.get();
    }
}
