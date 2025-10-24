package frc.robot.commands.subsystem.DeployerSub;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DeployerSub;

public class DeployIntakePiece extends Command
{
    private DeployerSub deployerSub;
    private double velocity;

    public DeployIntakePiece(DeployerSub deployerSub, double velocity)
    {
        this.deployerSub = deployerSub;
        this.velocity = velocity;
        addRequirements(deployerSub);
    }

    @Override
    public void initialize() {
        deployerSub.deployerMotor.set(velocity);
    }

    @Override
    public void execute()
    {}
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        deployerSub.stopDeployer();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
