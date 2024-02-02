package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.Shooter;

/**
 *
 */
public class ShooterClose extends InstantCommand {
    private final Shooter m_shooter;

    public ShooterClose(Shooter subsystem) {
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
