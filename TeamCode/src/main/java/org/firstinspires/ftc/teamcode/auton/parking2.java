package org.firstinspires.ftc.teamcode.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous (name = "parking2")
<<<<<<< HEAD
=======
@Disabled
>>>>>>> parent of 5eba2de (Rearranged & Organized Classes)
public class parking2 extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    DcMotor carousel;
<<<<<<< HEAD
    DcMotor carouselRight;
=======
>>>>>>> parent of 5eba2de (Rearranged & Organized Classes)
    Servo claw;
    DcMotor arm;


<<<<<<< HEAD

=======
>>>>>>> parent of 5eba2de (Rearranged & Organized Classes)
    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
        carousel = hardwareMap.dcMotor.get("carousel");
<<<<<<< HEAD
        carouselRight = hardwareMap.dcMotor.get("carouselRight");
=======
>>>>>>> parent of 5eba2de (Rearranged & Organized Classes)
        arm = hardwareMap.dcMotor.get("arm");
        claw = hardwareMap.servo.get("claw");

        waitForStart();
<<<<<<< HEAD
        stop(20000);
        backward (.4, 8000);
=======
>>>>>>> parent of 5eba2de (Rearranged & Organized Classes)
        forward(.4, 2000);
        stop(250);

    }

    public void forward(double speed, long time) {
        frontLeft.setPower(-speed);
        frontRight.setPower(speed);
        backLeft.setPower(-speed);
        backRight.setPower(speed);
        sleep(time);
    }

    public void stop(long time) {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
        carousel.setPower(0);
        sleep(time);

    }
<<<<<<< HEAD
    public void backward (double speed, long time) {
        frontLeft.setPower(speed);
        frontRight.setPower(-speed);
        backLeft.setPower(speed);
        backRight.setPower(-speed);
        sleep(time);
    }
}


=======
}





>>>>>>> parent of 5eba2de (Rearranged & Organized Classes)
