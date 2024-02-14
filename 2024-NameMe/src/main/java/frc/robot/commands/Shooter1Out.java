package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ShooterOne;


/**
 *
 */
public class Shooter1Out extends Command {

    private final ShooterOne s_ShooterOne;
    private double m_ShooterVelocity;
 

    public Shooter1Out(double ShooterVelocity, ShooterOne subsystem) {
        m_ShooterVelocity = ShooterVelocity;

        s_ShooterOne = subsystem;
        addRequirements(s_ShooterOne);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        s_ShooterOne.shooter1MotorRun(m_ShooterVelocity);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        s_ShooterOne.shooter1MotorRun(0);
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
