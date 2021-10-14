package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "TutorialAuton")
public class TutorialAuton extends LinearOpMode {

    // Declaration
    DcMotor frontLeft, frontRight, backLeft, backRight;
    double speed = 0.2;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        waitForStart();

        while (opModeIsActive()) {
            forward();
            sleep(1000);

            stopMotors();
            sleep(1000);

            backward();
            sleep(1000);

            stopMotors();
            sleep(1000);

            turnLeft();
            sleep(1000);

            stopMotors();
            sleep(1000);

            turnRight();
            sleep(1000);

            stopMotors();
            sleep(1000);

            strafeLeft();
            sleep(1000);

            stopMotors();
            sleep(1000);

            strafeRight();
            sleep(1000);

            stopMotors();
            sleep(1000);

        }
    }

    // Methods
    public void forward() {
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        backLeft.setPower(speed);
        backRight.setPower(-speed);
    }

    public void backward() {
        frontLeft.setPower(-speed);
        frontRight.setPower(speed);
        backLeft.setPower(-speed);
        backRight.setPower(speed);
    }

    public void stopMotors() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void turnLeft() {
        frontLeft.setPower(speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(speed);
    }

    public void turnRight() {
        frontLeft.setPower(-speed);
        frontRight.setPower(-speed);
        backLeft.setPower(-speed);
        backRight.setPower(-speed);
    }

    public void strafeLeft() {
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        backLeft.setPower(-speed);
        backRight.setPower(speed);
    }

    public void strafeRight() {
        frontLeft.setPower(-speed);
        frontRight.setPower(speed);
        backLeft.setPower(speed);
        backRight.setPower(-speed);
    }
}
