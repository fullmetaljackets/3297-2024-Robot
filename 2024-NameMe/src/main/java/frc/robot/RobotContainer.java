package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ArmExtend;
import frc.robot.commands.ArmRetract;
import frc.robot.commands.ElevatorLower;
import frc.robot.commands.ElevatorRaise;
import frc.robot.commands.FloorAmpIn;
import frc.robot.commands.FloorAmpOut;
import frc.robot.commands.FloorIntakeIn;
import frc.robot.commands.FloorIntakeOut;
import frc.robot.commands.groups.FloorIntakeClear;
import frc.robot.commands.groups.FloorIntakeNote;
import frc.robot.commands.swerve.TeleopSwerve;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.Swerve;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final XboxController driveStick = new XboxController(0);
    private final XboxController copilotStick = new XboxController(1);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftX.value;
    private final int strafeAxis = XboxController.Axis.kLeftY.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;
    private final int zerogyro = XboxController.Button.kY.value;
    private final int robotcentric = XboxController.Button.kA.value;
    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driveStick, zerogyro);
    private final JoystickButton robotCentric = new JoystickButton(driveStick, robotcentric);

    //Speed Controls
    private final double desiredspeed = 1;
    private final double desiredturnspeed = desiredspeed*0.4;

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    public final Elevator s_Elevator = new Elevator();
    private final Arm s_Arm = new Arm();
    private final FloorIntake s_FloorIntake = new FloorIntake();
    private final SendableChooser<Command> autoChooser;

    /* Limelight Values */


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        NamedCommands.registerCommand("shootSpeaker", new PrintCommand("shootSpeaker"));

        NamedCommands.registerCommand("floorAmpShoot", new FloorAmpIn(0.25, s_FloorIntake));
        NamedCommands.registerCommand("floorIntakeIn", new FloorIntakeIn(0.25, s_FloorIntake));
        NamedCommands.registerCommand("floorIntakeOut", new FloorIntakeOut(-0.25, s_FloorIntake));
        NamedCommands.registerCommand("elevatorRaise", new ElevatorRaise(0.25, s_Elevator));
        NamedCommands.registerCommand("elevatorLower", new ElevatorLower(-0.25, s_Elevator));
        NamedCommands.registerCommand("armExtend", new ArmExtend(s_Arm));
        NamedCommands.registerCommand("armRetract", new ArmRetract(s_Arm));
        NamedCommands.registerCommand("floorIntakeNote", new FloorIntakeNote(s_FloorIntake));


        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> Math.pow(-desiredspeed*driveStick.getRawAxis(strafeAxis), 3), 
                () -> Math.pow(-desiredspeed*driveStick.getRawAxis(translationAxis), 3),
                () -> -desiredturnspeed*driveStick.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
                
            )
            
        );

        // Configure the button bindings
        configureButtonBindings();

        autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Chooser", autoChooser);
    }
    

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        // robotCentric.

        /* CoPilot Buttons */
        // Elevator
        final JoystickButton elevatorRaise = new JoystickButton(copilotStick, XboxController.Button.kA.value);        
        elevatorRaise.whileTrue(new ElevatorRaise(0.25, s_Elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

        final JoystickButton elevatorLower = new JoystickButton(copilotStick, XboxController.Button.kB.value);        
        elevatorLower.whileTrue(new ElevatorLower(-0.25, s_Elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf)); 

        // Arm
        final JoystickButton armExtend = new JoystickButton(copilotStick, XboxController.Button.kY.value);        
        armExtend.onTrue(new ArmExtend(s_Arm).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

        final JoystickButton armRetract = new JoystickButton(copilotStick, XboxController.Button.kX.value);        
        armRetract.onTrue(new ArmRetract(s_Arm).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

        // Floor Intake
        final JoystickButton floorIntakeNote =new JoystickButton(driveStick, XboxController.Button.kRightBumper.value);
        floorIntakeNote.onTrue(new FloorIntakeNote(s_FloorIntake).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

        final JoystickButton floorIntakeClear =new JoystickButton(driveStick, XboxController.Button.kLeftBumper.value);
        floorIntakeClear.onTrue(new FloorIntakeClear(s_FloorIntake).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

        final JoystickButton floorIntakeShoot =new JoystickButton(driveStick, XboxController.Axis.kRightTrigger.value);
        floorIntakeShoot.onTrue(new FloorAmpOut(0.25,s_FloorIntake).withInterruptBehavior(InterruptionBehavior.kCancelSelf).withTimeout(1));

    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        //return new exampleAuto(s_Swerve); 

        // return new PathPlannerAuto("Test 2");

        // PathPlannerPath  path = PathPlannerPath.fromPathFile("Test path 1");
        // return AutoBuilder.followPath(path);

        return autoChooser.getSelected();
    }

}
