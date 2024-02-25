package frc.robot.commands.groups.archive;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.archive.FloorAmpIn;
import frc.robot.commands.archive.FloorIntakeIn;
import frc.robot.commands.archive.FloorIntakeOut;
import frc.robot.subsystems.archive.FloorIntake;

public class FloorIntakeNote extends ParallelCommandGroup {
    
    // public FloorIntakeNote(FloorIntake s_FloorIntake){

    //     addCommands(
    //         // new FloorIntakeIn(0.25, s_FloorIntake).withTimeout(2),
    //         new FloorAmpIn(1, s_FloorIntake).withTimeout(2),
    //         new WaitCommand(2)
    //         // new FloorIntakeOut(-0.25,s_FloorIntake )
    //     );
    // }
}
