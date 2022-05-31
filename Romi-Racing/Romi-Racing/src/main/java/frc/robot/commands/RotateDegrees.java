package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class RotateDegrees extends CommandBase{ 
    private Drivetrain drivetrain = null;
    private double left_speed = 0;
    private double right_speed = 0;
    private double degrees = 0;

    public RotateDegrees(Drivetrain drivetrain, boolean clock_wise, double speed, double degrees)
    {
        this.drivetrain = drivetrain;
        this.degrees = degrees;
        addRequirements(drivetrain);

        if(clock_wise)
        {
            left_speed = Math.abs(speed);
            right_speed = -Math.abs(speed);
        }
        else
        {
            left_speed = -Math.abs(speed);
            right_speed = Math.abs(speed);
        }
    }

    @Override
    public void initialize()
    {
        drivetrain.tankDrive(0, 0);
        drivetrain.resetGyro();
    }

    @Override
    public void execute()
    {
        drivetrain.tankDrive(left_speed, right_speed);
    }

    @Override
    public void end(boolean interrupted)
    {
        drivetrain.tankDrive(0, 0);
    }

    @Override
    public boolean isFinished()
    {
        return degrees <= Math.abs(drivetrain.getGyroAngleZ());
    }
}