package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.robot.T_Minus70;

@TeleOp(name = "mainTeleop")
public class teleop extends OpMode {

    T_Minus70 robot = new T_Minus70();

    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    @Override
    public void loop() {
        //Movement (P1) - Going Backwards / Forwards / Turning
        if (Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_y) > .1) {
            robot.frontLeft.setPower(gamepad1.left_stick_y);
            robot.backLeft.setPower(gamepad1.left_stick_y);
            robot.frontRight.setPower(-gamepad1.right_stick_y);
            robot.backRight.setPower(-gamepad1.right_stick_y);
        }
        //Strafing
        else if (gamepad1.left_trigger > .1) {
            robot.frontLeft.setPower(gamepad1.left_trigger);
            robot.backLeft.setPower(-gamepad1.left_trigger);
            robot.frontRight.setPower(gamepad1.left_trigger);
            robot.backRight.setPower(-gamepad1.left_trigger);
        } else if (gamepad1.right_trigger> .1) {
            robot.frontLeft.setPower(-gamepad1.right_trigger);
            robot.backLeft.setPower(gamepad1.right_trigger);
            robot.frontRight.setPower(-gamepad1.right_trigger);
            robot.backRight.setPower(gamepad1.right_trigger);
        }
        //D-Pad Controls
        else if (gamepad1.dpad_left) {
            robot.frontLeft.setPower(.25);
            robot.backLeft.setPower(-.25);
            robot.frontRight.setPower(.25);
            robot.backRight.setPower(-.25);
        } else if (gamepad1.dpad_right) {
            robot.frontLeft.setPower(-.25);
            robot.backLeft.setPower(.25);
            robot.frontRight.setPower(-.25);
            robot.backRight.setPower(.25);
        } else if (gamepad1.dpad_up) {
            robot.frontLeft.setPower(.25);
            robot.backLeft.setPower(.25);
            robot.frontRight.setPower(-.25);
            robot.backRight.setPower(-.25);
        } else if (gamepad1.dpad_down) {
            robot.frontLeft.setPower(-.25);
            robot.backLeft.setPower(-.25);
            robot.frontRight.setPower(.25);
            robot.backRight.setPower(.25);
        } else {
            robot.frontLeft.setPower(0);
            robot.backLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backRight.setPower(0);
          //int x = (int) robot.COUNTS_PER_MOTOR_REV;
            //robot.armEncoderDrive();
        }

        //Game Related (P2)
        if (Math.abs(gamepad2.left_trigger) > .1) {
            robot.arm.setPower(gamepad2.left_trigger );
        } else if (Math.abs(gamepad2.right_trigger) > .1){
            robot.arm.setPower(-gamepad2.right_trigger);
        } else {
            robot.arm.setPower(0);
        }

        if (gamepad2.b) {
            robot.carousel.setPower(.7);
            robot.carouselRight.setPower(.7);
        } else if (gamepad2.x) {
            robot.carousel.setPower(-.7);
            robot.carouselRight.setPower(-.7);
        } else {
            robot.carousel.setPower(0);
            robot.carouselRight.setPower(0);
        }
        if (gamepad2.right_bumper) {
            robot.claw.setPosition(.3)
            ;
        } else if (gamepad2.left_bumper) {
            robot.claw.setPosition(0);
        }


        telemetry.addData("gamepadRightStick", gamepad1.right_stick_y);
        telemetry.addData("gamepadLeftStick", gamepad1.left_stick_y);
        telemetry.addData("fL", robot.frontLeft.getPower());
        telemetry.addData("fR", robot.frontRight.getPower());
        telemetry.addData("bL", robot.backLeft.getPower());
        telemetry.addData("bR", robot.backRight.getPower());
        telemetry.update();
    }
}
