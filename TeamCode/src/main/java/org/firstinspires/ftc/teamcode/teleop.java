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
       // arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        claw = hardwareMap.servo.get("claw");

    }

    @Override
    public void loop() {
        //Movement (P1)
        if (Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_y) > .1) {
            frontLeft.setPower(gamepad1.left_stick_y);
            backLeft.setPower(gamepad1.left_stick_y);
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);
        } else if (gamepad1.left_trigger > .1) {
            frontLeft.setPower(gamepad1.left_trigger);
            backLeft.setPower(-gamepad1.left_trigger);
            frontRight.setPower(gamepad1.left_trigger);
            backRight.setPower(-gamepad1.left_trigger);
        } else if (gamepad1.right_trigger> .1) {
            frontLeft.setPower(-gamepad1.right_trigger);
            backLeft.setPower(gamepad1.right_trigger);
            frontRight.setPower(-gamepad1.right_trigger);
            backRight.setPower(gamepad1.right_trigger);
        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        //Game Related (P2)
        if (Math.abs(gamepad2.left_trigger) > .1) {
            arm.setPower(gamepad2.left_trigger);
        } else if (Math.abs(gamepad2.right_trigger) > .1){
            arm.setPower(-gamepad2.right_trigger);
        } else {
            arm.setPower(0);
        }

        if (gamepad2.b) {
            carousel.setPower(1);
        } else if (gamepad2.x) {
            carousel.setPower(-1);
        } else {
            carousel.setPower(0);
        }
        if (gamepad2.right_bumper) {
            claw.setPosition(1)
            ;
        } else if (gamepad2.left_bumper) {
            claw.setPosition(.7);
        }


        telemetry.addData("gamepadRightStick", gamepad1.right_stick_y);
        telemetry.addData("gamepadLeftStick", gamepad1.left_stick_y);
        telemetry.addData("fL", frontLeft.getPower());
        telemetry.addData("fR", frontRight.getPower());
        telemetry.addData("bL", backLeft.getPower());
        telemetry.addData("bR", backRight.getPower());
        telemetry.update();
    }
}
