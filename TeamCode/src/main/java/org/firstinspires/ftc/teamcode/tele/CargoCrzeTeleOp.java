package org.firstinspires.ftc.teamcode.tele;

import android.view.ViewGroup;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "pain")
public class CargoCrzeTeleOp extends OpMode {
//No servo???
    DcMotor fl;
    DcMotor br;
    DcMotor fr;
    DcMotor bl;
    //Servo balls;

    @Override
    public void init() {

        fl = hardwareMap.dcMotor.get("frontLeft");
        br = hardwareMap.dcMotor.get("backRight");
        fr = hardwareMap.dcMotor.get("frontRight");
        bl = hardwareMap.dcMotor.get("backLeft");
        //balls = hardwareMap.servo.get("balls");
        //Temp name
//No balls???
    }

    @Override
    public void loop() {
        // movement
//No gamepad 2???
        if (Math.abs(gamepad1.left_stick_y) > .1) {

            fl.setPower(gamepad1.left_stick_y);
            bl.setPower(gamepad1.left_stick_y);

        } else if (Math.abs(gamepad1.right_stick_y) > .1) {

            fr.setPower(-gamepad1.right_stick_y);
            br.setPower(-gamepad1.right_stick_y);

        } else {
            fl.setPower(0);
            bl.setPower(0);
            fr.setPower(0);
            br.setPower(0);


            if (Math.abs(gamepad1.right_trigger) > .1) {

                fr.setPower(-gamepad1.right_trigger);
                fl.setPower(-gamepad1.right_trigger);
                br.setPower(gamepad1.right_trigger);
                bl.setPower(gamepad1.right_trigger);
            }
            if (Math.abs(gamepad1.left_trigger) > .1) {

                fr.setPower(gamepad1.left_trigger);
                fl.setPower(gamepad1.left_trigger);
                br.setPower(-gamepad1.left_trigger);
                bl.setPower(-gamepad1.left_trigger);
            }
            //{if (gamepad2.b);

            //balls.setPosition(.1);
        //}
    }}}
