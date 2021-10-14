package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "TutorialTele")
public class TutorialTele extends OpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight;
    double speed = 0.2;

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
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
    }
}
