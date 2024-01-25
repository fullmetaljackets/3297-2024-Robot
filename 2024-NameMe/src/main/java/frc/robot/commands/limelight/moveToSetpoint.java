// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.limelight;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.LimelightLED;
import frc.robot.subsystems.Swerve;

public class moveToSetpoint extends Command {

  private final Swerve m_swerve;
  private final Limelight m_limelight;

  private PIDController limelightController;
  private double setpoint;

  /** Creates a new moveToSetpoint. */
  public moveToSetpoint(Swerve m_swerve, Limelight m_limelight) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_swerve = m_swerve;
    this.m_limelight = m_limelight;

    addRequirements(m_swerve);
    addRequirements(m_limelight);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
