package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ShooterTwo;


/**
 *
 */
public class Shooter2In extends Command {

    private final ShooterTwo s_ShooterTwo;
    private double m_ShooterVelocity;
 

    public Shooter2In(double ShooterVelocity, ShooterTwo subsystem) {
        m_ShooterVelocity = ShooterVelocity;

        s_ShooterTwo = subsystem;
        addRequirements(s_ShooterTwo);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        s_ShooterTwo.shooter2MotorRun(m_ShooterVelocity);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        s_ShooterTwo.shooter2MotorRun(0);
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
