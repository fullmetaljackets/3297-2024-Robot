package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.FloorIntake;

/**
 *
 */
public class FloorIntakeIn extends Command {

    private final FloorIntake s_FloorIntake;
    private double m_FloorIntakeSpeed;

    public FloorIntakeIn(double My_FloorIntakeSpeed, FloorIntake subsystem) {

        m_FloorIntakeSpeed = My_FloorIntakeSpeed;

        s_FloorIntake = subsystem;
        addRequirements(s_FloorIntake);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        s_FloorIntake.floorIntakeMotorRun(m_FloorIntakeSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        s_FloorIntake.floorIntakeMotorRun(0);
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
