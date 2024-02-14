package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.ElevatorLower;
import frc.robot.commands.Shooter1Out;
import frc.robot.commands.Shooter2Out;
import frc.robot.commands.TriggerOut;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterTrigger;
import frc.robot.subsystems.ShooterTwo;

public class ScoreTrap extends SequentialCommandGroup {
    public ScoreTrap(ShooterTrigger s_ShooterTrigger, ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, Elevator s_Elevator){
        addCommands(
        //lower elevator
        new ElevatorLower(0.25,s_Elevator),
        new WaitCommand(1),
        //shoot 
        new Shooter1Out(1,s_ShooterOne),
        new Shooter2Out(-.7, s_ShooterTwo),
        new WaitCommand(1),
        new TriggerOut(.25,s_ShooterTrigger)
        );
 

    }
    

    
}

