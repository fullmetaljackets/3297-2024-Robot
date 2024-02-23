package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Shooter1In;
import frc.robot.commands.Shooter2In;
import frc.robot.commands.ShooterOpen;
import frc.robot.commands.TriggerIn;
import frc.robot.subsystems.ShooterJaws;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterTrigger;
import frc.robot.subsystems.ShooterTwo;

public class ShooterIn extends ParallelCommandGroup {
    
    public ShooterIn(ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, ShooterJaws s_ShooterJaws){

        addCommands(
            //shooter motor 1&2 out
            new ShooterOpen(s_ShooterJaws),
            new Shooter1In(.4, s_ShooterOne),
            new Shooter2In(.4, s_ShooterTwo)
        );
    }

    public ShooterIn(ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, ShooterTrigger s_ShooterTrigger){

        addCommands(
            //shooter motor 1&2 out
            new Shooter1In(.4, s_ShooterOne),
            new Shooter2In(.4, s_ShooterTwo),
            new TriggerIn(-.2, s_ShooterTrigger)
        );
    }
}
