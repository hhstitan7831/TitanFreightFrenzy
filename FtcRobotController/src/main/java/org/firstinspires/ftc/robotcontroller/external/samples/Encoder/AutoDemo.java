package org.firstinspires.ftc.robotcontroller.external.samples.Encoder;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="Demo")
public class AutoDemo extends LinearOpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor br;
    DcMotor bl;




    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 537.7 ;
    static final double     DRIVE_GEAR_REDUCTION    =  1 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.6 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.5;



    @Override
    public void runOpMode() {

        fl = hardwareMap.dcMotor.get("frontLeft");
        br = hardwareMap.dcMotor.get("backRight");
        fr = hardwareMap.dcMotor.get("frontRight");
        bl = hardwareMap.dcMotor.get("backLeft");


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

       /* // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d :%7d :%7d,
                fl.getCurrentPosition(),
                fr.getCurrentPosition(),
                br.getCurrentPosition(),
                bl.getCurrentPosition(),
                telemetry.update();
*/

        waitForStart();

        // forward
        encoderDrive(DRIVE_SPEED,  -50,  50, 5.0);
        //spin
        encoderDrive(DRIVE_SPEED,   60, 60, 4.0);






        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newflTarget;
        int newfrTarget;
        int newblTarget;
        int newbrTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newflTarget = fl.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newfrTarget = fr.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newblTarget = bl.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newbrTarget = br.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            fl.setTargetPosition(newflTarget);
            fr.setTargetPosition(newfrTarget);
            br.setTargetPosition(newbrTarget);
            bl.setTargetPosition(newblTarget);

            // Turn On RUN_TO_POSITION
            br.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            bl.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fr.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            fl.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            fr.setPower(Math.abs(speed));
            fl.setPower(Math.abs(speed));
            br.setPower(Math.abs(speed));
            bl.setPower(Math.abs(speed));


            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (fr.isBusy() && fl.isBusy() && br.isBusy() && bl.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1", "Running to %7d :%7d :%7d :%7d", newflTarget, newblTarget, newfrTarget, newbrTarget);
                telemetry.addData("Path2", "Running at %7d :%7d :%7d :%7d",
                        fr.getCurrentPosition(),
                        fl.getCurrentPosition(),
                        br.getCurrentPosition(),
                        bl.getCurrentPosition());


                telemetry.update();
            }

            // Stop all motion;
            fl.setPower(0);
            fr.setPower(0);
            br.setPower(0);
            bl.setPower(0);

            bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



        }

    }


}


