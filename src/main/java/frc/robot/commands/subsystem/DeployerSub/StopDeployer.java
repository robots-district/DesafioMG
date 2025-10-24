package frc.robot.commands.subsystem.DeployerSub;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DeployerSub;

public class StopDeployer extends Command
{
    private DeployerSub deployerSub;

    public StopDeployer(DeployerSub deployerSub)
    {
        this.deployerSub = deployerSub;
        addRequirements(deployerSub);
    }

    @Override
    public void initialize() {
        deployerSub.stopDeployer();
    }

    @Override
    public void execute(){
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return true;
    }
}
