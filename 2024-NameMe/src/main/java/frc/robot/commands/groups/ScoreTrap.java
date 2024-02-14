package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ElevatorLower;
import frc.robot.commands.Shooter1Out;
import frc.robot.commands.Shooter2Out;
import frc.robot.commands.TriggerOut;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterTrigger;
import frc.robot.subsystems.ShooterTwo;

public class ScoreTrap extends ParallelCommandGroup {
    public ScoreTrap(ShooterTrigger s_ShooterTrigger, ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, Elevator s_Elevator){
        addCommands(
        //lower elevator
        new ElevatorLower(0.25,s_Elevator),
        new WaitCommand(2),
        //shoot 
        new Shooter1Out(.7,s_ShooterOne),
        new Shooter2Out(-1, s_ShooterTwo),
        new WaitCommand(1),
        new TriggerOut(-1,s_ShooterTrigger)
        );
 

    }
    

    
}

