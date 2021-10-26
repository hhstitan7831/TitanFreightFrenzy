package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@Autonomous (name = "c1")
public class c1 extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor motor5;
    DcMotor motor6;
    Servo  servo1;
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
        sleep(3000);

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

        //Grab object
        servo1.setPosition(50);

        //Lift Object
        motor6.setPower(.5);
        sleep(600);









    }
}
