package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PneumaticsModuleType;


/**
 *
 */
public class ShooterTrigger extends SubsystemBase{ 
   
    private CANSparkMax TrigerMotor;
    private DoubleSolenoid PanValve;
    private DoubleSolenoid ShooterValve;

    /**
    *
    */
    public ShooterTrigger() {

        TrigerMotor = new CANSparkMax(13, MotorType.kBrushless);   
        TrigerMotor.restoreFactoryDefaults();  
        TrigerMotor.setInverted(false);
        TrigerMotor.setIdleMode(IdleMode.kBrake);
        TrigerMotor.burnFlash();

        PanValve = new DoubleSolenoid(15, PneumaticsModuleType.REVPH, 2, 3);
        addChild("armValve", PanValve);
        
        ShooterValve = new DoubleSolenoid(15, PneumaticsModuleType.REVPH, 4, 5);
        addChild("armValve", ShooterValve);

    }

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void triggerMotorRun(double setpoint){
        TrigerMotor.set(setpoint);
        //DriverStation.reportError("******** TrigerMotor **************", false);
    }
    public void panUp(){
        PanValve.set(Value.kForward);
    }
    public void panDown(){
        PanValve.set(Value.kReverse);
    }
    public void shooterOpen(){
        PanValve.set(Value.kForward);
    }
    public void shooterClose(){
        PanValve.set(Value.kReverse);
    }

}

