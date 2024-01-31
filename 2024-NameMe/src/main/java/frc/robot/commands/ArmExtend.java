package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.Arm;

/**
 *
 */
public class ArmExtend extends InstantCommand {
    private final Arm m_arm;

    public ArmExtend(Arm subsystem) {
        m_arm = subsystem;
        addRequirements(m_arm);
    }

    // Called once when this command runs
    @Override
    public void initialize() {
        m_arm.my_ArmExtend();
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
