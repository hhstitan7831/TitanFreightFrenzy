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
    DcMotor in;
    DcMotor out;
    Servo box;

    @Override
    public void init() {

        fl = hardwareMap.dcMotor.get("frontLeft");
        br = hardwareMap.dcMotor.get("backRight");
        fr = hardwareMap.dcMotor.get("frontRight");
        bl = hardwareMap.dcMotor.get("backLeft");
        in = hardwareMap.dcMotor.get("intake");
        out = hardwareMap.dcMotor.get("outtake");
        box = hardwareMap.servo.get("box");
    }

    @Override
    public void loop() {
        // movement
//No gamepad 2???
        if (Math.abs(gamepad1.left_stick_y) > .1 || Math.abs(gamepad1.right_stick_y) > .1) {

            fl.setPower(gamepad1.left_stick_y);
            bl.setPower(gamepad1.left_stick_y);
            fr.setPower(-gamepad1.right_stick_y);
            br.setPower(-gamepad1.right_stick_y);

        } else {
            fl.setPower(0);
            bl.setPower(0);
            fr.setPower(0);
            br.setPower(0);

        }
            if (Math.abs(gamepad1.right_trigger) > .1) {

                fr.setPower(-gamepad1.right_trigger);
                fl.setPower(-gamepad1.right_trigger);
                br.setPower(gamepad1.right_trigger);
                bl.setPower(gamepad1.right_trigger);

            } else {
                fl.setPower(0);
                bl.setPower(0);
                fr.setPower(0);
                br.setPower(0);

            }
            if (Math.abs(gamepad1.left_trigger) > .1) {

                fr.setPower(gamepad1.left_trigger);
                fl.setPower(gamepad1.left_trigger);
                br.setPower(-gamepad1.left_trigger);
                bl.setPower(-gamepad1.left_trigger);

            } else {
                fl.setPower(0);
                bl.setPower(0);
                fr.setPower(0);
                br.setPower(0);

                if (gamepad2.x) {

                    in.setPower(1);

                } else {

                    in.setPower(0);
                }
                if (gamepad2.y) {

                    in.setPower(-1);

                } else {

                    in.setPower(0);

                }
                if (gamepad2.b) {
                    box.setPosition(1);

                } else if (gamepad2.a) {

                    box.setPosition(.5);

                } else


                if (gamepad2.dpad_down) {

                    out.setPower(-1);
                    box.setPosition(.5);
                    }

                else  {

                    out.setPower(0);
                    box.setPosition(0);

                }


            }


            }}