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

        rightDrive.setPower(-1);
        leftDrive.setPower(-1);

        ElapsedTime eTime = new ElapsedTime();

        eTime.reset();

        while (eTime.time() < 2.5) {
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


        loader2.setPower(1);
        try {
            Thread.sleep(2000);
        }
        catch (Error e) {

        }

        shooter.setPower(0.0);
        loader2.setPower(0.0);

        rightDrive.setPower(-1);
        leftDrive.setPower(-1);


        eTime.reset();

        while (eTime.time() < .5) {
        }

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);
    }
}
    /*{
        rightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        shooter.setPower(1);
        loader2.setPower(1);
        }

        {
        eTime.reset();

        while(eTime.time() < 2.5)

        shooter.setPower(0);
        loader2.setPower(0);

        leftDrive.setPower(0.0);
        rightDrive.setPower(0.0);
        }

    }
}*/