package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AimAmp;
import frc.robot.commands.ArmExtend;
import frc.robot.commands.ElevatorRaise;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ShooterJaws;
import frc.robot.subsystems.ShooterPan;
import frc.robot.subsystems.ShooterTrigger;

public class ScoreTrapPrep extends SequentialCommandGroup {

    public ScoreTrapPrep(ShooterTrigger s_ShooterTrigger, Arm s_Arm, Elevator s_Elevator, ShooterJaws s_ShooterJaws, ShooterPan s_ShooterPan){
        addCommands(
        // Aim Amp
        new AimAmp(s_ShooterPan),
        //Extend arm
        new ArmExtend(s_Arm),
        //Raise Elevator
        new ElevatorRaise(-0.25,s_Elevator).withTimeout(2)
        );
    }
}

