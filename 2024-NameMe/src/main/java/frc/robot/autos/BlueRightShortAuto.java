package frc.robot.autos;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.ZeroPigeon;
import frc.robot.commands.groups.ShooterOut;
import frc.robot.commands.groups.ShooterOutAuto;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterTrigger;
import frc.robot.subsystems.ShooterTwo;
import frc.robot.subsystems.Swerve;

public class BlueRightShortAuto extends SequentialCommandGroup {

    public BlueRightShortAuto(Swerve s_Swerve, ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, ShooterTrigger s_ShooterTrigger){
        TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(Constants.Swerve.swerveKinematics);

        Trajectory myTrajectory =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(.0, 0, new Rotation2d(0)),
                // Back up 2.5 meters
                List.of(new Translation2d(-2.5, 1)),
                // End 3.5 meters straight ahead of where we started, facing forward
                new Pose2d(-4, -1.25, Rotation2d.fromDegrees(50)),
                config);

        var thetaController =
            new ProfiledPIDController(
                Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        SwerveControllerCommand swerveControllerCommand =
            new SwerveControllerCommand(
                myTrajectory,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);


        addCommands(
            // Set our initial position
            new InstantCommand(() -> s_Swerve.setPose(myTrajectory.getInitialPose())),
            // Fire up shooter motors (assumes coast mode)
            new ShooterOut(s_ShooterOne, s_ShooterTwo ).withTimeout(2),
            // Fire up shooter and trigger motors to shoot the note
            new ShooterOutAuto(s_ShooterOne, s_ShooterTwo, s_ShooterTrigger).withTimeout(1),
            // new WaitCommand (1),    // maybe not needed
            // Now move to desired position
            swerveControllerCommand,
            new ZeroPigeon(s_Swerve).withTimeout(0.1)
        );
    }
}