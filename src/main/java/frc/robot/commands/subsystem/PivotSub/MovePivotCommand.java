package frc.robot.commands.subsystem.PivotSub;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.constants.SubConstants;
import frc.robot.subsystems.PivotSub;

public class MovePivotCommand extends Command
{
    private PivotSub pivotSub;
    private double position;

    public MovePivotCommand(PivotSub pivotSub, double position)
    {
        this.pivotSub = pivotSub;
        this.position = position;
        addRequirements(pivotSub);
    }

    @Override
    public void initialize() {
        SmartDashboard.putNumber("Position pivot", position);
        pivotSub.setPosition(position);
    }

    @Override
    public void execute()
    {}
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(pivotSub.getPivotEncoderPosition() - position) < SubConstants.PivotConstants.PID_TOLERANCE;
    }
}
