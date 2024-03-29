package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

/**
 *
 */
public class Arm extends SubsystemBase {
    private DoubleSolenoid armValve;
 
    /**
    *
    */
    public Arm() {
        armValve = new DoubleSolenoid(15, PneumaticsModuleType.REVPH, 0, 1);
        armValve.set(Value.kForward);
        addChild("armValve", armValve);
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void my_ArmExtend(){
        armValve.set(Value.kReverse);
    }
    public void my_ArmRetract(){
        armValve.set(Value.kForward);
    }
    public void my_ArmToggle(){
        armValve.toggle();
    }
}

