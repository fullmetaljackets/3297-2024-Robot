package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.ShooterTrigger;

/**
 *
 */
public class AimSpeaker extends InstantCommand {
    private final ShooterTrigger m_shooterTrigger;

    public AimSpeaker(ShooterTrigger subsystem) {
        m_shooterTrigger = subsystem;
        addRequirements(m_shooterTrigger);
    }

    // Called once when this command runs
    @Override
    public void initialize() {
        m_shooterTrigger.panUp();
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
