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
    DcMotor loader2;
    public void init() {
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        loader = hardwareMap.dcMotor.get("loader");
        shooter = hardwareMap.dcMotor.get("shooter");
        loader2 = hardwareMap.dcMotor.get("loader2");
        loader2.setDirection(DcMotor.Direction.REVERSE);
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        loader.setDirection(DcMotor.Direction.REVERSE);
        shooter.setDirection(DcMotor.Direction.REVERSE);


    }



    public void loop() {
        leftDrive.setPower(-gamepad1.left_stick_y);
        rightDrive.setPower(-gamepad1.right_stick_y);


        if (gamepad1.right_trigger > 0.1) {
            shooter.setPower(1);
        } else {
            shooter.setPower(0);
        }

        if (gamepad1.left_bumper) {
            loader.setPower(-1);
        } else if (gamepad1.left_trigger > 0.7) {
            loader.setPower(1);
        } else {
            loader.setPower(0);
        }
        if (gamepad1.right_bumper) {
            loader2.setPower(1);
        } else {
            loader2.setPower(0);

            }
        }
    }

