6package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous (name = "blueCarouselAuto")
public class c2 extends LinearOpMode {
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
        carousel = hardwareMap.dcMotor.get("carousel");
        arm = hardwareMap.dcMotor.get("arm");
        claw = hardwareMap.servo.get("claw");

        waitForStart();
        //Strafe Right
        frontLeft.setPower(-.5);
        frontRight.setPower(-.5);
        backLeft.setPower(.5);
        backRight.setPower(.5);
        sleep(2100);
        //stop
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(1000);

        carousel.setPower(-.2);
        sleep(5000);
        //stop
        carousel.setPower(0);
        sleep(2000);

        //Strafe Left
        frontLeft.setPower(.3);
        frontRight.setPower(.3);
        backLeft.setPower(-.3);
        backRight.setPower(-.3);
        sleep(4000);
        //stop
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(2000);


        sleep(2000);
        //stop
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        sleep(2000);

    }

    public void forward (double speed, long time) {
        //Code here
        frontLeft.setPower(speed);
        frontLeft.setPower(-speed);
        backLeft.setPower(speed);
        backRight.setPower(-speed);
        sleep(time);
    }
    public void backward (double speed, long time) {
        frontLeft.setPower(-speed);
        frontRight.setPower(speed);
        backLeft.setPower(-speed);
        backRight.setPower(speed);
        sleep(time);
    }
    public void strafeRight (double speed, long time) {

    }
    public void strafeLeft (double speed, long time) {

    }
    public void turnRight (double speed, long time) {

    }
    public void turnLeft (double speed, long time) {

}
   }
