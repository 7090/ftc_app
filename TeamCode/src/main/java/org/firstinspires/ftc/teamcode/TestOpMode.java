package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Team Far on 11/12/2016
 */

@TeleOp(name="tellyOp")
public class TestOpMode extends OpMode {
DcMotor LeftDrive;
    DcMotor RightDrive;
    DcMotor Shooter;
    DcMotor Loader;
    @Override
    public void init()
    {
        LeftDrive=hardwareMap.dcMotor.get("LeftDrive");
        RightDrive=hardwareMap.dcMotor.get("RightDrive");
        Shooter=hardwareMap.dcMotor.get("Shooter");
        Loader=hardwareMap.dcMotor.get("Loader");
        RightDrive.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop()
    {
        LeftDrive.setPower(-gamepad1.left_stick_y);
        RightDrive.setPower(-gamepad1.right_stick_y);

        if (gamepad1.right_bumper){
            Shooter.setPower(1);
        }
        else{
            Shooter.setPower(0);
        }

        if(gamepad1.left_bumper){
            Loader.setPower(-1);
        }
        else if(gamepad1.left_trigger >0.7){
            Loader.setPower(1);
        }
        else {
            Loader.setPower(0);
        }
    }
}
