package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Shooter1Out;
import frc.robot.commands.Shooter2Out;
import frc.robot.commands.ShooterClose;
import frc.robot.commands.TriggerOut;
import frc.robot.subsystems.ShooterJaws;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterTrigger;
import frc.robot.subsystems.ShooterTwo;

public class ShooterOutAmp extends ParallelCommandGroup {
    
    public ShooterOutAmp(ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, ShooterJaws s_ShooterJaws){

        addCommands(
            //shooter motor 1&2 out
            new ShooterClose(s_ShooterJaws),
            new Shooter1Out(-.7, s_ShooterOne),
            new Shooter2Out(-.9, s_ShooterTwo)
        );
    }

    public ShooterOutAmp(ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, ShooterTrigger s_Trigger){

        addCommands(
            //shooter motor 1&2 out
            new Shooter1Out(-.7, s_ShooterOne),
            new Shooter2Out(-.9, s_ShooterTwo),
            new TriggerOut(0.9, s_Trigger)
        );
    }
}
