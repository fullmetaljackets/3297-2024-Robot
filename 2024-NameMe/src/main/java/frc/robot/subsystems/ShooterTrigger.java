package frc.robot.subsystems;


import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


/**
 *
 */
public class ShooterTrigger extends SubsystemBase{ 
   
    private CANSparkMax TrigerMotor;

    /**
    *
    */
    public ShooterTrigger() {

        TrigerMotor = new CANSparkMax(13, MotorType.kBrushless);   
        TrigerMotor.restoreFactoryDefaults();  
        TrigerMotor.setInverted(false);
        TrigerMotor.setIdleMode(IdleMode.kBrake);
        TrigerMotor.burnFlash();
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
}

