package org.firstinspires.ftc.teamcode;

/**
 * Created by TeamFAR on 2/19/2017.
 */
/**
 * Program Name: PsynonemousWillEdit.java
 * Programmers: Veronica Watson and William Wakely
 Team: First Appalachian Robotics 7090
 * Purpose: to run a robot during the 30 second autonomous period
 *
 */

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;
//import org.firstinspires.ftc.teamcode.AutonomousResources;

/**
 * Created by TeamFar on 12/21/2016.
 */



@Autonomous(name = "PsynonemousWillEdit")
public class PsynonemousWillEdit extends LinearOpMode {


    private DcMotor left_drive;
    private DcMotor right_drive;
    private DcMotor motorLoader;
    private DcMotor motorShooter;
    private DcMotor motorPusher;
    private ColorSensor sensorColorBeacon;
    private TouchSensor sensorTouchPusher;

    String TeamColor;

    /**
     *
     * Method Name: runOpMode
     * Purpose: Mapping motors.
     * Date Modified: 1/13/17
     */
    public void runOpMode() throws InterruptedException {
        //Initialize hardware devices - Will 19/2/17
        left_drive = hardwareMap.dcMotor.get("leftDrive");
        right_drive = hardwareMap.dcMotor.get("rightDrive");
        motorLoader = hardwareMap.dcMotor.get("loader");
        motorShooter = hardwareMap.dcMotor.get("shooter");
        motorPusher = hardwareMap.dcMotor.get("pusher");
        sensorColorBeacon = hardwareMap.colorSensor.get("sensorColorBeacon");
        sensorTouchPusher = hardwareMap.touchSensor.get("sensorTouchPusher");
        motorShooter.setDirection(DcMotorSimple.Direction.REVERSE);
        left_drive.setDirection(DcMotorSimple.Direction.REVERSE);
        left_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        TeamColor = "Red"; // Defines our team colour. What is this used for, and should this be set via an external source somehow? - Will 19/2/17

        waitForStart();
        /**
        driveDirection('f',1000);
        driveDirection('b',1000);
        driveDirection('l',1000);
        driveDirection('r',1000);
        */
        drivetrainMoveInches(0,0);



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

        //fudgeFactor is for slop in chain, ticksPerRev can be changed for different motors
        final double ticksPerRev = 1478.4;
        final int fudgeFactor = 100;
        //1.3 is for the drivetrain reduction (1.3 motor revs for each wheel rev), 12.12 is the
        // circumfrence of the center wheels in inches
        final double ticksPerIn = (ticksPerRev*1.3)/(12.12);
        int leftTicksGoal = (int)(leftInches * ticksPerIn) + left_drive.getCurrentPosition() + fudgeFactor;
        int rightTicksGoal = (int)(rightInches * ticksPerIn) + right_drive.getCurrentPosition() + fudgeFactor;
        if (opModeIsActive()){
            left_drive.setTargetPosition(leftTicksGoal);
            right_drive.setTargetPosition(rightTicksGoal);
            left_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            right_drive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            left_drive.setPower(LP);
            right_drive.setPower(RP);
            while (opModeIsActive()&& left_drive.isBusy() || right_drive.isBusy()) {
                idle(); //This is so that the motors actually run, instead of just sitting there
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

    public void PushCapBall (){
//todo dedicated capball pushing code here. - Will 19/2/17
    }


    public void FindWhiteLine (){
//todo line finding and following code here - Will 19/2/17
    }


    public void SenseColorRamp (){
//todo ramp colour sensor code here. - Will 19/2/17
    }


    /**
     * Method Name: launchParticles
     * Purpose: Launching Particles
     * Description: Runs shooter for 4 seconds total at full power
     * Date Modified: 1/13/17
     */
    private void launchParticles (){
        motorShooter.setPower(1);

        //wait 1.5 secs
        try {   // Try Catch is used because if it does not sleep, it should continue to the next step
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //wait 2.5 secs
        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        motorShooter.setPower(0.0);

    }

    /**
     * Method Name: runPusher
     * Purpose: Extending the pusher arm
     * Description: Extends the pusher arm, with touch sensor capability as requested by Declan and
     * documented in the notebook
     * Modified: 2/20/17
     *@param isSensorTouchUsed: Sets if the touch sensor capability is used to prevent over-running the lead screw
     *@param direction: Sets the motor direction, 'f' is forwards, 'b' is backwards.
     */
    private  void runPusher (boolean isSensorTouchUsed, char direction, double time){
        short power = 0;
        //Set motor direction
        switch (direction){
            case ('f'): power = 1;
                break;
            case ('b'): power = -1;
                break;
            default: power = 0;
                break;
        }

        //If the touch sensor isn't to be used, don't use it
        if (!isSensorTouchUsed){
            double cutoff = System.currentTimeMillis() + time;
            while (cutoff > System.currentTimeMillis()){
                motorPusher.setPower(power);
            }
            motorPusher.setPower(0);
        }
        //Else, use the touch sensor
        else {
            double cutoff = System.currentTimeMillis() + time;
            while (cutoff > System.currentTimeMillis()&& sensorTouchPusher.isPressed()) {
                motorPusher.setPower(power);
            }
            motorPusher.setPower(0);
        }

    }


    public void PushBeacon () {

        float hsvValues[] = {0f, 0f, 0f};
        final float value[] = hsvValues;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);
        boolean bPrevState = false;
        boolean bCurrState = false;
        boolean bLedOn = false;
        sensorColorBeacon.enableLed(bLedOn);  //For future use

        bPrevState = bCurrState;

        Color.RGBToHSV(sensorColorBeacon.red() * 8, sensorColorBeacon.green() * 8, sensorColorBeacon.blue() * 8, hsvValues);
        //// todo: 2/20/2017 Add more descriptive variable names
        int i = 0;
        int k;
        while(opModeIsActive() ) {
            telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Red  ", sensorColorBeacon.red());
            telemetry.addData("Blue ", sensorColorBeacon.blue());
            telemetry.addData("Hue", hsvValues[0]);
            telemetry.update();

            while(sensorColorBeacon.blue() > sensorColorBeacon.red()){
                runPusher(false, 'f',100);
                i++;
                //Go forward until remainder of 4/i = 0
                if (i% 4 == 0){
                    runPusher(false,'b',100);
                    runPusher(false,'b',100);
                }
            }
            //Go back how many times it's gone out
            for (k = i/2; k>i; k++){
                runPusher(false,'b',100);
            }
        }

        relativeLayout.post(new Runnable() {
            public void run() {relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, value));}
        });

    }

}


