/**
 *
 */
package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by TeamFAR on 2/20/2017.
 * If all goes well, this will be a library of useful code snippets for autonomous
 *
 */
public class AutonomousResources {
    DcMotor left_drive;
    DcMotor right_drive;
    DcMotor motorPusher;
    DcMotor motorShooter;
    DcMotor motorLoader;
    ColorSensor sensorColorBeacon;
    TouchSensor sensorTouchPusher;
    LinearOpMode linopmode;

        public AutonomousResources (DcMotor left_drive, DcMotor right_drive, DcMotor motorPusher,DcMotor motorShooter, DcMotor motorLoader, LinearOpMode linopmode){
            this.left_drive = left_drive;
            this.right_drive = right_drive;
            this.motorPusher = motorPusher;
            this.motorShooter = motorShooter;
            this.motorLoader = motorLoader;
            this.linopmode = linopmode;
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
     * Method name: turnOffWheels
     * Purpose: To turn off the drive wheels
     * Description: Passes 0 to the right and left drive motors
     * Modified: 02/20/17
     */

    private void turnOffWheels (){
        left_drive.setPower(0);
        right_drive.setPower(0);
    }



    /**
     *
     */
    private void drivetrainMoveInches (double leftInches, double rightInches){
        //Set the power based on the distance
        double RP;
        double LP;
        if(rightInches<0){RP = -1;}
        else {RP = 1;}
        if(leftInches<0){LP = -1;}
        else {LP = 1;}

        //fudgeFactor is for slop in chain, wheel diameter is 98 because of the middle wheels.
        final double ticksPerRev = 1478.4;
        final double drivetrainReduction = 2/3;
        final double wheelDiaMM = 98;
        final int fudgeFactor = 100;
        //Dividing by 2.54 converts to inches
        final double ticksPerIn = ((ticksPerRev*drivetrainReduction)/(wheelDiaMM*3.1415))/2.54;
        int leftTicksGoal = (int)(leftInches * ticksPerIn) + right_drive.getCurrentPosition() + fudgeFactor;
        int rightTicksGoal = (int)(rightInches * ticksPerIn) + right_drive.getCurrentPosition() + fudgeFactor;

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
     * Method Name: extendPusher
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
        final View relativeLayout = ((Activity) linopmode.hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);
        boolean bPrevState = false;
        boolean bCurrState = false;
        boolean bLedOn = false;
        sensorColorBeacon.enableLed(bLedOn);  //For future use

        bPrevState = bCurrState;

        Color.RGBToHSV(sensorColorBeacon.red() * 8, sensorColorBeacon.green() * 8, sensorColorBeacon.blue() * 8, hsvValues);


        //// todo: 2/20/2017 Add more descriptive variable names

        int i = 0;
        int k;
        while(linopmode.opModeIsActive() ) {
            linopmode.telemetry.addData("LED", bLedOn ? "On" : "Off");
            linopmode.telemetry.addData("Red  ", sensorColorBeacon.red());
            linopmode.telemetry.addData("Blue ", sensorColorBeacon.blue());
            linopmode.telemetry.addData("Hue", hsvValues[0]);
            linopmode.telemetry.update();

            while(sensorColorBeacon.blue() > sensorColorBeacon.red()){
                runPusher(false, 'f',100);
                i++;
                if (i% 4 == 0){
                    runPusher(false,'b',100);
                    runPusher(false,'b',100);
                }
            }
            for (k = i/2; k>i; k++){
                runPusher(false,'b',100);
            }
        }

        relativeLayout.post(new Runnable() {
            public void run() {relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, value));}
        });

    }

}
