package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Elevator;


/**
 *
 */
public class ElevatorRaise extends Command {

    private final Elevator s_Elevator;
    private double m_ElevatorVelocity;
 

    public ElevatorRaise(double My_ElevatorVelocity, Elevator subsystem) {
        m_ElevatorVelocity = My_ElevatorVelocity;

        s_Elevator = subsystem;
        addRequirements(s_Elevator);

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        s_Elevator.my_motorrun(m_ElevatorVelocity);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        s_Elevator.my_motorrun(0);
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
