// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.sensors.RomiGyro;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {

  private static final double counts_per_revolution = 1440.0;
  private static final double wheel_diameter_inches = 2.75591; // 70 mm
  // The Romi has the left and right motors set to
  // PWM channels 0 and 1 respectively
  private final Spark left_motor = new Spark(0);
  private final Spark right_motor = new Spark(1);
  // The Romi has onboard encoders that are hardcoded
  // to use DIO pins 4/5 and 6/7 for the left and right
  private final Encoder left_encoder = new Encoder(4, 5);
  private final Encoder right_encoder = new Encoder(6, 7);
  // Set up the differential drive controller
  private final DifferentialDrive differential_drive = new DifferentialDrive(left_motor, right_motor);
  // Set up the RomiGyro
  private final RomiGyro gyro = new RomiGyro();
  // Set up the BuiltInAccelerometer
  private final BuiltInAccelerometer accelerometer = new BuiltInAccelerometer();

  public Drivetrain() {
    right_motor.setInverted(true);
    left_encoder.setDistancePerPulse((Math.PI * wheel_diameter_inches) / counts_per_revolution);
    right_encoder.setDistancePerPulse((Math.PI * wheel_diameter_inches) / counts_per_revolution);
    resetEncoders();
  }

  public void resetEncoders() {
    left_encoder.reset();
    right_encoder.reset();
  }

  public int getLeftEncoderCount() {
    return left_encoder.get();
  }

  public int getRightEncoderCount() {
    return right_encoder.get();
  }

  public double getLeftDistanceInch() {
    return left_encoder.getDistance();
  }

  public double getRightDistanceInch() {
    return right_encoder.getDistance();
  }

  public double getAverageDistanceInch() {
    return (getLeftDistanceInch() + getRightDistanceInch()) / 2.0;
  }

  public double getAccelX() {
    return accelerometer.getX();
  }

  public double getAccelY() {
    return accelerometer.getY();
  }

  public double getAccelZ() {
    return accelerometer.getZ();
  }

  public double getGyroAngleX() {
    return gyro.getAngleX();
  }

  public double getGyroAngleY() {
    return gyro.getAngleY();
  }

  public double getGyroAngleZ() {
    return gyro.getAngleZ();
  }

  public void resetGyro() {
    gyro.reset();
  }

  public void tankDrive(double left_speed, double right_speed)
  {
    differential_drive.tankDrive(left_speed, right_speed);
  }

  @Override
  public void periodic() {}
}
