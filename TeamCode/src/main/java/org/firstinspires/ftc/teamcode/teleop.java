package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "mainTeleop")
public class teleop extends OpMode {


    //Movement
    DcMotor frontLeft, frontRight, backLeft, backRight;
    //Game-Related
    DcMotor carousel, arm;
    Servo claw;


    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");


        carousel = hardwareMap.dcMotor.get("carousel");
        arm = hardwareMap.dcMotor.get("arm");

        claw = hardwareMap.servo.get("claw");

    }

    @Override
    public void loop() {
        //Movement (P1)
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            frontLeft.setPower(gamepad1.left_stick_y);
            backLeft.setPower(gamepad1.left_stick_y);
        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }
        if (Math.abs(gamepad1.right_stick_y) > .1) {
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);
        } else {
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        //Strafing
        if (Math.abs(gamepad1.right_stick_x) > .1) {
            frontLeft.setPower(-gamepad1.right_stick_x);
            backLeft.setPower(gamepad1.right_stick_x);
            frontRight.setPower(gamepad1.right_stick_x);
            backRight.setPower(-gamepad1.right_stick_x);
        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
        }
        //Game Related (P2)
        if (Math.abs(gamepad2.left_stick_y) > .1) {
            arm.setPower(gamepad2.left_stick_y);
        } else {
            arm.setPower(0);
        }
        if (gamepad2.b) {
            carousel.setPower(.3);
        } else {
            carousel.setPower(0);
        }
        if (gamepad2.a) {
            claw.setPosition(1);
        } else {
            claw.setPosition(0);

        }
    }
}








