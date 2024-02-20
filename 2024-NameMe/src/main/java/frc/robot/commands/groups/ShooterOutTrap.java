package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Shooter1Out;
import frc.robot.commands.Shooter2Out;
import frc.robot.commands.ShooterClose;
import frc.robot.subsystems.ShooterJaws;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterTwo;

public class ShooterOutTrap extends ParallelCommandGroup {
    
    public ShooterOutTrap(ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, ShooterJaws s_ShooterJaws){

        addCommands(
            //shooter motor 1&2 out
            new ShooterClose(s_ShooterJaws),
            new Shooter1Out(-.3, s_ShooterOne),
            new Shooter2Out(-.4, s_ShooterTwo)
        );
    }

    public ShooterOutTrap(ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo){

        addCommands(
            //shooter motor 1&2 out
            new Shooter1Out(-.3, s_ShooterOne),
            new Shooter2Out(-.4, s_ShooterTwo)
        );
    }
}
