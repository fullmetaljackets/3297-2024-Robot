package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.ShooterPan;

/**
 *
 */
public class AimSpeaker extends InstantCommand {
    private final ShooterPan m_shooterTrigger;

    public AimSpeaker(ShooterPan subsystem) {
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
