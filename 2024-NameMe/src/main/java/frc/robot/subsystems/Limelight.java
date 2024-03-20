// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  private boolean initialized = true;
  private NetworkTableEntry tTarget = null;
  private NetworkTableEntry tx = null;
  private NetworkTableEntry ty = null;
  private NetworkTableEntry ta = null;

  /** Creates a new Limelight. */
  public Limelight() {
  }

  public void initializeLimeLight() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    turnOnLED();
    try {
      tTarget = table.getEntry("tv");
      tx = table.getEntry("tx");
      ty = table.getEntry("ty");
      ta = table.getEntry("ta");
      
    } catch (Exception e) {
      SmartDashboard.putBoolean("couldn't get nt entries", true);
      return;
    }
    initialized = true;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    try {
      tTarget = table.getEntry("tv");
      tx = table.getEntry("tx");
      ty = table.getEntry("ty");
      ta = table.getEntry("ta");

    } catch (Exception e) {
      return;
    }
    
      double x = tx.getDouble(0.0);
      double y = ty.getDouble(0.0);
      double area = ta.getDouble(0.0);
      boolean isWithinRange = false;

      //change these two constants for calibration (THESE ARE IN INCHES)
      double minimumDistanceToShoot = 1;
      double maximumDistanceToShoot = 1000;

      //this equation calculates distance from the limelight lens to the apriltag horizontally
      //note there might be slight variance from different locations due to the limelight being off-center on the robot
      //consider this during calibration
      //also note all values are inches
      
      //these are constants regarding the limelight location on the robot and apriltag locations on the field
      double limelightMountAngleDegrees = 7.716;
      double limelightLensHeightFromFloor = 7.375; 
      double aprilTagHeightFromFloor = 57.125;  //this is for the center tag on the speaker
 
      //calculating more angles
      double angleToAprilTagDegrees = limelightMountAngleDegrees + y;
      double angleToAprilTagRadians = angleToAprilTagDegrees * (3.14159 / 180.0);

      //calculating distance to apriltag 
      double distanceFromLimelightToAprilTag = (aprilTagHeightFromFloor - limelightLensHeightFromFloor) / Math.tan(angleToAprilTagRadians);
      
      //calculates if the distance is within our range
      if(distanceFromLimelightToAprilTag < minimumDistanceToShoot || distanceFromLimelightToAprilTag > maximumDistanceToShoot) {
        isWithinRange = false;
      }
      else {
        isWithinRange = true;
      }

      //sets isWithinRange to false if no apriltag is found
      //this works because the apriltag should basically never be dead center
      if(x == 0 && y == 0) {
        isWithinRange = false;
      }

      SmartDashboard.putNumber("tY Value", y);
      SmartDashboard.putNumber("tX Value", x);
      SmartDashboard.putNumber("'tA Value", area);
      SmartDashboard.putNumber("distance", distanceFromLimelightToAprilTag);
      SmartDashboard.putBoolean("isWithinRange", isWithinRange);
 
  
  }

  public NetworkTableEntry getEntry(String str) {
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry(str);
  }

  public boolean isInitialized() {
    return this.initialized;
  }

  public boolean hasTargets() {
    boolean hits = false;
    SmartDashboard.putBoolean("isInitialized", isInitialized());
    if (isInitialized()) {
      hits = (getEntry("tv").getDouble(0.0) == 1.0);
    }
    return hits;
  }

  public double x() {
    double dx = 0.0;
    if (isInitialized()) {
      dx = getEntry("tx").getDouble(0.0);
    }
    return dx;
  }

  public double y() {
    double dy = 0.0;
    if (isInitialized()) {
      dy = getEntry("ty").getDouble(0.0);
    }
    return dy;
  }

  public double targetArea() {
    double dArea = 0.0;
    if (isInitialized()) {
      dArea = getEntry("ta").getDouble(0.0);
    }
    return dArea;
  }

  public void turnOnLED() {
    lightLED(LimelightLED.ON);
  }

  public void turnOffLED() {
    lightLED(LimelightLED.OFF);
  }

  private void lightLED(LimelightLED value) {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    table.getEntry("ledMode").setNumber(value.ordinal());
  }
}
