package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AimAmp;
import frc.robot.commands.ArmExtend;
import frc.robot.commands.ElevatorRaise;
import frc.robot.commands.ShooterClose;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;

public class ScoreTrapPrep extends SequentialCommandGroup {

    public ScoreTrapPrep(Shooter s_Shooter, Arm s_Arm, Elevator s_Elevator){
        addCommands(
        //shooter close
        new ShooterClose(s_Shooter),
        // Aim Amp
        new AimAmp(s_Shooter),
        //Extend arm
        new ArmExtend(s_Arm),
        //Raise Elevator
        new ElevatorRaise(-0.25,s_Elevator)
        );
 

    }
}

