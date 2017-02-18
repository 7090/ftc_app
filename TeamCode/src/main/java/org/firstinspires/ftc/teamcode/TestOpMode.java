/**
 * Program Name: TestOpMode.java
 * Programmer: Veronica Watson
 Team: First Appalachian Robotics 7090
 * Purpose: to run a robot during Controlled portion.
 */


 package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

 /**
 * Created by Team Far on 11/12/2016
 */

@TeleOp(name="tellyOp")
public class TestOpMode extends OpMode {
    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor loader;
    DcMotor shooter;
    DcMotor imPushingYourButton;

    public void init() {
        leftDrive = hardwareMap.dcMotor.get("leftDrive");   //assigns the motor leftDrive
        rightDrive = hardwareMap.dcMotor.get("rightDrive"); //assigns the motor rightDrive
        loader = hardwareMap.dcMotor.get("loader"); //assigns the motor loader
        shooter = hardwareMap.dcMotor.get("shooter"); //assigns the motor shooter
        leftDrive.setDirection(DcMotor.Direction.REVERSE);  //Reveres polarity on the left drive motor
        loader.setDirection(DcMotor.Direction.REVERSE); //reverses polarity on the loader motor
        shooter.setDirection(DcMotor.Direction.REVERSE); // reverses polarity on the shooter motor
        DcMotor imPushingYourButton = this.imPushingYourButton;
        imPushingYourButton = hardwareMap.dcMotor.get("imPushingYourButton");

    }



    public void loop() {    // this keeps the controls going in a constant loop.
        leftDrive.setPower(-gamepad1.left_stick_y);     // this assigns the left drive motor to run
        rightDrive.setPower(-gamepad1.right_stick_y);                                                // when the left stick is manipulated
                                                // this assigns the right drive motor to run
                                                         // when the right stick is manipulated


        if (gamepad1.right_trigger >= 0.1) {     // this says that when the trigger is pushed, it
            shooter.setPower(1);                                   // activates the shooter, and to rest otherwise.


        } else {
            shooter.setPower(0);
        }

        if (gamepad1.left_bumper) {     // this says that when the left bumper is pushed, it
            loader.setPower(-1);        // runs the loader motor backwards, and otherwise, when the
                                        //trigger is pressed, to send the loader motor forward and
                                        //otherwise, stop all motion
        } else if (gamepad1.left_trigger > 0.7) {
            loader.setPower(1);

        } else {
            loader.setPower(0);




        }
        if (gamepad1.a){
            imPushingYourButton.setPower(.25);

        } else if (gamepad1.y) {
            imPushingYourButton.setPower(-.25);

        }
            else {
            imPushingYourButton.setPower(0);

        }


        //if (gamepad1.right_bumper) {    // this says that when the right bumper is pressed, reverse
            //shooter.setPower(-1);      //the shooter motor, otherwise the motor is inactive

       // } else {
            //shooter.setPower(0);

            //}
        }
    }

