package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class ShooterOne extends SubsystemBase{ 
   
    private CANSparkMax Shooter1Motor;


    /**
    *
    */
    public ShooterOne() {

        Shooter1Motor = new CANSparkMax(11, MotorType.kBrushless);
        Shooter1Motor.restoreFactoryDefaults();  
        Shooter1Motor.setInverted(false);
        Shooter1Motor.setIdleMode(IdleMode.kCoast);
        Shooter1Motor.burnFlash();
    }

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void shooter1MotorRun(double setpoint){
        Shooter1Motor.set(setpoint);
        //DriverStation.reportError("******** TrigerMotor **************", false);
    }

}


