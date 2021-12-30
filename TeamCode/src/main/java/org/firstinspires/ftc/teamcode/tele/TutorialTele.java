package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "TutorialTele")
@Disabled
public class TutorialTele extends OpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight;
    DcMotor carousel, arm;
    double speed = 0.2;

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        carousel = hardwareMap.dcMotor.get("carousel");
        arm = hardwareMap.dcMotor.get("arm");
    }

    @Override
    public void loop() {
        if (Math.abs(gamepad1.left_stick_y) > .1) {
            frontLeft.setPower(-gamepad1.left_stick_y * speed);
            backLeft.setPower(-gamepad1.left_stick_y * speed);
        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }

        if (Math.abs(gamepad1.right_stick_y) > .1) {
            frontRight.setPower(gamepad1.right_stick_y * speed);
            backRight.setPower(gamepad1.right_stick_y * speed);
        } else {
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        // x axis


        if (Math.abs(gamepad1.right_stick_x) > .3) {
            frontRight.setPower(gamepad1.right_stick_x);
            backRight.setPower(-gamepad1.right_stick_x);

        } else {
            frontRight.setPower(0);
            backRight.setPower(0);
        }
        if (Math.abs(gamepad1.left_stick_x) > .3) {
            frontLeft.setPower(gamepad1.left_stick_x);
            backLeft.setPower(-gamepad1.left_stick_x);
        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);
        }
    }
}