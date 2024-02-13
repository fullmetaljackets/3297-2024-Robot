package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Shooter1Out;
import frc.robot.commands.Shooter2Out;
import frc.robot.subsystems.Shooter;

public class ShooterOut extends SequentialCommandGroup {
    
    public ShooterOut(Shooter s_Shooter){

        addCommands(
            //shooter motor 1&2 out
            new Shooter1Out (-1, s_Shooter),
            new Shooter2Out (.95, s_Shooter)
        );
    }
}
