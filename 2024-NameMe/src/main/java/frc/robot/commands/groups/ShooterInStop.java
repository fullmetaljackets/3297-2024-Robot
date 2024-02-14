package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Shooter1In;
import frc.robot.commands.Shooter2In;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterTwo;

public class ShooterInStop extends ParallelCommandGroup {
    
    public ShooterInStop(ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo){

        addCommands(
            //shooter motor 1&2 out
            new Shooter1In(0, s_ShooterOne),
            new Shooter2In(0, s_ShooterTwo)
        );
    }
}
