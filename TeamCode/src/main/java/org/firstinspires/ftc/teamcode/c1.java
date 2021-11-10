package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

//Start --> Go to Carousel --> Spin Carousel enough times to launch off duck --> park in warehouse
@Autonomous (name = "redOnlyCarouselAuto")
public class c1 extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor carousel;
    Servo claw;
    DcMotor arm;

    @Override
    public void runOpMode() throws InterruptedException{
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        carousel = hardwareMap.dcMotor.get("motor5");
        arm = hardwareMap.dcMotor.get("arm");
        claw = hardwareMap.servo.get("claw");

        //Strafe Right
        frontLeft.setPower(-1);
        frontRight.setPower(-1);
        backLeft.setPower(1);
        backRight.setPower(1);
        sleep(2000);

        carousel.setPower(.5);
        sleep(10000);

        //Strafe Left
        frontLeft.setPower(.3);
        frontRight.setPower(-.3);
        backLeft.setPower(-.3);
        backRight.setPower(.3);
        sleep(3000);

        forward();
        sleep(2000);




    }

    public void forward () {
        //Code here
        frontLeft.setPower(.2);
        frontRight.setPower(-.2);
        backLeft.setPower(.2);
        backRight.setPower(-.2);
    }
    public void backward () {

    }
}






