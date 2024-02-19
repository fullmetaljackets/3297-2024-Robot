package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


/**
 *
 */
public class ShooterPan extends SubsystemBase{ 
   
    private DoubleSolenoid PanValve;

    /**
    *
    */
    public ShooterPan() {

        PanValve = new DoubleSolenoid(15, PneumaticsModuleType.REVPH, 4, 5);
        PanValve.set(Value.kForward);
        addChild("armValve", PanValve);

    }

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void panUp(){
        PanValve.set(Value.kReverse);
    }
    public void panDown(){
        PanValve.set(Value.kForward);
    }
    public void my_PanToggle(){
        PanValve.toggle();
    }
}

