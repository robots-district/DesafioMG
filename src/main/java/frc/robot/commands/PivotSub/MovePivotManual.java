package frc.robot.commands.PivotSub;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PivotSub;

public class MovePivotManual extends Command
{
    private PivotSub pivotSub;
    private double velocity;

    public MovePivotManual(PivotSub pivotSub, double velocity)
    {
        this.pivotSub = pivotSub;
        this.velocity = velocity;
        addRequirements(pivotSub);
    }

    @Override
    public void initialize() {
        pivotSub.pivotMotor.set(velocity);
    }

    @Override
    public void execute()
    {}
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        pivotSub.stopPivot();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
