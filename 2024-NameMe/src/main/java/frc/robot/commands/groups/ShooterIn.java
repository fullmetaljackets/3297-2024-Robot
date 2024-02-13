package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.Shooter1In;
import frc.robot.commands.Shooter2In;
import frc.robot.subsystems.Shooter;

public class ShooterIn extends ParallelCommandGroup {
    
    public ShooterIn(Shooter s_Shooter){

        addCommands(
            //shooter motor 1&2 in
            new Shooter1In (.4, s_Shooter),
            new Shooter2In (-0.4, s_Shooter)
        );
    }
}
