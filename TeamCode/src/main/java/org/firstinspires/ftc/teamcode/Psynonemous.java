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

        waitForStart();

        int i;

        for (i = 0; i < 10; i++) {
            DriveForward();
        }

        for (i = 0; i < 10; i++) {
            ActivateLeft();
        }

        for (i = 0; i < 10; i++) {
            DriveBack();
        }

        for (i = 0; i < 10; i++) {
            ActivateRight();
        }

        PewPewShooter();



    }







    public void PushCapBall (){

    }

    public void PewPewShooter (){

        shooter.setPower(1);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
        loader2.setPower(1);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        shooter.setPower(0.0);
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

    public void DriveForward (){

        rightDrive.setPower(1);
        leftDrive.setPower(1);

        ElapsedTime eTime = new ElapsedTime();

        eTime.reset();

        while (eTime.time() < .1) {
        }

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);

    }

    public void DriveBack (){

        rightDrive.setPower(-1);
        leftDrive.setPower(-1);

        ElapsedTime eTime = new ElapsedTime();

        eTime.reset();

        while (eTime.time() < .1) {
        }

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);
    }

    public void ActivateLeft (){

        leftDrive.setPower(1);
        rightDrive.setPower(0);

        ElapsedTime eTime = new ElapsedTime();

        eTime.reset();

        while (eTime.time() < .1) {
        }

        leftDrive.setPower(0);
    }

    public void ActivateRight (){

        leftDrive.setPower(0);
        rightDrive.setPower(1);

        ElapsedTime eTime = new ElapsedTime();

        eTime.reset();

        while (eTime.time() < .1) {
        }

        leftDrive.setPower(0);
    }



}



