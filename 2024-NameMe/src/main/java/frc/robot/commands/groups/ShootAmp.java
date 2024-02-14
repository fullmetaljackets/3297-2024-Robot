/* 
 *  
*/

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.AimAmp;
import frc.robot.commands.ArmRetract;
import frc.robot.commands.Shooter1Out;
import frc.robot.commands.Shooter2Out;
import frc.robot.commands.ShooterClose;
import frc.robot.commands.TriggerOut;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterTrigger;
import frc.robot.subsystems.ShooterTwo;

public class ShootAmp extends SequentialCommandGroup {
    
    public ShootAmp(ShooterTrigger s_ShooterTrigger, ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, Arm s_Arm){

        addCommands(
            // arm retract
            new ArmRetract(s_Arm),
            // aim speaker
            new AimAmp(s_ShooterTrigger),
            //shooter close
            new ShooterClose(s_ShooterTrigger),
            //shooter motor 1&2 out
            new Shooter1Out (1, s_ShooterOne).withTimeout(3),
            new Shooter2Out (-0.7, s_ShooterTwo).withTimeout(3),
            new WaitCommand(1),
            //trigger out
            new TriggerOut (0.25, s_ShooterTrigger).withTimeout(2)

        );
    }
}

