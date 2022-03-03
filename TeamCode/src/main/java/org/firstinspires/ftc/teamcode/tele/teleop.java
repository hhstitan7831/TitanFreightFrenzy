package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
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

    static final double DRIVE_SPEED = 0.2;
    static final double COUNTS_PER_ARM_MOTOR_REV = 1440.0;  // eg: TETRIX Motor Encoder //2150.8
    static final double ARM_GEAR_REDUCTION = 0.3;        // This is < 1.0 if geared UP
    static final double SPROCKET_DIAMETER_INCHES = 3.0;     // For figuring circumference

    static final double ARM_PER_INCH = (COUNTS_PER_ARM_MOTOR_REV * ARM_GEAR_REDUCTION) / (SPROCKET_DIAMETER_INCHES * 3.1415);
    static final double LVL_1_INCHES = 11;
    static final double LVL_2_INCHES = 15;
    static final double LVL_3_INCHES = 21;

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
        if (gamepad2.dpad_down) {
            double startArm = time.milliseconds();
            armEncoderDrive(DRIVE_SPEED, LVL_1_INCHES, 3.0);
        } else if (gamepad2.dpad_left) {
            double startArm = time.milliseconds();
            armEncoderDrive(DRIVE_SPEED, LVL_2_INCHES, 3.0);
        } else if (gamepad2.dpad_up) {
            double startArm = time.milliseconds();
            armEncoderDrive(DRIVE_SPEED, LVL_3_INCHES, 3.0);
        } else if (gamepad2.right_trigger > .1) {
            double startArm = time.milliseconds();
            robot.arm.setPower(-gamepad2.right_trigger);
        } else if (gamepad2.left_trigger > .1) {
            double startArm = time.milliseconds();
            robot.arm.setPower(gamepad2.left_trigger);
        } else {
            robot.arm.setPower(0);
        }


        //Getting Toggle Button for Carousel Ready
        boolean G2b = gamepad2.b;
        boolean G2x = gamepad2.x;
        boolean G2bPressed = ifPressed(G2b);
        boolean G2xPressed = ifPressed(G2x);

        if (gamepad2.right_bumper) {
            robot.claw.setPosition(.2);
        } else if (gamepad2.left_bumper) {
            robot.claw.setPosition(0);

            if (gamepad2.left_bumper) {
                robot.claw.setPosition(0);
            } else if (gamepad2.right_bumper) {
                robot.claw.setPosition(.2);
            }


            if (G2bPressed && robot.carousel.getPower() == 0 && robot.carouselRight.getPower() == 0) {
                double spinPower = .1;
                double startSpin = time.milliseconds();
                while (time.milliseconds() < startSpin + 1600) {
                    if (time.milliseconds() % 250 > 150) spinPower *= 1.15;
                    else if (spinPower > 1) spinPower = 1.0;
                    robot.carousel.setPower(spinPower);
                    robot.carouselRight.setPower(-spinPower);
                    //Failsafe
//            robot.carousel.setPower(.7);
//            robot.carouselRight.setPower(-.7);
                }
            } else if (G2xPressed && robot.carousel.getPower() == 0 && robot.carouselRight.getPower() == 0) {
                double spinPower = .1;
                double startSpin = time.milliseconds();
                while (time.milliseconds() < startSpin + 1600) {
                    if (time.milliseconds() % 200 > 150) spinPower *= 1.15;
                    else if (spinPower > 1) spinPower = 1.0;
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
    }

        private boolean ifPressed ( boolean button){
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

        public void armEncoderDrive ( double speed, double inches, double timeoutS){
            int newarmTarget;


            // Ensure that the opmode is still active
            robot.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            // Determine new target position, and pass to motor controller
            newarmTarget = robot.arm.getCurrentPosition() + (int) (inches * ARM_PER_INCH);

            robot.arm.setTargetPosition(newarmTarget);

            // Turn On RUN_TO_POSITION
            robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            time.reset();
            robot.arm.setPower(Math.abs(speed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while ((time.seconds() < timeoutS) && (robot.arm.isBusy())) {// frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d ", newarmTarget);//newBackLeftTarget, newFrontRightTarget, newBackRightTarget);
                telemetry.addData("Path2", "Running at %7d  ",
                        robot.arm.getCurrentPosition());

                telemetry.update();
            }

            // Stop all motion;
            robot.arm.setPower(0);


            // Turn off RUN_TO_POSITION
            robot.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
