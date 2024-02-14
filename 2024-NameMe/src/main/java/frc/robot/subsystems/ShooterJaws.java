package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


/**
 *
 */
public class ShooterJaws extends SubsystemBase{ 
   
    private DoubleSolenoid ShooterValve;

    /**
    *
    */
    public ShooterJaws() {        
        ShooterValve = new DoubleSolenoid(15, PneumaticsModuleType.REVPH, 2, 3);
        addChild("armValve", ShooterValve);

    }

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    public void shooterOpen(){
        ShooterValve.set(Value.kForward);
    }
    public void shooterClose(){
        ShooterValve.set(Value.kReverse);
    }

}

