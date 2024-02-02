package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Shooter;


/**
 *
 */
public class TriggerIn extends Command {

    private final Shooter s_Shooter;
    private double m_TriggerVelocity;
 

    public TriggerIn(double TriggerVelocity, Shooter subsystem) {
        m_TriggerVelocity = TriggerVelocity;

        s_Shooter = subsystem;
        addRequirements(s_Shooter);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        s_Shooter.triggerMotorRun(m_TriggerVelocity);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        s_Shooter.triggerMotorRun(0);
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
