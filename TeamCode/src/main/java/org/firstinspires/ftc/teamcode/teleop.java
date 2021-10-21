package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "mainTeleop")
public class teleop extends OpMode {
    //Movement
    DcMotor frontLeft, frontRight, backLeft, backRight;
    //Game-Related
    DcMotor carousel, claw;

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        carousel = hardwareMap.dcMotor.get("carousel");
        claw = hardwareMap.dcMotor.get("claw");
    }

    @Override
    public void loop() {
        //Movement (P1)
        if (Math.abs(-gamepad1.left_stick_y) > .1) {
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
                frontLeft.setPower(gamepad1.right_stick_x);
                backRight.setPower(gamepad1.right_stick_x);
                frontRight.setPower(gamepad1.right_stick_x);
                backRight.setPower(gamepad1.right_stick_x);
            }
            else {
                frontLeft.setPower(0);
                backRight.setPower(0);
                frontRight.setPower(0);
                backRight.setPower(0);
            }

        //Game Related (P2)
        if (Math.abs(gamepad2.left_stick_y) > .1) {
            claw.setPower(gamepad2.left_stick_y);
        } else {
            claw.setPower(0);
        }
        if (gamepad2.b) {
            carousel.setPower(0.3);
        } else {
            carousel.setPower(0);
        }
    }

}