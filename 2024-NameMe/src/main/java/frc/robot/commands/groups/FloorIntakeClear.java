package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.FloorAmpOut;
import frc.robot.commands.FloorIntakeOut;
import frc.robot.subsystems.FloorIntake;

public class FloorIntakeClear extends SequentialCommandGroup {
    
    public FloorIntakeClear(FloorIntake s_FloorIntake){

        addCommands(
            new FloorIntakeOut(-0.25, s_FloorIntake).withTimeout(3),
            new FloorAmpOut(-0.25, s_FloorIntake).withTimeout(3) 
        );
    }
}
