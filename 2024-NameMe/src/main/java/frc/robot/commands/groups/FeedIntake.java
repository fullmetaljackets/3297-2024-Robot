package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AimSpeaker;
import frc.robot.commands.ArmExtend;
import frc.robot.commands.Shooter1In;
import frc.robot.commands.Shooter2In;
import frc.robot.commands.ShooterOpen;
import frc.robot.commands.TriggerIn;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Shooter;

public class FeedIntake extends SequentialCommandGroup {
    
    public FeedIntake (Shooter s_Shooter, Arm s_Arm) {

        addCommands(
            // arm retract
            new ArmExtend(s_Arm),
            // aim speaker
            new AimSpeaker(s_Shooter),
            //shooter close
            new ShooterOpen(s_Shooter),
            //shooter motor 1&2 out
            new Shooter1In (-1, s_Shooter).withTimeout(2),
            new Shooter2In (0.7, s_Shooter).withTimeout(2),
            //trigger out
            new TriggerIn (-.25, s_Shooter).withTimeout(2)

        );
    }
}
