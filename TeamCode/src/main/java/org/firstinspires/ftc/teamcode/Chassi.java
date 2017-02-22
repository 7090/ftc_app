package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by TeamFAR on 2/21/2017.
 */

public class Chassi {
    DcMotor left_drive;
    DcMotor right_drive;
    OpMode opmode;
    LinearOpMode linopmode;
    public Chassi (DcMotor left_drive, DcMotor right_drive, OpMode opmode) {
        this.left_drive = left_drive;
        this.right_drive = right_drive;
        this.opmode = opmode;
    }
    /**
     * Method name: drivetrainMoveTime
     * Purpose: Provides two methods for drivetrain movement with time as the control.
     * Description: These two methods work together to make complex drivetrain calls cleaner and
     * easier. The 2-argument method will pass a dummy value to the timer in the 3-argument method,
     * disabling the timer. The 3-argument method includes a timer function, where
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
     * Method name: turnOffWheels
     * Purpose: To turn off the drive wheels
     * Description: Passes 0 to the right and left drive motors
     * Modified: 02/20/17
     */

    private void turnOffWheels (){left_drive.setPower(0); right_drive.setPower(0); }


    /**
     *
     */
    private void drivetrainMoveInches (double rightInches, double leftInches){
        //Set the power based on the distance
        double RP;
        double LP;
        if(rightInches==0){RP = 0;}
        else if (rightInches>0){RP = 1;}
        else {RP = -1;}
        if(leftInches==0){LP = 0;}
        else if (leftInches>0){LP = 1;}
        else {LP = -1;}

        //fudgeFactor is for slop in chain, wheel diameter is 98 because of the middle wheels.
        final double ticksPerRev = 1478.4;
        final int fudgeFactor = 100;
        //Dividing by 2.54 converts to inches
        final double ticksPerIn = (ticksPerRev*1.3)/(12.12); //
        int leftTicksGoal = (int)(leftInches * ticksPerIn) + left_drive.getCurrentPosition() + fudgeFactor;
        int rightTicksGoal = (int)(rightInches * ticksPerIn) + right_drive.getCurrentPosition() + fudgeFactor;
        int i = 0;
        while (i<100){
            linopmode.telemetry.update();
            i++;
        }
        if (linopmode.opModeIsActive()){
            left_drive.setTargetPosition(leftTicksGoal);
            right_drive.setTargetPosition(rightTicksGoal);
            left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            left_drive.setPower(LP);
            right_drive.setPower(RP);
            while (linopmode.opModeIsActive()&& left_drive.isBusy() || right_drive.isBusy()) {
                linopmode.idle(); //This is so that the motors actually run, instead of just sitting there
            }
            turnOffWheels();
        }
    }


    private void drivetrainMoveTicks (int rightTicks, int leftTicks, boolean isAdditive){

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
}
