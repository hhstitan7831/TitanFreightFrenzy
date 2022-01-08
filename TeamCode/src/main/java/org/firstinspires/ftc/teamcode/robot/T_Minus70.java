package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class T_Minus70 {
    //Movement / Base Motors
    public DcMotor frontLeft = null, frontRight = null, backLeft = null, backRight;
    //Game-Related
    public DcMotor carousel = null, carouselRight = null, arm = null;
    public Servo claw = null;
    public static final double     COUNTS_PER_MOTOR_REV    = 537.7;    // eg: TETRIX Motor Encoder //2150.8

    //This is like big robot hub so we don't have to repetitively put this info in every class

    //public static final double servoStart = 2;
    //public static final double baseSpd = .6;

    HardwareMap hwMap;

    public T_Minus70() {

    }

    public void init (HardwareMap ahwMap) {

        hwMap = ahwMap;

        //Base Motors
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        backLeft = hwMap.get(DcMotor.class, "backLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");

        //Game Elements
        carousel  = hwMap.get(DcMotor.class, "carousel");
        carouselRight  = hwMap.get(DcMotor.class, "carouselRight");
        arm = hwMap.get(DcMotor.class, "arm");
        claw = hwMap.get(Servo.class, "claw");

        //Set Behaviors
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    /*public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newFrontLeftTarget;
        int newBackLeftTarget;
        int newFrontRightTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = frontLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newBackLeftTarget = backLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newFrontRightTarget = frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newBackRightTarget = backRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

            frontLeft.setTargetPosition(newFrontLeftTarget);
            backLeft.setTargetPosition(newBackLeftTarget);
            frontRight.setTargetPosition(newFrontRightTarget);
            backRight.setTargetPosition(newBackRightTarget);
            // Turn On RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            frontLeft.setPower(Math.abs(speed));
            backLeft.setPower(Math.abs(speed));
            frontRight.setPower(Math.abs(speed));
            backRight.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d : %7d: %7d ", newFrontLeftTarget, newBackLeftTarget, newFrontRightTarget, newBackRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d : %7d: %7d ",
                        frontLeft.getCurrentPosition(),
                        backLeft.getCurrentPosition(),
                        frontRight.getCurrentPosition(),
                        backRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);

            // Turn off RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move
        }
    }

   /* public void encoderDriveStrafe(double speed, double leftInches, double rightInches, double timeoutS) {

        int newFrontLeftTarget;
        int newBackLeftTarget;
        int newFrontRightTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = frontLeft.getCurrentPosition() - (int) (leftInches * COUNTS_PER_INCH);
            newBackLeftTarget = backLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newFrontRightTarget = frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newBackRightTarget = backRight.getCurrentPosition() - (int) (rightInches * COUNTS_PER_INCH);
            frontLeft.setTargetPosition(newFrontLeftTarget);
            backLeft.setTargetPosition(newBackLeftTarget);
            frontRight.setTargetPosition(newFrontRightTarget);
            backRight.setTargetPosition(newBackRightTarget);
            // Turn On RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            frontLeft.setPower(Math.abs(-speed));
            backLeft.setPower(Math.abs(speed));
            frontRight.setPower(Math.abs(speed));
            backRight.setPower(Math.abs(-speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d : %7d: %7d ", newFrontLeftTarget, newBackLeftTarget, newFrontRightTarget, newBackRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d : %7d: %7d ",
                        frontLeft.getCurrentPosition(),
                        backLeft.getCurrentPosition(),
                        frontRight.getCurrentPosition(),
                        backRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            frontLeft.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);

            // Turn off RUN_TO_POSITION
            frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move
        }
    }

    */


    }