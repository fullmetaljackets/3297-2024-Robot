package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.ShooterTrigger;

/**
 *
 */
public class ShooterOpen extends InstantCommand {
    private final ShooterTrigger m_shooter;

    public ShooterOpen(ShooterTrigger subsystem) {
        m_shooter = subsystem;
        addRequirements(m_shooter);
    }

    // Called once when this command runs
    @Override
    public void initialize() {
        m_shooter.shooterOpen();
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
