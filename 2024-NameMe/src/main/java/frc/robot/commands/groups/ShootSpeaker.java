package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.AimSpeaker;
import frc.robot.commands.ArmRetract;
import frc.robot.commands.Shooter1Out;
import frc.robot.commands.Shooter2Out;
import frc.robot.commands.ShooterClose;
import frc.robot.commands.TriggerOut;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ShooterJaws;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterPan;
import frc.robot.subsystems.ShooterTrigger;
import frc.robot.subsystems.ShooterTwo;

public class ShootSpeaker extends ParallelCommandGroup {
    
    public ShootSpeaker(ShooterTrigger s_ShooterTrigger, ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, Arm s_Arm, ShooterPan s_ShooterPan, ShooterJaws s_ShooterJaws){

        addCommands(
            // arm retract
            new ArmRetract(s_Arm),
            // aim speaker
            new AimSpeaker(s_ShooterPan),
            //shooter close
            // new ShooterClose(s_ShooterJaws),
            //shooter motor 1&2 out

            new Shooter1Out (1, s_ShooterOne).withTimeout(3),
            new Shooter2Out (-1, s_ShooterTwo).withTimeout(3),
            new WaitCommand(1),
            //trigger out
            new TriggerOut (0.25, s_ShooterTrigger).withTimeout(2)

        );
    }
}
