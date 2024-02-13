package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Shooter1In;
import frc.robot.commands.Shooter2In;
import frc.robot.subsystems.Shooter;

public class ShooterIn extends SequentialCommandGroup {
    
    public ShooterIn(Shooter s_Shooter){

        addCommands(
            //shooter motor 1&2 in
            new Shooter1In (.4, s_Shooter).withTimeout(0.25),
            new Shooter2In (-0.4, s_Shooter).withTimeout(0.25),
            new Shooter1In (.4, s_Shooter).withTimeout(0.25),
            new Shooter2In (-0.4, s_Shooter).withTimeout(0.25),
            new Shooter1In (.4, s_Shooter).withTimeout(0.25),
            new Shooter2In (-0.4, s_Shooter).withTimeout(0.25),
            new Shooter1In (.4, s_Shooter).withTimeout(0.25),
            new Shooter2In (-0.4, s_Shooter).withTimeout(0.25),
            new Shooter1In (.4, s_Shooter).withTimeout(0.25),
            new Shooter2In (-0.4, s_Shooter).withTimeout(0.25),
            new Shooter1In (.4, s_Shooter).withTimeout(0.25),
            new Shooter2In (-0.4, s_Shooter).withTimeout(0.25)
        );
    }
}
