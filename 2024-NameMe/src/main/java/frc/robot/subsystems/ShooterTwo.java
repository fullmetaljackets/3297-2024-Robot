package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Top motor
 */
public class ShooterTwo extends SubsystemBase{ 
   
    private CANSparkMax Shooter2Motor;


    /**
    *
    */
    public ShooterTwo() {

        Shooter2Motor = new CANSparkMax(12, MotorType.kBrushless);
        Shooter2Motor.restoreFactoryDefaults();  
        Shooter2Motor.setInverted(false);
        Shooter2Motor.setIdleMode(IdleMode.kCoast);
        Shooter2Motor.burnFlash();
    }

    @Override
    public void periodic() {

    }

    @Override
    public void simulationPeriodic() {

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void shooter2MotorRun(double setpoint){
        Shooter2Motor.set(setpoint);
        //DriverStation.reportError("******** TrigerMotor **************", false);
    }

}


