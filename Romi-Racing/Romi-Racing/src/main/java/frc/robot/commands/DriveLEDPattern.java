package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.OnBoardLEDs;

public class DriveLEDPattern extends CommandBase
{
    private class LED_Activity
    {
        public boolean red_is_active = false;
        public boolean green_is_active = false;
        public boolean blue_is_active = false;

        public LED_Activity() {}
    }

    public DriveLEDPattern(OnBoardLEDs LEDs, int[] LED_ID_pattern) {
        for(int i = 0; i < LED_ID_pattern.length; i++)
        {
            try
            {
                LED_Activity LED_activity = getLEDActivity(LED_ID_pattern, i);
                LEDs.enableLEDs(LED_activity.red_is_active, LED_activity.green_is_active, LED_activity.blue_is_active);
                Thread.sleep(Constants.COPY_PATTERN_ROUND.SIGNALED_LED_DURATION_MILLIS);
                
            }
            catch(InterruptedException e) {}
        }
    }

    private LED_Activity getLEDActivity(int[] LED_ID_pattern, int pattern_index) {
        LED_Activity LED_activity = new LED_Activity();

        LED_activity.red_is_active = (LED_ID_pattern[pattern_index] == Constants.COPY_PATTERN_ROUND.RED_LED_ORDER_ID);
        LED_activity.green_is_active = (LED_ID_pattern[pattern_index] == Constants.COPY_PATTERN_ROUND.GREEN_LED_ORDER_ID);
        LED_activity.blue_is_active = (LED_ID_pattern[pattern_index] == Constants.COPY_PATTERN_ROUND.BLUE_LED_ORDER_ID);
        
        return LED_activity;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}