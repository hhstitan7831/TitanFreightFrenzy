package org.firstinspires.ftc.robotcontroller.external.samples.Encoder;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="NewbieAuto")

public class NewbieAuto extends LinearOpMode {
    DcMotor frontLeft;
    DcMotor ?;
    DcMotor ?;
    DcMotor backRight;

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        ? = hardwareMap.dcMotor.get("?");
        ? = hardwareMap.dcMotor.get("?");
        backRight = hardwareMap.dcMotor.get("backRight");

        waitForStart();
        // move forward
        frontLeft.setPower(?);
        backRight.setPower(?);

        //move backward


        // left turn


        //right turn 

    }
}
