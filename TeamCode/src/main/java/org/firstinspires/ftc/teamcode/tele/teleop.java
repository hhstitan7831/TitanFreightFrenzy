package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.ArrayList;

import org.firstinspires.ftc.teamcode.robot.T_Minus70;

@TeleOp(name = "mainTeleop")
public class teleop extends OpMode {

    //Define Robot & its Mechanisms
    T_Minus70 robot = new T_Minus70();
    ElapsedTime time;

    //Important Variables
    ArrayList<Boolean> booleanArray = new ArrayList<Boolean>();
    int booleanIncrementer = 0;

    @Override
    public void init() {

        robot.init(hardwareMap);
        time = new ElapsedTime();
    }

    @Override
    public void init_loop() {

        time.reset();

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
        } else if (gamepad1.right_trigger > .1) {
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
            robot.arm.setPower(gamepad2.left_trigger);
        } else if (Math.abs(gamepad2.right_trigger) > .1) {
            robot.arm.setPower(-gamepad2.right_trigger);
        } else {
            robot.arm.setPower(0);
        }


        //Getting Toggle Button for Carousel Ready
        boolean G2b = gamepad2.b;
        boolean G2x = gamepad2.x;
        boolean G2bPressed = ifPressed(G2b);
        boolean G2xPressed = ifPressed(G2x);

        if (gamepad2.b) {
//            bToggle = !bToggle;
//            if (bToggle == true) {
//                double spinPower = .6;
//                double startSpin = time.milliseconds();
//                while (time.milliseconds() < startSpin + 1400) {
//                    if (time.milliseconds() % 250 > 150) spinPower *= 1.04;
//                    if (spinPower > 1) spinPower = 1.0;
//                    robot.carousel.setPower(spinPower);
//                    robot.carouselRight.setPower(-spinPower);
//                }
            robot.carousel.setPower(.7);
            robot.carouselRight.setPower(-.7);
        } else if (gamepad2.x) {
//            xToggle = !xToggle;
//            if (xToggle == true) {
//                double spinPower = .6;
//                double startSpin = time.milliseconds();
//                while (time.milliseconds() < startSpin + 1400) {
//                    if (time.milliseconds() % 250 > 150) spinPower *= 1.04;
//                    if (spinPower > 1) spinPower = 1.0;
//                    robot.carousel.setPower(-spinPower);
//                    robot.carouselRight.setPower(spinPower);
//                }
            robot.carousel.setPower(-.7);
            robot.carouselRight.setPower(.7);
        } else {
            robot.carousel.setPower(0);
            robot.carouselRight.setPower(0);
        }
        if (gamepad2.right_bumper) {
            robot.claw.setPosition(.2);
        } else if (gamepad2.left_bumper) {
            robot.claw.setPosition(0);
        }


        if (G2bPressed && robot.carousel.getPower() == 0 && robot.carouselRight.getPower() == 0) {
            double spinPower = .6;
            double startSpin = time.milliseconds();
            while (time.milliseconds() < startSpin + 1400) {
                if (time.milliseconds() % 250 > 150) spinPower *= 1.04;
                if (spinPower > 1) spinPower = 1.0;
                robot.carousel.setPower(spinPower);
                robot.carouselRight.setPower(-spinPower);
                //Failsafe
//            robot.carousel.setPower(.7);
//            robot.carouselRight.setPower(-.7);
                }
            } else if (G2xPressed && robot.carousel.getPower() == 0 && robot.carouselRight.getPower() == 0) {
                double spinPower = .6;
                double startSpin = time.milliseconds();
                while (time.milliseconds() < startSpin + 1400) {
                    if (time.milliseconds() % 250 > 150) spinPower *= 1.04;
                    if (spinPower > 1) spinPower = 1.0;
                    robot.carousel.setPower(-spinPower);
                    robot.carouselRight.setPower(spinPower);
                    }
                    //Failsafe
//            robot.carousel.setPower(-.7);
//            robot.carouselRight.setPower(.7);
                    } else {
                        robot.carousel.setPower(0);
                        robot.carouselRight.setPower(0);
                    }
                    if (gamepad2.right_bumper) {
                        robot.claw.setPosition(0);
                    } else if (gamepad2.left_bumper) {
                        robot.claw.setPosition(.1);
                    }

                    booleanIncrementer = 0;
                    telemetry.addData("gamepadRightStick", gamepad1.right_stick_y);
                    telemetry.addData("gamepadLeftStick", gamepad1.left_stick_y);
                    telemetry.addData("fL", robot.frontLeft.getPower());
                    telemetry.addData("fR", robot.frontRight.getPower());
                    telemetry.addData("bL", robot.backLeft.getPower());
                    telemetry.addData("bR", robot.backRight.getPower());
                    telemetry.addData("carousel", robot.carousel.getPower());
                    telemetry.addData("carouselRight", robot.carouselRight.getPower());
                    telemetry.update();
                }

        private boolean ifPressed (boolean button){
            boolean output = false;
            if (booleanArray.size() == booleanIncrementer) {
                booleanArray.add(false);
            }
            boolean buttonWas = booleanArray.get(booleanIncrementer);
            if (button != buttonWas && button == true) {
                output = true;
            }
            booleanArray.set(booleanIncrementer, button);
            booleanIncrementer = booleanIncrementer + 1;

            return output;
           }
    }