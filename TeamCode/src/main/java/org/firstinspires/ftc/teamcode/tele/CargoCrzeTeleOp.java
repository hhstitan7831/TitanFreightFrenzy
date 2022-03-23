package org.firstinspires.ftc.teamcode.tele;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class CargoCrzeTeleOp extends OpMode {

    DcMotor fl;
    DcMotor br;
    DcMotor fr;
    DcMotor bl;

    @Override
    public void init() {

        fl = hardwareMap.dcMotor.get("frontLeft");
        br = hardwareMap.dcMotor.get("backRight");
        fr = hardwareMap.dcMotor.get("frontRight");
        bl = hardwareMap.dcMotor.get("backLeft");

    }

    @Override
    public void loop() {
        if (Math.abs(gamepad1.left_stick_y) > .1){

            fl.setPower(gamepad1.left_stick_y);
            bl.setPower(gamepad1.left_stick_y);

        } else if (Math.abs(gamepad1.right_stick_y) > .1){

            fr.setPower(-gamepad1.right_stick_x);
            br.setPower(-gamepad1.right_stick_x);

        }

        else {
            fl.setPower(gamepad1.left_stick_y);
            bl.setPower(gamepad1.left_stick_y);
            fr.setPower(gamepad1.right_stick_x);
            br.setPower(gamepad1.right_stick_x);

        }

        
    }
}
