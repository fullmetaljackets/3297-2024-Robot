package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.Shooter1Out;
import frc.robot.commands.Shooter2Out;
import frc.robot.commands.ShooterClose;
import frc.robot.commands.TriggerOut;
import frc.robot.subsystems.ShooterJaws;
import frc.robot.subsystems.ShooterOne;
import frc.robot.subsystems.ShooterTrigger;
import frc.robot.subsystems.ShooterTwo;

public class ShooterOutAuto extends ParallelCommandGroup {
    
    public ShooterOutAuto(ShooterOne s_ShooterOne, ShooterTwo s_ShooterTwo, ShooterTrigger s_ShooterTrigger){

        addCommands(
            // new ShooterClose(s_ShooterJaws),
            new Shooter1Out(-1, s_ShooterOne).withTimeout(2),
            new Shooter2Out(-1, s_ShooterTwo).withTimeout(2),
            new WaitCommand(1),
            new TriggerOut(1, s_ShooterTrigger).withTimeout(2)
        );
    }
}