package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "TutorialAutonStudent")
public class TutorialAutonStudent extends LinearOpMode {

    // Declaration
    DcMotor frontLeft, frontRight, backLeft, backRight;
    double speed = 1;

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
            break;
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

    }

    public void stopMotors() {

    }

    public void turnLeft() {

    }

    public void turnRight() {

    }

    public void strafeLeft() {

    }

    public void strafeRight() {

    }
}
