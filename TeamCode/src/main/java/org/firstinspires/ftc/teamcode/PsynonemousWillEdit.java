package org.firstinspires.ftc.teamcode;

/**
 * Created by TeamFAR on 2/19/2017.
 */
/**
 * Program Name: Psynonemous.java
 * Programmer: Veronica Watson
 Team: First Appalachian Robotics 7090
 * Purpose: to run a robot during the 30 second autonomous period
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
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by TeamFar on 12/21/2016.
 */



@Autonomous(name = "Psynonemous")
public class PsynonemousWillEdit extends LinearOpMode {


    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor loader;
    DcMotor shooter;
    DcMotor imPushingYourButton;
    ColorSensor mindReader;
    TouchSensor travelLimit;

    String TeamColor;

    /**
     *
     * Method Name: runOpMode
     * Purpose: Mapping motors.
     * Date Modified: 1/13/17
     */
    public void runOpMode() throws InterruptedException {
        /*Initialize hardware devices*/
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        loader = hardwareMap.dcMotor.get("loader");
        shooter = hardwareMap.dcMotor.get("shooter");
        shooter.setDirection(DcMotorSimple.Direction.REVERSE);
        mindReader = hardwareMap.colorSensor.get("mindReader");
        travelLimit = hardwareMap.touchSensor.get("travelLimit");
        imPushingYourButton = hardwareMap.dcMotor.get("imPushingYourButton");


       /* This is where i call all of my methods, meaning that this is the area where i tell the
       robot specifically what i want it to do */
        TeamColor = "Red";

        waitForStart();

       /* int i;

        for (i = 0; i < 16; i++) {     // This one calls the DriveForward command, making it drive
            DriveForward();            // forward for 16 iterations, meaning it will go through
        }                              // that specific method 16 times.

        for (i = 0; i < 1; i++) {       // this calls the method ActivateRight, by doing this it
            ActivateRight();            // turns the robot to the right.
        }

       PewPewShooter();                 // This calls the method PewPewShooter, which activates the
                                        // shooting mechanisms, launching the particles.

        for (i = 0; i < 16; i++) {      // this drives the robot forward, for the remaining time.
            DriveForward();
        } **/
        PushBeacon();
        //PewPewShooter();
    }






    public void PushCapBall (){

    }

    /**
     *
     * Method Name: PewPewShooter
     * Purpose: Launching Particles
     * Date Modified: 1/13/17
     */
    public void PewPewShooter (){   // this creates a public method.

        shooter.setPower(1);        // this sets the shooter power to 1 (full power)

        try {   // Try Catch is used because if it does not sleep, it should continue to the next
            // step

            Thread.sleep(1500);    // this makes it stop for 1.5 seconds, before continuing.


        } catch (InterruptedException e) {
            e.printStackTrace();        // What this does is print what causes the error.


        }


        try {       // Try Catch is used because if it does not sleep, it should continue to the next
            // step


            Thread.sleep(2500);   // this makes it stop for 1.5 seconds, before continuing.

        } catch (InterruptedException e) {
            e.printStackTrace();        // What this does is print what causes the error.
        }

        shooter.setPower(0.0);      // this stops the motors.

    }


    public void FindWhiteLine (){

    }



    public  void PushyTheButton (){
        imPushingYourButton.setPower(1);
        // motor run .1 seconds
        ElapsedTime eTime = new ElapsedTime();
        eTime.reset();
        while (eTime.time() < 0.1);

        imPushingYourButton.setPower(0);
    }


    public void RejectyTheButton (){
        imPushingYourButton.setPower(-1);
        // motor run .1 seconds
        ElapsedTime eTime = new ElapsedTime();
        eTime.reset();
        while (eTime.time() < 0.1);

        imPushingYourButton.setPower(0);
    }

    public void PushBeacon () {

        float ohtheHUEmanatee[] = {0f, 0f, 0f};
        final float value[] = ohtheHUEmanatee;
        final View relativeLayout = ((Activity) hardwareMap.appContext).findViewById(com.qualcomm.ftcrobotcontroller.R.id.RelativeLayout);
        boolean bPrevState = false;
        boolean bCurrState = false;
        boolean bLedOn = false;
        mindReader.enableLed(bLedOn);  //(For future use. Your time will come.)



        // while the op mode is active, loop and read the RGB data.
        // Note we use opModeIsActive() as our loop condition because it is an interruptible method.
        //  while (opModeIsActive());

        // update previous state variable.
        bPrevState = bCurrState;

        Color.RGBToHSV(mindReader.red() * 8, mindReader.green() * 8, mindReader.blue() * 8, ohtheHUEmanatee);

        // send the info back to driver station using telemetry function.
        int i = 0;
        int k;

        while(opModeIsActive() ) {
            telemetry.addData("LED", bLedOn ? "On" : "Off");
            telemetry.addData("Clear", mindReader.alpha());
            telemetry.addData("Red  ", mindReader.red());
            telemetry.addData("Green", mindReader.green());
            telemetry.addData("Blue ", mindReader.blue());
            telemetry.addData("Hue", ohtheHUEmanatee[0]);
            telemetry.update();

            while(mindReader.blue() > mindReader.red()){

                PushyTheButton();
                i++;

                if (i% 4 == 0){
                    RejectyTheButton();
                    RejectyTheButton();
                }


            }
            for (k = i/2; k>i; k++){
                RejectyTheButton();
            }

        }

        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.HSVToColor(0xff, value));
            }
        });






    }





    public void LiftCapBall (){

    }

    public void SenseColorRamp (){

    }


    /**
     * Method Name: DriveForward
     * Purpose: drive forward
     * Date Modified: 1/13/17
     */
    public void DriveForward (){    // This creates a Public Method

        rightDrive.setPower(1);     // this sets both drive motors to full power.
        leftDrive.setPower(1);

        ElapsedTime eTime = new ElapsedTime();      // this is a time parameter, telling it to drive
        // for .1 seconds,

        eTime.reset();

        while (eTime.time() < .1) {
        }

        leftDrive.setPower(0.0);            // This stops the driving motors.
        rightDrive.setPower(0.0);

    }


    /**
     * Method Name: DriveBack
     * Purpose: Driving Backwards
     * Date Modified: 1/13/17
     */
    public void DriveBack (){       // This Creates a public method

        rightDrive.setPower(-1);    // This sets the Drive motors, to move in reverse.
        leftDrive.setPower(-1);

        ElapsedTime eTime = new ElapsedTime();      // this is a time parameter, telling it to drive
        // for .1 seconds,

        eTime.reset();

        while (eTime.time() < .1) {
        }

        leftDrive.setPower(0.0);        // This stops the driving motors.
        rightDrive.setPower(0.0);
    }






    /**
     * Method Name: ActivateLeft
     * Purpose: Turning Left
     * Date Modified: 1/13/17
     */
    public void ActivateLeft (){

        leftDrive.setPower(1);      //This sets one motor to turn forward and the other to turn
        rightDrive.setPower(-1);   // backwards, making a 0 point turn


        ElapsedTime eTime = new ElapsedTime();      // this is a time parameter, telling it to drive
        // for .1 seconds,

        eTime.reset();

        while (eTime.time() < .1) {
        }

        leftDrive.setPower(0);      // This stops drive motors
        rightDrive.setPower(0);
    }


    /**
     * Method Name: ActivateRight
     * Purpose: Turning Right
     * Date Modified: 1/13/17
     */
    public void ActivateRight (){

        leftDrive.setPower(-1);         //This sets one motor to turn forward and the other to turn
        rightDrive.setPower(1);         // backwards, making a 0 point turn

        ElapsedTime eTime = new ElapsedTime();       // this is a time parameter, telling it to drive
        // for .1 seconds,

        eTime.reset();

        while (eTime.time() < .1) {
        }

        leftDrive.setPower(0);      // This Stops the Drive motors
        rightDrive.setPower(0);
    }



}



