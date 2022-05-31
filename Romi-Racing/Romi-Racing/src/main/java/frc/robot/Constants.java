// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants 
{
    public static final class JOYSTICK_IDS
    {
        public static final int CONTROLLER = 0;
    }

    public static final class LED_PINS
    {
        public static final int RED_LED_DIO_PIN = 8; //EXT PIN: 0
        public static final int GREEN_LED_DIO_PIN = 9; //EXT PIN: 1
        public static final int BLUE_LED_DIO_PIN = 10; //EXT PIN : 2
    }

    public static final class COPY_PATTERN_ROUND
    {
        public static final int RED_LED_ORDER_ID = 0;
        public static final int GREEN_LED_ORDER_ID = 1;
        public static final int BLUE_LED_ORDER_ID = 2; 
        
        public static final int SIGNALED_LED_DURATION_MILLIS = 500;
        public static final int ROUND_COOL_DOWN_MILLIS = 1000;
        public static final int BASE_TRAVEL_INCHES = 2; 
    }
}
