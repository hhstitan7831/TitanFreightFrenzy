package org.firstinspires.ftc.robotcontroller.external.samples.Encoder;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
@Autonomous(name="EncoderBluCycle", group="Pushbot")
public class jpegCargocrazyBluCycle extends LinearOpMode {

    DcMotor fl;
    DcMotor fr;
    DcMotor br;
    DcMotor bl;
    DcMotor out;
    DcMotor in;
    Servo box;

    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 537.7 ;
    static final double     DRIVE_GEAR_REDUCTION    =  1 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 3.6 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;

    static final double     COUNTS_PER_SPOOL_MOTOR_REV    = 2786.2  ;
    static final double     DRIVE_SPOOL_GEAR_REDUCTION    =  1 ;     // This is < 1.0 if geared UP
    static final double     SPOOL_DIAMETER_INCHES   = 2.2 ;     // For figuring circumference
    static final double     ROTATION_PER_INCH         = (COUNTS_PER_SPOOL_MOTOR_REV * DRIVE_SPOOL_GEAR_REDUCTION) /
            (SPOOL_DIAMETER_INCHES * 3.1415);


    @Override
    public void runOpMode() {

        fl = hardwareMap.dcMotor.get("frontLeft");
        br = hardwareMap.dcMotor.get("backRight");
        fr = hardwareMap.dcMotor.get("frontRight");
        bl = hardwareMap.dcMotor.get("backLeft");
        out = hardwareMap.dcMotor.get("outtake");
        in = hardwareMap.dcMotor.get("intake");
        box = hardwareMap.servo.get("box");


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        fl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        fr.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        br.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        bl.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        out.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        fl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        fr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        out.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0",  "Starting at %7d :%7d :%7d :%7d :%7d",
                fl.getCurrentPosition(),
                fr.getCurrentPosition(),
                br.getCurrentPosition(),
                bl.getCurrentPosition(),
                out.getCurrentPosition());

        telemetry.update();

        waitForStart();

        encoderDrive(DRIVE_SPEED,  -60,  60, 5.0);
        encoderDrive(DRIVE_SPEED,   -17, -17, 4.0);
        in.setPower(1);
        encoderDrive(.1, -9, 9, 4.0);
        encoderDrive(DRIVE_SPEED, 30, -30, 5.0);
        encoderBOX(DRIVE_SPEED,-1,4.0);
        box.setPosition(0.13);
        sleep(2000);
        box.setPosition(0.92);
        encoderDrive(DRIVE_SPEED, -10, 10, 4.0 );
        encoderDrive(.1, -17, 17, 4.0);
        encoderDrive(DRIVE_SPEED,30,-30,4.0);
        encoderBOX(DRIVE_SPEED,-1,4.0);
        box.setPosition(0.13);
        sleep(2000);
        box.setPosition(0.92);
       /* encoderDrive(DRIVE_SPEED,-18,-18,4.0);
        encoderDrive(DRIVE_SPEED,-15,15,5.0);
        encoderDrive(DRIVE_SPEED,29,29,4.0);
        encoderDrive(DRIVE_SPEED,45,-45,4.0);*/

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

    public void encoderBOX(double speed, double Inches, double timeoutS) {
        int newoutTarget;

        if (opModeIsActive()) {

            newoutTarget = out.getCurrentPosition() + (int)(Inches * ROTATION_PER_INCH);

            out.setTargetPosition(newoutTarget);

            out.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            out.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (out.isBusy())) {

                telemetry.addData("Path1", "Running to %7d", newoutTarget);
                telemetry.addData("Path2", "Running at %7d",
                        out.getCurrentPosition());

                telemetry.update();
            }
            out.setPower(0);

            out.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }
}
