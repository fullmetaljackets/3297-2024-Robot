package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterJaws;

/**
 *
 */
public class ShooterClose extends InstantCommand {
    private final ShooterJaws m_shooter;

    public ShooterClose(ShooterJaws subsystem) {
        m_shooter = subsystem;
        addRequirements(m_shooter);
    }

    // Called once when this command runs
    @Override
    public void initialize() {
        m_shooter.shooterClose();
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
