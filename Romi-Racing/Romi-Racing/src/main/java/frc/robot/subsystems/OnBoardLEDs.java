// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OnBoardLEDs extends SubsystemBase {

  private DigitalOutput red_LED = null;
  private DigitalOutput green_LED = null;
  private DigitalOutput blue_LED = null;

  public OnBoardLEDs(int red_pin, int green_pin, int blue_pin) {
    red_LED = new DigitalOutput(red_pin)
    green_LED = new DigitalOutput(green_pin);
    blue_LED = new DigitalOutput(blue_pin);
  }

  public void enableLEDs(boolean enable_red, boolean enable_green, boolean enable_blue)
  {
    red_LED.set(enable_red);
    green_LED.set(enable_green);
    blue_LED.set(enable_blue);
  }

  @Override
  public void periodic() {}
}
