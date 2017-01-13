package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by TeamFar on 12/21/2016.
 */

@Autonomous(name = "Psynonemous")
public class Psynonemous extends LinearOpMode {


    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor loader2;
    DcMotor loader;
    DcMotor shooter;
    public void runOpMode() throws InterruptedException {

        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        loader = hardwareMap.dcMotor.get("loader");
        loader2 = hardwareMap.dcMotor.get("loader2");
        shooter = hardwareMap.dcMotor.get("shooter");
        loader2.setDirection(DcMotorSimple.Direction.REVERSE);
        shooter.setDirection(DcMotorSimple.Direction.REVERSE);

       /* This is where i call all of my methods, meaning that this is the area where i tell the
       robot specifically what i want it to do */

        waitForStart();

        int i;

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
        }

    }







    public void PushCapBall (){

    }

    /**
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
        loader2.setPower(1);       // this sets the loader power to 1 (full power)

        try {       // Try Catch is used because if it does not sleep, it should continue to the next
            // step


            Thread.sleep(2500);   // this makes it stop for 1.5 seconds, before continuing.

        } catch (InterruptedException e) {
            e.printStackTrace();        // What this does is print what causes the error.
        }

        shooter.setPower(0.0);      // this stops the motors.
        loader2.setPower(0.0);
    }


    public void FindWhiteLine (){

    }

    public void PushBeacon (){

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



