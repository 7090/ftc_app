package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/**\
 * Created by Team FAR on 11/12/16
 */
@Autonomous(name = "Anonymous")
public class Anonymous extends LinearOpMode {

    DcMotor leftDrive;
    DcMotor rightDrive;
    DcMotor loader;
    DcMotor shooter;

    public void runOpMode() throws InterruptedException {
        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        loader = hardwareMap.dcMotor.get("loader");
        shooter = hardwareMap.dcMotor.get("shooter");
        shooter.setDirection(DcMotorSimple.Direction.REVERSE);
        loader.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        rightDrive.setPower(-1);
        leftDrive.setPower(-1);

        ElapsedTime eTime = new ElapsedTime();

        eTime.reset();

        while (eTime.time() < 1.6) {
        }

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);

        shooter.setPower(1);
        try {
            Thread.sleep(1500);
        }
        catch ( Error e)
        {

        }


        loader.setPower(1);
        try {
            Thread.sleep(2000);
        }
        catch (Error e) {

        }

        shooter.setPower(0.0);
        loader.setPower(0.0);

        rightDrive.setPower(-1);
        leftDrive.setPower(-1);


        eTime.reset();

        while (eTime.time() < 1) {
        }

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);
    }
}
