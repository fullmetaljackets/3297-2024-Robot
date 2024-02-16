package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve;


/**
 *
 */
public class ZeroPigeon extends Command {

    private final Swerve s_Swerve;
 

    public ZeroPigeon(Swerve subsystem) {

        s_Swerve = subsystem;
        addRequirements(s_Swerve);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        s_Swerve.zeroHeading();
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
       
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
