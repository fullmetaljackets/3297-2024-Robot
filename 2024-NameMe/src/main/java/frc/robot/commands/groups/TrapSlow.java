package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Shooter1Out;
import frc.robot.commands.Shooter2Out;
import frc.robot.commands.ShooterClose;
import frc.robot.commands.TriggerOut;
import frc.robot.subsystems.ShooterJaws;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterTrigger;
import frc.robot.subsystems.ShooterTwo;

public class TrapSlow extends ParallelCommandGroup {
    
    public TrapSlow (ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, ShooterJaws s_ShooterJaws, ShooterTrigger s_ShooterTrigger){

        addCommands(
            //shooter motor 1&2 out
            new ShooterClose(s_ShooterJaws),
            new Shooter1Out(-.1, s_ShooterOne),
            new Shooter2Out(-.2, s_ShooterTwo)
        );
    }

    public TrapSlow(ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, ShooterTrigger s_ShooterTrigger){

        addCommands(
            //shooter motor 1&2 out
            //Bottom
            new Shooter1Out(-.15, s_ShooterOne),
            //Top
            new Shooter2Out(-.15, s_ShooterTwo),
            // new WaitCommand(1),
            new TriggerOut(.1, s_ShooterTrigger)
        );
    }
}
