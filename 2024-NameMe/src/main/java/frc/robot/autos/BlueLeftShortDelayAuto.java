package frc.robot.autos;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
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

public class BlueLeftShortDelayAuto extends SequentialCommandGroup {

    public BlueLeftShortDelayAuto(Swerve s_Swerve, ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, ShooterTrigger s_ShooterTrigger){
        TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(Constants.Swerve.swerveKinematics);

            Trajectory myTrajectory1 =
                TrajectoryGenerator.generateTrajectory(
                    // Start at the origin facing the +X direction
                    new Pose2d(.0, 0, new Rotation2d(0)),
                    // Pass through these two interior waypoints, making an 's' curve path
                    List.of(),
                    // Back up 0.66 meters
                    new Pose2d(-0.66, -0.33, Rotation2d.fromDegrees(0)),
                    config);
    
            Trajectory myTrajectory2 =
                TrajectoryGenerator.generateTrajectory(
                    // Start at the origin facing the +X direction
                    new Pose2d(-0.66, -0.33, new Rotation2d(0)),
                    // Pass through these two interior waypoints, making an 's' curve path
                    List.of(),
                    // End 3 meters towards middle of field facing forward
                    new Pose2d(-2.66, 2, Rotation2d.fromDegrees(-50)),
                    config);
    
            var thetaController =
            new ProfiledPIDController(
                Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        SwerveControllerCommand swerveControllerCommand1 =
            new SwerveControllerCommand(
                myTrajectory1,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);

        SwerveControllerCommand swerveControllerCommand2 =
            new SwerveControllerCommand(
                myTrajectory2,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);


        addCommands(
            // Set our initial position
            new InstantCommand(() -> s_Swerve.setPose(myTrajectory1.getInitialPose())),
            // Fire up shooter motors (assumes coast mode)
            new ShooterOut(s_ShooterOne, s_ShooterTwo ).withTimeout(2),
            // Fire up shooter and trigger motors to shoot the note
            new ShooterOutAuto(s_ShooterOne, s_ShooterTwo, s_ShooterTrigger).withTimeout(1),
            // new WaitCommand (1),
            // Move to the side and wait 
            swerveControllerCommand1,
            new WaitCommand (8),
            // Move to final position
            swerveControllerCommand2,
            new ZeroPigeon(s_Swerve).withTimeout(0.1)
        );
    }
}