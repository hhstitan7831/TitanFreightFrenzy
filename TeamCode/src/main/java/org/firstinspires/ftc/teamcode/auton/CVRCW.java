package org.firstinspires.ftc.teamcode.auton;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.robot.ContourPipeline;
import org.firstinspires.ftc.teamcode.robot.T_Minus70;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@Autonomous(name="CVRCW", group="Tutorials")

public class CVRCW extends LinearOpMode {

    T_Minus70 robot = new T_Minus70();

    private ElapsedTime runtime = new ElapsedTime();
    private Orientation lastAngles = new Orientation();
    private double currAngle = 0.0;


    // values for base
    static final double COUNTS_PER_MOTOR_REV = 537.7;    // eg: TETRIX Motor Encoder //2150.8
    static final double DRIVE_GEAR_REDUCTION = 1;        // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 3.77953;     // For figuring circumference

    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.2;
    static final double TURN_SPEED = 0.5;


    //values for arm
    static final double COUNTS_PER_ARM_MOTOR_REV = 1440.0;  // eg: TETRIX Motor Encoder //2150.8
    static final double ARM_GEAR_REDUCTION = 0.3;        // This is < 1.0 if geared UP
    static final double SPROCKET_DIAMETER_INCHES = 3.0;     // For figuring circumference

    static final double ARM_PER_INCH = (COUNTS_PER_ARM_MOTOR_REV * ARM_GEAR_REDUCTION) / (SPROCKET_DIAMETER_INCHES * 3.1415);
    static final double LVL_1_INCHES = 11;
    static final double LVL_2_INCHES = 15;
    static final double LVL_3_INCHES = 20;

    public static double liftHeight = 0.0;
    public static int BP = 0;


    private OpenCvCamera webcam;
    private ContourPipeline pipeline;


    private int minRectangleArea = 2000;
    private double leftBarcodeRangeBoundary = 0.3; //i.e 30% of the way across the frame from the left
    private double rightBarcodeRangeBoundary = 0.7; //i.e 60% of the way across the frame from the left

    private double lowerRuntime = 0;
    private double upperRuntime = 0;
    static final double WEBCAM_WIDTH = 640;

    // Red Range                                      Y      Cr     Cb
    // public static Scalar scalarLowerYCrCb = new Scalar(0.0, 170.0, 0.0);
    // public static Scalar scalarUpperYCrCb = new Scalar(255.0, 255.0, 128.0);

    // Green Range                                      Y      Cr     Cb
    public static Scalar scalarLowerYCrCb = new Scalar(0.0, 0.0, 0.0);
    public static Scalar scalarUpperYCrCb = new Scalar(255.0, 120.0, 128.0);

    @Override
    public void runOpMode() throws InterruptedException
    {
        robot.init(hardwareMap);
        // OpenCV webcam
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        //OpenCV Pipeline

        pipeline = new ContourPipeline(0.0, 0.0, 0.0, 0.0);

        pipeline.configureScalarLower(scalarLowerYCrCb.val[0],scalarLowerYCrCb.val[1],scalarLowerYCrCb.val[2]);
        pipeline.configureScalarUpper(scalarUpperYCrCb.val[0],scalarUpperYCrCb.val[1],scalarUpperYCrCb.val[2]);


        robot.frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        robot.backLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        robot.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0", "Starting at %7d :%7d : %7d: %7d ",
                robot.frontLeft.getCurrentPosition(),
                robot.frontRight.getCurrentPosition(),
                robot.backLeft.getCurrentPosition(),
                robot.backRight.getCurrentPosition());
        telemetry.update();


        pipeline.configureScalarLower(scalarLowerYCrCb.val[0],scalarLowerYCrCb.val[1],scalarLowerYCrCb.val[2]);
        pipeline.configureScalarUpper(scalarUpperYCrCb.val[0],scalarUpperYCrCb.val[1],scalarUpperYCrCb.val[2]);

        webcam.setPipeline(pipeline);

        // Webcam Streaming
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {

            }
        });


        waitForStart();

        //liftHeight = LVL_3_INCHES;

        robot.claw.setPosition(0);
        sleep(250);

        //if(isStopRequested()) return;


        if(pipeline.error){
            telemetry.addData("Exception: ", pipeline.debug.getStackTrace());
        }


        double rectangleArea = pipeline.getRectArea();

        //Print out the area of the rectangle that is found.
        telemetry.addData("Rectangle Area", rectangleArea);

        //Check to see if the rectangle has a large enough area to be a marker.
        if(rectangleArea > minRectangleArea){
            //Then check the location of the rectangle to see which barcode it is in.
            if(pipeline.getRectMidpointX() < leftBarcodeRangeBoundary * WEBCAM_WIDTH){
                telemetry.addData("Barcode Position", "Left");
                BP = 1;
                liftHeight = LVL_1_INCHES;
            }
            else if(pipeline.getRectMidpointX() > rightBarcodeRangeBoundary * WEBCAM_WIDTH){
                telemetry.addData("Barcode Position", "Right");
                BP = 3;
                liftHeight = LVL_3_INCHES;
            }
            else {
                telemetry.addData("Barcode Position", "Center");
                BP = 2;
                liftHeight = LVL_2_INCHES;

            }
        }
        telemetry.addData("BP", BP);
        telemetry.addData("LH", liftHeight);
        telemetry.update();
        sleep(1000);
        // rest of auton
        // strafe left to carousel
        encoderDriveStrafe(0.5, 26, 26, 4.0);
        // strafe right to make space for axel
        encoderDriveStrafe(0.5, -3, -3, 1.0);
        //forward
        encoderDrive(0.5, 2, 2,5.0);

        // make turn
        encoderDrive(0.5, -17, 17, 5.0);
        // forward to carousel
        encoderDrive(DRIVE_SPEED, 10, 10, 5.0);
        // spinning duck, duck go brrr
        robot.carousel.setPower(-.7);
        sleep(2200);
        robot.carousel.setPower(0);
        // turn
        encoderDrive(0.3, 2, -2, 4.0);
        // strafe right
        encoderDriveStrafe(0.5, -40, -40, 4.0);
        // turn
        encoderDrive(DRIVE_SPEED, 38, -38, 4.0);
        // forward to shipping hub
        encoderDrive(0.4, 20, 20, 4.0);
        // arm lift
        armEncoderDrive(DRIVE_SPEED, liftHeight,2.0);
        // forward
        encoderDrive(DRIVE_SPEED, 9, 9, 3.0);
        // release block
        robot.claw.setPosition(.1);
        sleep(250);
        // go backwards a little
        encoderDrive(DRIVE_SPEED, -2, -2, 0.5);
        // strafe right
        encoderDriveStrafe(.4, -24, -24, 2.0);
        // drive forward at mach speed hella fast
        armEncoderDrive(.3, LVL_3_INCHES, 2.0);
        encoderDrive(.8, 70, 70, 2.0);




    }

    public double inValues(double value, double min, double max){
        if(value < min){ value = min; }
        if(value > max){ value = max; }
        return value;
    }

    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
        int newFrontLeftTarget;
        int newBackLeftTarget;
        int newFrontRightTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = robot.frontLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newBackLeftTarget = robot.backLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newFrontRightTarget = robot.frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newBackRightTarget = robot.backRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

            robot.frontLeft.setTargetPosition(newFrontLeftTarget);
            robot.backLeft.setTargetPosition(newBackLeftTarget);
            robot.frontRight.setTargetPosition(newFrontRightTarget);
            robot.backRight.setTargetPosition(newBackRightTarget);
            // Turn On RUN_TO_POSITION
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            robot.frontLeft.setPower(Math.abs(speed));
            robot.backLeft.setPower(Math.abs(speed));
            robot.frontRight.setPower(Math.abs(speed));
            robot.backRight.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.frontLeft.isBusy() && robot.frontRight.isBusy() && robot.backLeft.isBusy() && robot.backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d : %7d: %7d ", newFrontLeftTarget, newBackLeftTarget, newFrontRightTarget, newBackRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d : %7d: %7d ",
                        robot.frontLeft.getCurrentPosition(),
                        robot.backLeft.getCurrentPosition(),
                        robot.frontRight.getCurrentPosition(),
                        robot.backRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.frontLeft.setPower(0);
            robot.backLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backRight.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move
        }
    }

    public void encoderDriveStrafe(double speed, double leftInches, double rightInches, double timeoutS) {

        int newFrontLeftTarget;
        int newBackLeftTarget;
        int newFrontRightTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            robot.frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            // Determine new target position, and pass to motor controller
            newFrontLeftTarget = robot.frontLeft.getCurrentPosition() - (int) (leftInches * COUNTS_PER_INCH);
            newBackLeftTarget = robot.backLeft.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newFrontRightTarget = robot.frontRight.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newBackRightTarget = robot.backRight.getCurrentPosition() - (int) (rightInches * COUNTS_PER_INCH);
            robot.frontLeft.setTargetPosition(newFrontLeftTarget);
            robot.backLeft.setTargetPosition(newBackLeftTarget);
            robot.frontRight.setTargetPosition(newFrontRightTarget);
            robot.backRight.setTargetPosition(newBackRightTarget);
            // Turn On RUN_TO_POSITION
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            // reset the timeout time and start motion.
            runtime.reset();
            robot.frontLeft.setPower(Math.abs(-speed));
            robot.backLeft.setPower(Math.abs(speed));
            robot.frontRight.setPower(Math.abs(speed));
            robot.backRight.setPower(Math.abs(-speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.frontLeft.isBusy() && robot.frontRight.isBusy() && robot.backLeft.isBusy() && robot.backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d : %7d: %7d ", newFrontLeftTarget, newBackLeftTarget, newFrontRightTarget, newBackRightTarget);
                telemetry.addData("Path2", "Running at %7d :%7d : %7d: %7d ",
                        robot.frontLeft.getCurrentPosition(),
                        robot.backLeft.getCurrentPosition(),
                        robot.frontRight.getCurrentPosition(),
                        robot.backRight.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.frontLeft.setPower(0);
            robot.backLeft.setPower(0);
            robot.frontRight.setPower(0);
            robot.backRight.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move
        }
    }



    public void armEncoderDrive(double speed, double inches, double timeoutS) {
        int newarmTarget;


        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            robot.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


            // Determine new target position, and pass to motor controller
            newarmTarget = robot.arm.getCurrentPosition() + (int) (inches * ARM_PER_INCH);

            robot.arm.setTargetPosition(newarmTarget);

            // Turn On RUN_TO_POSITION
            robot.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.arm.setPower(Math.abs(speed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() && (runtime.seconds() < timeoutS) && (robot.arm.isBusy())) {// frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy())) {

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



            sleep(250);   // optional pause after each move
        }
    }
    public void resetAngle() {
        lastAngles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        currAngle = 0;

    }

    public double getAngle() {

        Orientation orientation = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder .ZYX, AngleUnit.DEGREES);

        double deltaAngle = orientation.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180) {
            deltaAngle += 360;
        }
        else if (deltaAngle > 180) {
            deltaAngle -= 360;
        }


        currAngle += deltaAngle;
        lastAngles = orientation;
        return currAngle;

    }

    public void turn (double degrees) {
        resetAngle();

        double error = degrees;
        while (opModeIsActive() && Math.abs(error) > 2) {
            double motorPower = (error < 0 ? -0.3 : 0.3);
            robot.frontLeft.setPower(motorPower);
            robot.frontRight.setPower(motorPower);
            robot.backLeft.setPower(motorPower);
            robot.backRight.setPower(motorPower);
            error = degrees - getAngle();
        }
    }

    public void turnTo (double degrees) {
        Orientation orientation = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder .ZYX, AngleUnit.DEGREES);

        double error = degrees - orientation.firstAngle;

        if (error > 180) {
            error -= 360;
        }
        else if (error < -180) {
            error += 360;
        }
        turn(error);
    }

    public double getAbsoluteAngle() { return robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder .ZYX, AngleUnit.DEGREES).firstAngle; }

}