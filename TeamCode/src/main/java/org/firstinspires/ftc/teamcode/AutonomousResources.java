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
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by TeamFAR on 2/20/2017.
 * If all goes well, this will be a library of useful code snippets for autonomous
 *
 */
public class AutonomousResources {
    private DcMotor left_drive;
    private DcMotor right_drive;
    private DcMotor motorPusher;
    private DcMotor motorShooter;
    private DcMotor motorLoader;
    private ColorSensor sensorColorBeacon;
    private ColorSensor sensorColorLine;
    private TouchSensor sensorTouchPusher;
    private LinearOpMode linopmode;

        public AutonomousResources (DcMotor left_drive, DcMotor right_drive, DcMotor motorPusher,DcMotor motorShooter, DcMotor motorLoader, TouchSensor sensorTouchPusher, ColorSensor sensorColorBeacon, ColorSensor sensorColorLine, LinearOpMode linopmode){
            this.left_drive = left_drive;
            this.right_drive = right_drive;
            this.motorPusher = motorPusher;
            this.motorShooter = motorShooter;
            this.motorLoader = motorLoader;
            this.sensorTouchPusher = sensorTouchPusher;
            this.sensorColorBeacon = sensorColorBeacon;
            this.sensorColorLine = sensorColorLine;
            this.linopmode = linopmode;
        }

    DriveTrainResources DTR = new DriveTrainResources(left_drive, right_drive, linopmode);



    void pushCapBall (){
//todo dedicated capball pushing code here. - Will 19/2/17
    }


    void findWhiteLine (){
        boolean isDone = false;
        long cutoff = 0;

        while (!isDone || (cutoff + 1000) < System.currentTimeMillis()) {
            DTR.drivetrainMove(.4,.4);
            if (sensorColorLine.equals(16)) {
                if (!isDone) {
                    isDone = true;
                    cutoff = System.currentTimeMillis();
                }
            }
            else {
                isDone = false;
            }

        }
        DTR.turnOffWheels();
//todo line finding and following code here - Will 19/2/17
    }


    void senseColorRamp (){
//todo ramp colour sensor code here. - Will 19/2/17
    }


    /**
     * Method Name: launchParticles
     * Purpose: Launching Particles
     * Description: Runs shooter for 4 seconds total at full power
     * Date Modified: 1/13/17
     */
    void launchParticles (){
        motorShooter.setPower(1);

        //wait .75 seconds for the shooter motor to spin up, then start the loader motor
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        motorLoader.setPower(-1);

        //wait 2 secs to shoot the balls, then turn it all off
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        motorShooter.setPower(0);
        motorLoader.setPower(0);

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
    void runPusher (boolean isSensorTouchUsed, char direction, double time){
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


    void pushBeacon () {


    }

}
