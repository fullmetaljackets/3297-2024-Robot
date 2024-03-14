package frc.robot;

import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autos.BlueLeftLongAuto;
import frc.robot.autos.BlueLeftLongDelayAuto;
import frc.robot.autos.BlueLeftShortAuto;
import frc.robot.autos.BlueLeftShortDelayAuto;
import frc.robot.autos.BlueRightLongAuto;
import frc.robot.autos.BlueRightLongDelayAuto;
import frc.robot.autos.BlueRightShortAuto;
import frc.robot.autos.BlueRightShortDelayAuto;
import frc.robot.autos.RedLeftLongAuto;
import frc.robot.autos.RedLeftLongDelayAuto;
import frc.robot.autos.RedLeftShortAuto;
import frc.robot.autos.RedLeftShortDelayAuto;
import frc.robot.autos.RedRightLongAuto;
import frc.robot.autos.RedRightLongDelayAuto;
import frc.robot.autos.RedRightShortAuto;
import frc.robot.autos.RedRightShortDelayAuto;
import frc.robot.commands.ArmExtend;
import frc.robot.commands.ArmRetract;
import frc.robot.commands.ArmToggle;
import frc.robot.commands.ElevatorLower;
import frc.robot.commands.ElevatorRaise;
import frc.robot.commands.PanToggle;
import frc.robot.commands.Shooter1In;
import frc.robot.commands.Shooter1Out;
import frc.robot.commands.Shooter2In;
import frc.robot.commands.Shooter2Out;
import frc.robot.commands.ShooterClose;
import frc.robot.commands.ShooterOpen;
import frc.robot.commands.TriggerIn;
import frc.robot.commands.TriggerOut;
import frc.robot.commands.ZeroPigeon;
import frc.robot.commands.groups.ShooterIn;
import frc.robot.commands.groups.ShooterInStop;
import frc.robot.commands.groups.ShooterOut;
import frc.robot.commands.groups.ShooterOutAmp;
import frc.robot.commands.groups.ShooterOutAuto;
import frc.robot.commands.groups.ShooterOutStop;
import frc.robot.commands.groups.ShooterOutTrap;
import frc.robot.commands.groups.TrapSlow;
import frc.robot.commands.swerve.TeleopSwerve;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ShooterJaws;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterPan;
import frc.robot.subsystems.ShooterTrigger;
import frc.robot.subsystems.ShooterTwo;
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
    private final Joystick joystick = new Joystick(2);
    

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftX.value;
    private final int strafeAxis = XboxController.Axis.kLeftY.value;
    private int rotationAxis = XboxController.Axis.kRightX.value;
    private final int zerogyro = XboxController.Button.kStart.value;
    private final int zerogyro2 = XboxController.Button.kLeftStick.value;
 
    private final boolean useJoystick = false;
    private final int j_translationAxis = Joystick.AxisType.kX.value;
    private final int j_strafeAxis = Joystick.AxisType.kY.value;
    private int j_rotationAxis = Joystick.AxisType.kZ.value;
    private final int j_zerogyro = 12;
 
    private final int robotcentric = XboxController.Button.kA.value;
 
 
    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driveStick, zerogyro);
    private final JoystickButton zeroGyro2 = new JoystickButton(driveStick, zerogyro2);
    private final JoystickButton zeroGyro3 = new JoystickButton(driveStick, j_zerogyro);
    private final JoystickButton robotCentric = new JoystickButton(driveStick, robotcentric);

    //Speed Controls
    private final double desiredspeed = 2;
    private final double desiredturnspeed = desiredspeed*0.4;

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    public final Elevator s_Elevator = new Elevator();
    private final Arm s_Arm = new Arm();
    // private final FloorIntake s_FloorIntake = new FloorIntake();
    private final ShooterOne s_ShooterOne = new ShooterOne();
    private final ShooterTwo s_ShooterTwo = new ShooterTwo();
    private final ShooterTrigger s_ShooterTrigger = new ShooterTrigger();
    private final ShooterPan s_ShooterPan = new ShooterPan();
    private final ShooterJaws s_ShooterJaws = new ShooterJaws();
    private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
    private final SendableChooser<Command> autoChooser;

    /* Limelight Values */


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        NamedCommands.registerCommand("shootSpeaker", new ShooterOutAuto(s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        // NamedCommands.registerCommand("floorIntakeNote", new FloorIntakeNote(s_FloorIntake));
        // NamedCommands.registerCommand("floorAmpShoot", new FloorAmpIn(0.25, s_FloorIntake));
        NamedCommands.registerCommand("zeroGyro", new ZeroPigeon(s_Swerve).withTimeout(1));

        // NamedCommands.registerCommand("floorIntakeIn", new FloorIntakeIn(0.25, s_FloorIntake));
        // NamedCommands.registerCommand("floorIntakeOut", new FloorIntakeOut(-0.25, s_FloorIntake));
        NamedCommands.registerCommand("elevatorRaise", new ElevatorRaise(0.25, s_Elevator));
        NamedCommands.registerCommand("elevatorLower", new ElevatorLower(-0.25, s_Elevator));
        NamedCommands.registerCommand("armExtend", new ArmExtend(s_Arm));
        NamedCommands.registerCommand("armRetract", new ArmRetract(s_Arm));
        NamedCommands.registerCommand("shooter1Out", new Shooter1Out(0.25, s_ShooterOne));
        NamedCommands.registerCommand("shooter1In", new Shooter1In(-0.25, s_ShooterOne));
        NamedCommands.registerCommand("shooter2Out", new Shooter2Out(-0.25, s_ShooterTwo));
        NamedCommands.registerCommand("shooter2In", new Shooter2In(0.25, s_ShooterTwo));
        NamedCommands.registerCommand("shooterOpen", new ShooterOpen(s_ShooterJaws));
        NamedCommands.registerCommand("shooterClose", new ShooterClose(s_ShooterJaws));
        NamedCommands.registerCommand("triggerIn", new TriggerIn(-0.25, s_ShooterTrigger).withTimeout(5));
        NamedCommands.registerCommand("triggerOut", new TriggerOut(0.25, s_ShooterTrigger).withTimeout(2));

        if (useJoystick) {
            s_Swerve.setDefaultCommand(
                new TeleopSwerve(
                    s_Swerve, 
                    () -> Math.pow(-desiredspeed*joystick.getRawAxis(j_strafeAxis), 3), 
                    () -> Math.pow(-desiredspeed*joystick.getRawAxis(j_translationAxis), 3),
                    () -> desiredturnspeed*joystick.getRawAxis(j_rotationAxis), 
                    () -> robotCentric.getAsBoolean()                
                )
            );
        } else {
            s_Swerve.setDefaultCommand(
                new TeleopSwerve(
                    s_Swerve, 
                    () -> Math.pow(-desiredspeed*driveStick.getRawAxis(strafeAxis), 3), 
                    () -> Math.pow(-desiredspeed*driveStick.getRawAxis(translationAxis), 3),
                    () -> desiredturnspeed*driveStick.getRawAxis(rotationAxis), 
                    () -> robotCentric.getAsBoolean()
                )
            );
        }
            

        // Configure the button bindings
        configureButtonBindings();

        /* Pathplanner Auto */
        // autoChooser = AutoBuilder.buildAutoChooser();
        // SmartDashboard.putData("Auto Chooser", autoChooser);

        autoChooser = new SendableChooser<>();
        autoChooser.addOption("Blue Left Long", new BlueLeftLongAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Blue Left Long Delay", new BlueLeftLongDelayAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Blue Left Short", new BlueLeftShortAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Blue Left Short Delay", new BlueLeftShortDelayAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Blue Right Long", new BlueRightLongAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Blue Right Long Delay", new BlueRightLongDelayAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Blue Right Short", new BlueRightShortAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Blue Right Short Delay", new BlueRightShortDelayAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Red Left Long", new RedLeftLongAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Red Left Long Delay", new RedLeftLongDelayAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Red Left Short", new RedLeftShortAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Red Left Short Delay", new RedLeftShortDelayAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Red Right Long", new RedRightLongAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Red Right Long Delay", new RedRightLongDelayAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Red Right Short", new RedRightShortAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.addOption("Red Right Short Delay", new RedRightShortDelayAuto(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        // autoChooser.addOption("Short Right Blue", new shortRightAutoBlue(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        // autoChooser.addOption("Long Right Blue", new longRightAutoBlue(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        // autoChooser.addOption("Short Right Dalay Blue", new shortRightDelayAutoBlue(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        // autoChooser.addOption("short Right Blue Test", new shortRightAutoBlueTest(s_Swerve, s_ShooterOne, s_ShooterTwo, s_ShooterTrigger));
        autoChooser.setDefaultOption("AutonousCommand", getAutonomousCommand());
        SmartDashboard.putData("Auto Mode", autoChooser);
      
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
        zeroGyro2.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        zeroGyro3.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));

        // robotCentric.
        /*
         * COPILOT
         */
        //Copilot Face Buttons 
        // Elevator
        final JoystickButton elevatorRaise = new JoystickButton(copilotStick, XboxController.Button.kB.value);        
        elevatorRaise.whileTrue(new ElevatorRaise(1, s_Elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

        final JoystickButton elevatorLower = new JoystickButton(copilotStick, XboxController.Button.kA.value);
        elevatorLower.whileTrue(new ElevatorLower(-1, s_Elevator).withInterruptBehavior(InterruptionBehavior.kCancelSelf)); 

        // Arm
        final JoystickButton armRetract = new JoystickButton(copilotStick, XboxController.Button.kX.value);        
        armRetract.toggleOnTrue(new ArmToggle(s_Arm).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

        // Pan
        final JoystickButton aim = new JoystickButton(copilotStick, XboxController.Button.kY.value);
        aim.toggleOnTrue(new PanToggle(s_ShooterPan));

        // final JoystickButton shooterToggle = new JoystickButton(copilotStick, XboxController.Button.kRightBumper.value);
        // shooterToggle.onTrue(new ShooterOpen(s_ShooterJaws).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        // shooterToggle.onFalse(new ShooterClose(s_ShooterJaws).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        // shooterToggle.toggleOnTrue(new ShooterToggle())

        // Shooter
        //Copilot Bumpers/Triggers
        final JoystickButton shooterOut =new JoystickButton(copilotStick, XboxController.Button.kRightBumper.value);
        shooterOut.onTrue(new ShooterOut(s_ShooterOne, s_ShooterTwo).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        shooterOut.onFalse(new ShooterOutStop(s_ShooterOne, s_ShooterTwo));

        final JoystickButton trapSlow = new JoystickButton(copilotStick, XboxController.Button.kLeftBumper.value);
        trapSlow.onTrue(new TrapSlow(s_ShooterOne, s_ShooterTwo, s_ShooterTrigger).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        trapSlow.onFalse(new ShooterOutStop(s_ShooterOne, s_ShooterTwo));

        // Floor Intake, Not Testing/Working, No Motors Yet 
        /*final JoystickButton floorIntakeNote =new JoystickButton(copilotStick, XboxController.Button.kRightBumper.value);
        floorIntakeNote.onTrue(new FloorAmpIn(0.80, s_FloorIntake).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        floorIntakeNote.onFalse(new FloorAmpIn(0, s_FloorIntake));

        final JoystickButton floorIntakeClear =new JoystickButton(copilotStick, XboxController.Button.kLeftBumper.value);
        floorIntakeClear.onTrue(new FloorAmpOut(-1, s_FloorIntake).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        floorIntakeClear.onFalse(new FloorAmpOut(0, s_FloorIntake));*/
        

        // final JoystickButton floorIntakeShoot =new JoystickButton(driveStick, XboxController.Axis.kRightTrigger.value);
        // floorIntakeShoot.onTrue(new FloorAmpOut(0.25,s_FloorIntake).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

        /*
         * DRIVER
         */
        //DriveStick Bumpers/Triggers
        //Shooter
    
        final JoystickButton shooterIn = new JoystickButton(driveStick, XboxController.Button.kLeftBumper.value);
        shooterIn.onTrue(new ShooterIn(s_ShooterOne, s_ShooterTwo, s_ShooterTrigger).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        shooterIn.onFalse(new ShooterInStop(s_ShooterOne, s_ShooterTwo));

        final JoystickButton shooterOutTrap = new JoystickButton(driveStick, XboxController.Button.kB.value);
        shooterOutTrap.onTrue(new ShooterOutTrap(s_ShooterOne, s_ShooterTwo, s_ShooterTrigger).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        shooterOutTrap.onFalse(new ShooterOutStop(s_ShooterOne, s_ShooterTwo));

        final JoystickButton shooterOutAmp = new JoystickButton(driveStick, XboxController.Button.kRightBumper.value);
        shooterOutAmp.onTrue(new ShooterOutAmp(s_ShooterOne, s_ShooterTwo, s_ShooterTrigger).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        shooterOutAmp.onFalse(new ShooterOutStop(s_ShooterOne, s_ShooterTwo));

        //Trigger
        //DriveStick Face Buttons
        final JoystickButton triggerIn = new JoystickButton(driveStick, XboxController.Button.kX.value);
        triggerIn.onTrue(new TriggerIn(-0.25, s_ShooterTrigger).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        triggerIn.onFalse(new TriggerIn(0, s_ShooterTrigger));
        // triggerIn.onFalse(new FloorIntakeIn(0, s_FloorIntake)); // trying to shut down floorintake motor that starts mysteriously with trigger in

        final JoystickButton triggerOut = new JoystickButton(driveStick, XboxController.Button.kY.value);
        triggerOut.onTrue(new TriggerOut(1, s_ShooterTrigger).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        triggerOut.onFalse(new TriggerOut(0, s_ShooterTrigger));

        final JoystickButton xxxx = new JoystickButton(driveStick, XboxController.Axis.kRightTrigger.value);
        xxxx.whileTrue(new TriggerIn(-0.25, s_ShooterTrigger).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

        // final JoystickButton brake = new JoystickButton(driveStick, XboxController.Button.kA.value);
        // brake.onTrue(new (s_ShooterPan));

        // Try adding a brake
        // driveStick.a(null).whileTrue(drivetrain.applyRequest(() -> brake));
        // s_Swerve.applyRequest(() -> brake);

        
        /**************************************
         * Logitect X3D Pro Flight Contorller *
         **************************************/
/*
        final JoystickButton j_triggerOut = new JoystickButton(joystick, Joystick.ButtonType.kTrigger.value);
        j_triggerOut.onTrue(new TriggerOut(1, s_ShooterTrigger).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        j_triggerOut.onFalse(new TriggerOut(0, s_ShooterTrigger));

        final JoystickButton j_triggerIn = new JoystickButton(joystick, 3);
        j_triggerIn.onTrue(new TriggerOut(-0.25, s_ShooterTrigger).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        j_triggerIn.onFalse(new TriggerOut(0, s_ShooterTrigger));

        final JoystickButton j_shooterOut =new JoystickButton(joystick, 2);
        j_shooterOut.onTrue(new ShooterOut(s_ShooterOne, s_ShooterTwo).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        j_shooterOut.onFalse(new ShooterOutStop(s_ShooterOne, s_ShooterTwo));
    
        final JoystickButton j_shooterIn = new JoystickButton(joystick, 5);
        j_shooterIn.onTrue(new ShooterIn(s_ShooterOne, s_ShooterTwo).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
        j_shooterIn.onFalse(new ShooterInStop(s_ShooterOne, s_ShooterTwo));
*/
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        // return new shortRightAutoBlue(s_Swerve); 

        // return new PathPlannerAuto("Test 2");

        // PathPlannerPath  path = PathPlannerPath.fromPathFile("Test path 1");
        // return AutoBuilder.followPath(path);

        return autoChooser.getSelected();
    }

}
