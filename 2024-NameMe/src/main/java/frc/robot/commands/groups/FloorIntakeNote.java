package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.FloorAmpIn;
import frc.robot.commands.FloorIntakeIn;
import frc.robot.commands.FloorIntakeOut;
import frc.robot.subsystems.FloorIntake;

public class FloorIntakeNote extends ParallelCommandGroup {
    
    public FloorIntakeNote(FloorIntake s_FloorIntake){

        addCommands(
            // new FloorIntakeIn(0.25, s_FloorIntake).withTimeout(2),
            new FloorAmpIn(1, s_FloorIntake).withTimeout(2),
            new WaitCommand(2)
            // new FloorIntakeOut(-0.25,s_FloorIntake )
        );
    }
}
