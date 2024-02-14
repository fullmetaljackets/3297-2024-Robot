package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AimSpeaker;
import frc.robot.commands.ArmExtend;
import frc.robot.commands.Shooter1In;
import frc.robot.commands.Shooter2In;
import frc.robot.commands.ShooterOpen;
import frc.robot.commands.TriggerIn;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ShooterJaws;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterPan;
import frc.robot.subsystems.ShooterTrigger;
import frc.robot.subsystems.ShooterTwo;

public class FeedIntake extends SequentialCommandGroup {
    
    public FeedIntake (ShooterTrigger s_ShooterTrigger, ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, Arm s_Arm, ShooterJaws s_ShooterJaws, ShooterPan s_ShooterPan) {

        addCommands(
            // arm retract
            new ArmExtend(s_Arm),
            // aim speaker
            new AimSpeaker(s_ShooterPan),
            //shooter close
            new ShooterOpen(s_ShooterJaws),
            //shooter motor 1&2 out
            new Shooter1In (-1, s_ShooterOne).withTimeout(2),
            new Shooter2In (0.7, s_ShooterTwo).withTimeout(2),
            //trigger out
            new TriggerIn (-.25, s_ShooterTrigger).withTimeout(2)

        );
    }
}
