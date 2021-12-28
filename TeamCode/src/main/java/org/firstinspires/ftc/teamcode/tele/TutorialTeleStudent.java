package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "TutorialTeleStudent")
public class TutorialTeleStudent extends OpMode {

    DcMotor frontLeft, frontRight, backLeft, backRight;
    double speed = 0.2;

    @Override
    public void init() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");
    }

    @Override
    public void loop() {
        /*
        if () {

        } else {

        }

        if () {

        } else {

        }
        */
    }
}
