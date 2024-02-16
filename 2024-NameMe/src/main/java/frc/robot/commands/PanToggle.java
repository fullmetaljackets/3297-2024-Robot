package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.ShooterPan;

/**
 *
 */
public class PanToggle extends InstantCommand {
    private final ShooterPan m_shooterPan;

    public PanToggle(ShooterPan subsystem) {
        m_shooterPan = subsystem;
        addRequirements(m_shooterPan);
    }

    // Called once when this command runs
    @Override
    public void initialize() {
        m_shooterPan.my_PanToggle();
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
