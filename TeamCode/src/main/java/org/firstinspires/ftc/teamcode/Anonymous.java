package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.TouchSensor;

/**
 * Created by Team FAR on 11/12/16
 */

public class Anonymous extends LinearOpMode{

    DcMotor leftDrive;
    DcMotor rightDrive;
    ColorSensor sensor_color;

    public void runOpMode (){
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        sensor_color = hardwareMap.colorSensor.get("sensor_color");
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);



        waitForStart();

        rightDrive.setPower(.5);
        leftDrive.setPower(.5);

        if (sensor_color.red()){
            rightDrive.setPower(1);
            leftDrive.setPower(1);
        }

        else {
            rightDrive.setPower(.5);
        }
    }
}
