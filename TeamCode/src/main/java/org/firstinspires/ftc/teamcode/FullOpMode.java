package org.firstinspires.ftc.teamcode;

/**
 * Created by TeamFAR on 2/19/2017.
 *
 * Program Name: FullOpMode.java
 * Programmer: William Wakely
 Team: First Appalachian Robotics 7090
 * Purpose: to run a robot during the 30 second autonomous period
 *
 */


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
 * This will be a 100 point autonomous, hopefully
 */



@Autonomous(name="FullOpMode")
class FullOpMode extends LinearOpMode {
    /**
     *
     * Method Name: runOpMode
     * Purpose:
     * Date Modified: 1/13/17
     */
    public void runOpMode() throws InterruptedException {
        //Set up local variables for hardware devices
        DcMotor left_drive;
        DcMotor right_drive;
        DcMotor motorLoader;
        DcMotor motorShooter;
        DcMotor motorPusher;
        ColorSensor sensorColorBeacon;
        TouchSensor sensorTouchPusher;

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
        left_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right_drive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        DriveTrainResources DTR = new DriveTrainResources(left_drive, right_drive, FullOpMode.this);
        AutonomousResources AutoResources = new AutonomousResources(left_drive, right_drive, motorPusher, motorShooter, motorLoader, FullOpMode.this);

        waitForStart();

        //DTR.drivetrainMoveInches(12,12);
        //AutoResources.launchParticles();
        //DTR.drivetrainTurnDegrees(45, 'l');
        //DTR.drivetrainMoveInches(4);//Change this to a real value
       // DTR.drivetrainTurnDegrees(45, 'r');
        //DTR.drivetrainTurnDegrees(180, 'l');
        AutoResources.findWhiteLine();

        DTR.drivetrainTurnDegrees(90,'l');
        AutoResources.pushBeacon();


    }

}



