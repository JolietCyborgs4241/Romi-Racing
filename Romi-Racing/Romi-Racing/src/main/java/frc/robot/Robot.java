// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.LinkedList;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.commands.DriveInches;
import frc.robot.commands.DriveLEDPattern;
import frc.robot.commands.RotateDegrees;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.OnBoardLEDs;

public class Robot extends TimedRobot {

  private XboxController controller = new XboxController(Constants.JOYSTICK_IDS.CONTROLLER);
  private LinkedList<Integer> color_pattern = new LinkedList<Integer>();
  private int travel_inches = Constants.TRAVEL_DISTANCE.BASE_TRAVEL_INCHES;
  private int inches_displaced = 0;
  private int inputted_order = -1;
  private double speed = 0.75;
  private boolean is_racing = true;

  // Subsystems:
  private Drivetrain drivetrain = new Drivetrain();
  private OnBoardLEDs LEDs = new OnBoardLEDs(Constants.LED_PINS.RED_LED_DIO_PIN, 
  Constants.LED_PINS.GREEN_LED_DIO_PIN, Constants.LED_PINS.BLUE_LED_DIO_PIN);

  private int getRandomNumber(int min, int max) {
    return (int)(min + (Math.random() * (max - min)));
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  private boolean isAwaitingInput()
  {
    if(controller.getAButtonPressed())
    {
      inputted_order = Constants.COPY_PATTERN_ROUND.GREEN_LED_ORDER_ID;
      return false;
    }
    else if(controller.getBButtonPressed())
    {
      inputted_order = Constants.COPY_PATTERN_ROUND.RED_LED_ORDER_ID;
      return false;
    }
    else if(controller.getXButtonPressed())
    {
      inputted_order = Constants.COPY_PATTERN_ROUND.BLUE_LED_ORDER_ID;
      return false;
    }

    return true;
  }

  private void race()
  {
    new DriveLEDPattern(LEDs, color_pattern).schedule();

    boolean successfully_copied_pattern = true;

    for(int i = 0; i < color_pattern.size(); i++)
    {
      while(isAwaitingInput());
      
      if(inputted_order != color_pattern.get(i))
      {
        successfully_copied_pattern = false;
        break;
      }
    }

    if(successfully_copied_pattern)
    {
      new DriveInches(speed, travel_inches, drivetrain).schedule();
      inches_displaced += travel_inches;
      travel_inches += Constants.TRAVEL_DISTANCE.BASE_TRAVEL_INCHES;
    } 
    else
    {
      new DriveInches(-speed, -Constants.TRAVEL_DISTANCE.BASE_TRAVEL_INCHES, drivetrain).schedule();
      inches_displaced -= Constants.TRAVEL_DISTANCE.BASE_TRAVEL_INCHES;
      travel_inches = Constants.TRAVEL_DISTANCE.BASE_TRAVEL_INCHES;
    }   

    color_pattern.addLast(getRandomNumber(0, 2));
  }

  private void finallyGotGood()
  {
    LinkedList<Integer> got_good_pattern = new LinkedList<Integer>();

    for(int i = 0; i < Constants.COPY_PATTERN_ROUND.ORDERS_LISTED.length; i++)
    {
      got_good_pattern.addLast(Constants.COPY_PATTERN_ROUND.ORDERS_LISTED[i]);
    }

    new ParallelCommandGroup(new DriveLEDPattern(LEDs, got_good_pattern), 
    new RotateDegrees(drivetrain, true, speed, 820)).schedule(); 
  }

  @Override
  public void teleopPeriodic() {

    if(is_racing)
    {
      race();
      is_racing = (inches_displaced < Constants.TRAVEL_DISTANCE.RACE_OVERALL_INCHES);
    }
    else
    {
      finallyGotGood();
    }
  }
}