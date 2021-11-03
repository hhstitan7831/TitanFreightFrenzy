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
    //Servo claw;


    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        carousel = hardwareMap.dcMotor.get("carousel");
        arm = hardwareMap.dcMotor.get("arm");

        //claw = hardwareMap.servo.get("claw");

    }

    @Override
    public void loop() {
        //Movement (P1)
        if (Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_y) > .1) {
            frontLeft.setPower(gamepad1.left_stick_y);
            backLeft.setPower(gamepad1.left_stick_y);
            frontRight.setPower(-gamepad1.right_stick_y);
            backRight.setPower(-gamepad1.right_stick_y);
        } else if (gamepad1.right_trigger > .1) {
            frontLeft.setPower(gamepad1.right_trigger);
            backLeft.setPower(-gamepad1.right_trigger);
            frontRight.setPower(gamepad1.right_trigger);
            backRight.setPower(-gamepad1.right_trigger);
        } else if (gamepad1.left_trigger > .1) {
            frontLeft.setPower(-gamepad1.left_trigger);
            backLeft.setPower(gamepad1.left_trigger);
            frontRight.setPower(-gamepad1.left_trigger);
            backRight.setPower(gamepad1.left_trigger);
        } else {
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
        }

        //Strafing
   /*    if (Math.abs(gamepad1.right_stick_x) > .3){
           frontLeft.setPower(gamepad1.right_stick_x);
           backLeft.setPower(-gamepad1.right_stick_x);
       } else {
           frontLeft.setPower(0);
           backLeft.setPower(0);
        }
        if (Math.abs(gamepad1.right_stick_x) > .3){
            frontRight.setPower(gamepad1.right_stick_x);
            backRight.setPower(-gamepad1.right_stick_x);
       } else {
           frontRight.setPower(0);
           backRight.setPower(0);
       }*/
        //Game Related (P2)
        if (gamepad2.dpad_up) {
            arm.setPower(.5);
        } else if (gamepad2.dpad_down) {
            arm.setPower(-.5);
        }
        else {
            arm.setPower(0);
        }

        if (gamepad2.b) {
            carousel.setPower(.3);
        } else {
            carousel.setPower(0);
        }
        if (gamepad2.a) {
      //      claw.setPosition(.8);
        } else {
            //     claw.setPosition(0);

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