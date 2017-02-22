package org.firstinspires.ftc.teamcode;

/**
 * Created by TeamFAR on 2/19/2017.
 */
/**
 * Program Name: PsynonemousWillEdit.java
 * Programmer: William Wakely
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
import org.firstinspires.ftc.teamcode.AutonomousResources;
import org.firstinspires.ftc.teamcode.DriveTrainResources;

/**
 * Created by TeamFar on 12/21/2016.
 */



@Autonomous(name="FullOpMode")
public class FullOpMode extends LinearOpMode {


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
     * Purpose:
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

        DriveTrainResources DriveTrainResources = new DriveTrainResources(left_drive, right_drive, FullOpMode.this);
        AutonomousResources AutoResources = new AutonomousResources(left_drive, right_drive, motorPusher, motorShooter, motorLoader, FullOpMode.this);

        TeamColor = "Red"; // Defines our team colour. What is this used for, and should this be set via an external source somehow? - Will 19/2/17

        waitForStart();
        /**
        driveDirection('f',1000);
        driveDirection('b',1000);
        driveDirection('l',1000);
        driveDirection('r',1000);
        */
        DriveTrainResources.drivetrainMoveInches(8,8);
        AutoResources.launchParticles();

    }

}



