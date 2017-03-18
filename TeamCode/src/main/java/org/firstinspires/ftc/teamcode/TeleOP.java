/**
 * Program Name: TestOpMode.java
 * Programmer: Veronica Watson
 Team: First Appalachian Robotics 7090
 * Purpose: to run a robot during Controlled portion.
 */


 package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Team Far on 11/12/2016
 */

@TeleOp(name="TeleOP")
public class TeleOP extends OpMode {
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor motorLoader;
    private DcMotor motorShooter;
    private DcMotor motorPusher;
    private ColorSensor sensorColorBeacon;
    private TouchSensor sensorTouchPusher;
     public void init() {
         leftDrive = hardwareMap.dcMotor.get("leftDrive");
         rightDrive = hardwareMap.dcMotor.get("rightDrive");
         motorLoader = hardwareMap.dcMotor.get("loader");
         motorShooter = hardwareMap.dcMotor.get("shooter");
         motorPusher = hardwareMap.dcMotor.get("pusher");
         sensorColorBeacon = hardwareMap.colorSensor.get("sensorColorBeacon");
         sensorTouchPusher = hardwareMap.touchSensor.get("sensorTouchPusher");
         leftDrive.setDirection(DcMotor.Direction.REVERSE);  //Reveres polarity on the left drive motor
         motorLoader.setDirection(DcMotor.Direction.REVERSE); //reverses polarity on the loader motor
         motorShooter.setDirection(DcMotor.Direction.REVERSE); // reverses polarity on the shooter motor
         leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
         rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
     }


     public void loop() {    // this keeps the controls going in a constant loop.
         leftDrive.setPower(-gamepad1.left_stick_y);     // this assigns the left drive motor to run
         rightDrive.setPower(-gamepad1.right_stick_y);                                                // when the left stick is manipulated
         // this assigns the right drive motor to run
         // when the right stick is manipulated


         if (gamepad1.right_trigger >= 0.1) {
             motorShooter.setPower(.65);
         } else if (gamepad1.right_bumper){
             motorShooter.setPower(-1);
         } else {
             motorShooter.setPower(0);
         }


         if (gamepad1.left_bumper) {
             motorLoader.setPower(-1);
         } else if (gamepad1.left_trigger > 0.7) {
             motorLoader.setPower(1);
         } else {
             motorLoader.setPower(0);
         }


         if (gamepad1.a) {
             motorPusher.setPower(.25);
         } else if (gamepad1.y) {
             motorPusher.setPower(-.25);
         } else {
             motorPusher.setPower(0);
         }



     }
 }