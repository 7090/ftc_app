package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by TeamFAR on 2/22/2017.
 * Adds several methods related to drivetrain movement. Also includes some smaller utility methods.
 * Call with LinearOpMode for access to autonomous functions such as drivetrainMoveInches
 */

public class DriveTrainResources {
    private DcMotor left_drive;
    private DcMotor right_drive;
    private LinearOpMode linopmode;

    //For autonomous use
    public DriveTrainResources(DcMotor left_drive, DcMotor right_drive, LinearOpMode linopmode){
        this.left_drive = left_drive;
        this.right_drive = right_drive;
        this.linopmode = linopmode;
    }

    //For non-autonomous use
    public DriveTrainResources(DcMotor left_drive, DcMotor right_drive){
        this.left_drive = left_drive;
        this.right_drive = right_drive;
    }
    /**
     * Method name: drivetrainMoveTime
     * Purpose: Provides two methods for drivetrain movement with time as the control.
     * Description: These two methods work together to make complex drivetrain calls cleaner and
     * easier. The 2-argument method will pass a dummy value to the timer in the 3-argument method,
     * triggering the timer to be disabled. The 3-argument method includes a timer function, where
     * time is in ms.
     * Modified: 2/19/2017
     * @param RP: Right motor power
     * @param LP: Left motor power
     * @param timer: Time to run motors, auto-stops after this time. Time is in ms.
     */

    public void drivetrainMoveTime (double RP, double LP, double timer) {
        //If no time is provided, run without timer - Will 19/2/17
        if (timer == 1234567){
            left_drive.setPower(LP);
            right_drive.setPower(RP);
        }

        //If time is passed, run with timer - Will 19/2/17
        else{
            double cutoff = System.currentTimeMillis() + timer;
            while (cutoff > System.currentTimeMillis()){
                left_drive.setPower(LP);
                right_drive.setPower(RP);
            }
            turnOffWheels();
        }

    }

    public void drivetrainMoveTime(double RP, double LP){
        drivetrainMoveTime(RP,LP,1234567);
    }


    /**
     * Method name: driveDirectionTime
     * Purpose: To provide a single method for common drivetrain movements. For more complex
     * movement, please use drivetrainMove
     * Description: If passed a char signifying the direction to move and a time to move, it will
     * move the drivetrain for that time, in that direction
     * Modified: 2/20/17
     * @param direction: A char value, can be 'f', 'b', 'l', or 'r'.
     * @param time: Time to move wheels. Time is in ms.
     */
    private void driveDirectionTime(char direction, double time){
        direction = java.lang.Character.toLowerCase(direction);
        switch (direction){
            case ('f'): drivetrainMoveTime(1,1,time);
                break;
            case ('b'): drivetrainMoveTime(-1,-1,time);
                break;
            case ('l'): drivetrainMoveTime(-1,1,time);
                break;
            case ('r'): drivetrainMoveTime(1,-1,time);
                break;
            default: turnOffWheels();
                break;
        }
    }


    /**
     * Method name: turnOffWheels
     * Purpose: To turn off the drive wheels
     * Description: Passes 0 to the right and left drive motors
     * Modified: 02/20/17
     */

    void turnOffWheels (){
        left_drive.setPower(0);
        right_drive.setPower(0);
    }



    /**
     * Method name: drivetrainMoveInches
     * Purpose: Moves the right and left drivetrains by a user-specified number of inches
     * Description: This method is a simple way to do relatively complex autonomous opmodes. It
     * automatically selects the correct direction for the wheels to turn, then turns them in that
     * direction until the distance target has been met
     * @param leftInches: The number of inches for the left drivetrain to move
     * @param rightInches: The number of inches for the right drivetrain to move
     * Modified: 2/22/17
     */
    void drivetrainMoveInches (double leftInches, double rightInches){
        //Set the power based on the distance
        double RP;
        double LP;
        if(rightInches<0){RP = -1;}
        else {RP = 1;}
        if(leftInches<0){LP = -1;}
        else {LP = 1;}

        //fudgeFactor is for slop in chain
        final double ticksPerRev = 1478.4;
        final int fudgeFactor = 100;
        //// TODO: 2/22/2017 Add the constants back in and fix the math
        //1.3 is for the drivetrain reduction (1.3 motor revs for each wheel rev), 12.12 is the
        // circumfrence of the center wheels in inches
        final double ticksPerIn = (ticksPerRev*1.3)/(12.12);
        int leftTicksGoal = (int)(leftInches * ticksPerIn) + right_drive.getCurrentPosition() + fudgeFactor;
        int rightTicksGoal = (int)(rightInches * ticksPerIn) + right_drive.getCurrentPosition() + fudgeFactor;

        // Check if the opmode is active (Don't want the robot moving after the 30 second limit),
        // then set target ticks and run to that position
        // // TODO: 2/22/2017 Add in timeout to prevent an infinite loop of waiting for the motors to go one tick...
        if (linopmode.opModeIsActive()){
            left_drive.setTargetPosition(leftTicksGoal);
            right_drive.setTargetPosition(rightTicksGoal);
            left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            left_drive.setPower(LP);
            right_drive.setPower(RP);
            while (left_drive.isBusy() || right_drive.isBusy()){
                //This is so that the motors actually run, instead of just sitting there
            }
            turnOffWheels();
            left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    private void drivetrainMoveTicks (int rightTicks, int leftTicks, boolean isAdditive){
    //// TODO: 2/22/2017 Write this method
    }

    void drivetrainTurnDegrees (int turnDegrees){
        final static double
    }

}
