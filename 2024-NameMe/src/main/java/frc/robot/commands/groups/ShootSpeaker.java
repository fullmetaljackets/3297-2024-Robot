package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.AimSpeaker;
import frc.robot.commands.ArmRetract;
import frc.robot.commands.FloorAmpIn;
import frc.robot.commands.FloorIntakeIn;
import frc.robot.commands.FloorIntakeOut;
import frc.robot.commands.Shooter1Out;
import frc.robot.commands.Shooter2Out;
import frc.robot.commands.ShooterClose;
import frc.robot.commands.TriggerOut;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Shooter;

public class ShootSpeaker extends SequentialCommandGroup {
    
    public ShootSpeaker(Shooter s_Shooter, Arm s_Arm){

        addCommands(
            // arm retract
            new ArmRetract(s_Arm),
            // aim speaker
            new AimSpeaker(s_Shooter),
            //shooter close
            new ShooterClose(s_Shooter),
            //shooter motor 1&2 out
            new Shooter1Out (1, s_Shooter).withTimeout(3),
            new Shooter2Out (-0.7, s_Shooter).withTimeout(3),
            new WaitCommand(1),
            //trigger out
            new TriggerOut (0.25, s_Shooter).withTimeout(2)

        );
    }
}
