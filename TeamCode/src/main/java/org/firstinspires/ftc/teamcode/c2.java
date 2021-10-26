package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Autonomous (name = "c2")
public class c2 extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor motor5;
    Servo  servo1;
    DcMotor motor6;
    @Override
    public void runOpMode() throws InterruptedException{
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        motor5 = hardwareMap.dcMotor.get("motor5");

        waitForStart();
        //Strafe Right
        frontLeft.setPower(.3);
        frontRight.setPower(-.3);
        backLeft.setPower(-.3);
        backRight.setPower(.3);
        sleep(2000);

        //motor 5 spin

        motor5.setPower(.5);
        sleep(20000);

        //Strafe Left
        frontLeft.setPower(-.3);
        frontRight.setPower(.3);
        backLeft.setPower(.3);
        backRight.setPower(-.3);
        sleep(2000);

        //Forward
        frontLeft.setPower(.2);
        frontLeft.setPower(-.2);
        backLeft.setPower(.2);
        backRight.setPower(-.2);
        sleep(500);





    }
}
