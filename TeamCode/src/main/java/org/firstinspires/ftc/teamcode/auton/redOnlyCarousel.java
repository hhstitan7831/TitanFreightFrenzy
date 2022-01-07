package org.firstinspires.ftc.teamcode.auton;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.vision.blueLeftWarehouse;

//Start --> Go to Carousel --> Spin Carousel enough times to launch off duck --> park in warehouse
@Autonomous (name = "Red Only Carousel Auto")
@Disabled

public class redOnlyCarousel extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor carousel;
    Servo claw;
    DcMotor arm;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        carousel = hardwareMap.dcMotor.get("carousel");
        arm = hardwareMap.dcMotor.get("arm");
        claw = hardwareMap.servo.get("claw");


        waitForStart();
        forward(.2, 500);
        stop(250);

        forward(.1,500 );
        stop(250);

        carousel.setPower(-.8);
        sleep(3300);
        stop(250);

        strafeRight(.3, 2500);
        stop(250);

        forward(.1, 500);
        stop(250);

        /*sleep(3000);
        stop(0,250);


        waitForStart();
       claw.setPosition(.7);


        stop(250);

<
       strafeLeft(.3, 2000);


        strafeLeft(.3, 2000);

        stop(250);

        forward(.2, 250);

        stop(250);

        turnLeft(.3 , 600);
        stop(250);

        carousel.setPower(.4);
        sleep(4000);
        stop(250);



        forward(.4, 720);
        stop(450);


        strafeRight(.4, 600);
        stop(0,250);

        //strafeLeft(.4, 650);
        stop(250);

        backward(.4, 630);
        stop(450);*/


        //claw.setPosition(1);



    }

    public void backward(double speed, long time) {
        //Code here
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        backLeft.setPower(speed);
        backRight.setPower(-speed);
        sleep(time);
    }

    public void forward(double speed, long time) {
        frontLeft.setPower(-speed);
        frontRight.setPower(speed);
        backLeft.setPower(-speed);
        backRight.setPower(speed);
        sleep(time);
    }

    public void strafeRight(double speed, long time) {
        frontLeft.setPower(-speed);
        frontRight.setPower(-speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);
        sleep(time);
    }

    public void strafeLeft(double speed, long time) {
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(-speed);
        backRight.setPower(-speed);
        sleep(time);
    }

    public void turnLeft (double speed, long time) {
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);
        sleep(time);
    }

    public void turnRight(double speed, long time) {
        frontLeft.setPower(-speed);
        frontRight.setPower(-speed);
        backLeft.setPower(-speed);
        backRight.setPower(-speed);
         sleep(time);
    }

    public void stop(long time) {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        carousel.setPower(0);
        sleep(time);
    }
}





