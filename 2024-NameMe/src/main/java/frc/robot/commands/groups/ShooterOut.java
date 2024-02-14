package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Shooter1Out;
import frc.robot.commands.Shooter2Out;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterTwo;

public class ShooterOut extends ParallelCommandGroup {
    
    public ShooterOut(ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo){

        addCommands(
            //shooter motor 1&2 out
            new Shooter1Out(-.7, s_ShooterOne),
            new Shooter2Out(1, s_ShooterTwo)
        );
    }
}
