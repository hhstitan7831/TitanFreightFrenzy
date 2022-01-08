<<<<<<< Updated upstream:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/auton/RedDDCarousel.java
=======
package org.firstinspires.ftc.teamcode.vision.OpenCVTesting;
>>>>>>> Stashed changes:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/vision/OpenCVTesting/CVTest.java


package org.firstinspires.ftc.teamcode.vision;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
<<<<<<< Updated upstream:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/auton/RedDDCarousel.java
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This 2020-2021 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the Freight Frenzy game elements.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@Autonomous(name = "RedDDCarousel", group = "Testing")
public class RedDDCarousel extends LinearOpMode {
    /* Note: This sample uses the all-objects Tensor Flow model (FreightFrenzy_BCDM.tflite), which contains
     * the following 4 detectable objects
     *  0: Ball,
     *  1: Cube,
     *  2: Duck,
     *  3: Marker (duck location tape marker)
     *
     *  Two additional model assets are available which only contain a subset of the objects:
     *  FreightFrenzy_BC.tflite  0: Ball,  1: Cube
     *  FreightFrenzy_DM.tflite  0: Duck,  1: Marker
     */
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
=======
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.robot.ContourPipeline;
import org.opencv.core.Scalar;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
>>>>>>> Stashed changes:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/vision/OpenCVTesting/CVTest.java


    DcMotor frontLeft, frontRight, backLeft, backRight;
    //Game-Related
    DcMotor carousel, arm;
    Servo claw;

    private ElapsedTime runtime = new ElapsedTime();


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
    static final double LVL_1_INCHES = 7;
    static final double LVL_2_INCHES = 14;
    static final double LVL_3_INCHES = 19.5;

    public static double liftHeight = 0.0;

    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"

    };


    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY =
            "AbWdbxD/////AAABmVEk6zH8akMZqaP1wVOVVwpwfObuMB7CE6yVxuGmtieI0kuJfSRhmL86my6hTU85SwpBOQFg+sVmrQ5qGxCh68pvcz8SXBe/RrmA+MiaBpj6fXkb3FRjthZMjE/55ZJJgiuY9p556l0DQShL1HXS65sqomlRKdQvOii+3ZkGonXbVeg1DRuUlQ1xtcOkt9kqKw9KMKoJp/4YvHtScneKaRa1/RiJGtw7w4clg+0SgBJdd1EdcKfPPhYqHFvR8zQYi7aVy2Ayf5zyXL+iPt0lB/fLOQSS2TSeVLq8M0V+UWC8JPqw1wQ5olZS4kOdYd1Y+cITdvdkMd+o4RMQ2Kn/yXF17tAj7oBs3vTbWWt1Lw6l";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private static TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();
        initTfod();
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        carousel = hardwareMap.dcMotor.get("carousel");
        arm = hardwareMap.dcMotor.get("arm");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        claw = hardwareMap.servo.get("claw");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0", "Starting at %7d :%7d : %7d: %7d ",
                frontLeft.getCurrentPosition(),
                frontRight.getCurrentPosition(),
                backLeft.getCurrentPosition(),
                backRight.getCurrentPosition());
        telemetry.update();

        /**
         * Activate TensorFlow Object Detection before we wait for the start command.
         * Do it here so that the Camera Stream window will have the TensorFlow annotations visible.
         **/
        if (tfod != null) {
            tfod.activate();

            // The TensorFlow software will scale the input images from the camera to a lower resolution.
            // This can result in lower detection accuracy at longer distances (> 55cm or 22").
            // If your target is at distance greater than 50 cm (20") you can adjust the magnification value
            // to artificially zoom in to the center of image.  For best results, the "aspectRatio" argument
            // should be set to the value of the images used to create the TensorFlow Object Detection model
            // (typically 16/9).
            tfod.setZoom(2.5, 16.0 / 9.0);
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();

            // close claw to pinch block
            claw.setPosition(0);
            sleep(250);
    
            //Move Forward
            encoderDrive(DRIVE_SPEED, 15, 15, 5.0);
            sleep(1000);
            telemetry.addLine("detecting ducks");
            telemetry.update();

            //DUCK WYA
            if (isDuckOrCubeDetected() == true) { //If Duck is in middle marker (Level 2)
                //Set lift height => 2
                liftHeight = LVL_2_INCHES;
                telemetry.addData("Changed lift height to", liftHeight);
                telemetry.addLine("Duck Detected @ LVL 2, Strafing now");
                telemetry.update();
                //Strafe right to shipping hub
                encoderDriveStrafe(DRIVE_SPEED, -24, -24, 12.0);
                sleep(500);

            } else { //Duck is NOT detected in Level 2
                //Strafe right to Level 3
                encoderDriveStrafe(DRIVE_SPEED, -8, -8, 3.0);
                encoderDrive(DRIVE_SPEED, 2,2,1.5);
                //Sleep 1s
                sleep(1000);
                if (isDuckOrCubeDetected() == true) { //If Duck is in LEVEL 3
                    //Set lift height => 3
                    liftHeight = LVL_3_INCHES;
                    telemetry.addData("Changed lift height to", liftHeight);
                    telemetry.addLine("Duck Detected @ LVL 3, Strafing now");
                    telemetry.update();
                    //Strafe right to shipping hub (different distance)
                    encoderDriveStrafe(DRIVE_SPEED, -18, -18, 12.0);
                    sleep(1000);
                } else { //If not in LEVEL 2 AND LEVEL 3
                    //Set lift height to 1 (assuming duck is at 1 since not detected at 2 or 3)
                    liftHeight = LVL_1_INCHES;
                    telemetry.addData("Changed lift height to", liftHeight);
                    telemetry.addLine("Duck assumed @ LVL 1, Strafing now");
                    telemetry.update();
                    //Strafe right to shipping hub (same distance as Level 3)
                    encoderDriveStrafe(DRIVE_SPEED, -15, -15, 8.0);
                    sleep(500);

                }
            }


        //Rest of auton
            armEncoderDrive(DRIVE_SPEED, liftHeight,2.0);
            encoderDrive(DRIVE_SPEED,8,8,5.0);

                 // go foward
                  // release block
                claw.setPosition(.3);
                sleep(250);
                // backward to wall, head to carousel 
                encoderDrive(DRIVE_SPEED, -20, -20,5.0);
                // strafe right to carousel 
                encoderDriveStrafe(DRIVE_SPEED, -42, -42, 5.0);
                // spinning duck, duck go brrr 
                carousel.setPower(-.8); 
                sleep(250); 
                // foward to park in storage unit 
                encoderDrive(DRIVE_SPEED, 12, 12, 5.0);
                // possible strafe left if on tape ? 
                //encoderDriveStrafe(DRIVE_SPEED, 3, 3, 5.0);
            

    }






    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }
    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed, double leftInches, double rightInches, double timeoutS) {
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

    public void encoderDriveStrafe(double speed, double leftInches, double rightInches, double timeoutS) {

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

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }


    public void armEncoderDrive(double speed, double inches, double timeoutS) {
        int newarmTarget;
        

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
           
            // Determine new target position, and pass to motor controller
            newarmTarget = arm.getCurrentPosition() + (int) (inches * ARM_PER_INCH);
            
            arm.setTargetPosition(newarmTarget);
          
            // Turn On RUN_TO_POSITION
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
           
            // reset the timeout time and start motion.
            runtime.reset();
            arm.setPower(Math.abs(speed));
            
            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() && (runtime.seconds() < timeoutS) && (arm.isBusy())) {// frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d ", newarmTarget);//newBackLeftTarget, newFrontRightTarget, newBackRightTarget);
                telemetry.addData("Path2", "Running at %7d  ",
                        arm.getCurrentPosition());
                
                telemetry.update();
            }

            // Stop all motion;
            arm.setPower(0);
           
            // Turn off RUN_TO_POSITION
            arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            
            sleep(250);   // optional pause after each move
        }
    }
    public boolean isDuckOrCubeDetected(){
        boolean isDetected = false;
        if (tfod != null) {
            // getUpdatedRecognitions() will return null if no new information is available since
            // the last time that call was made.
            List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
            if (updatedRecognitions != null) {
               // telemetry.addData("# Object Detected", updatedRecognitions.size());
                // step through the list of recognitions and display boundary info.
                int i = 0;

                for (Recognition recognition : updatedRecognitions) {
                    telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                    telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                            recognition.getLeft(), recognition.getTop());
                    telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                            recognition.getRight(), recognition.getBottom());
                    i++;
                    // check label to see if the camera now sees a Duck         ** ADDED **
                    if (recognition.getLabel().equals("Duck") || recognition.getLabel().equals("Cube") ) {
                       // telemetry.addData("Object Detected!!!", "Duck");
                        isDetected = true ;

                    } else {
                        telemetry.addData("Object Not Detected!!!", "Not DUCK OR CUBE");
                        isDetected = false;
                    }
                }
                telemetry.update();
            }
        }

        return isDetected;
    }
}