package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;


@Autonomous (name = "Gyro Auto")
public class gyroAuto extends LinearOpMode {
    T_Minus70 robot = new T_Minus70();
    private ElapsedTime     runtime = new ElapsedTime();

    private Orientation lastAngles = new Orientation();
    private double currAngle = 0.0;

    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(hardwareMap);


        waitForStart();
        turnTo(90);

    }

    public void resetAngle() {
        lastAngles = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        currAngle = 0;

    }

    public double getAngle() {

        Orientation orientation = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder .ZYX, AngleUnit.DEGREES);

        double deltaAngle = orientation.firstAngle - lastAngles.firstAngle;

        if (deltaAngle < -180) {
            deltaAngle += 360;
        }
        else if (deltaAngle > 180) {
            deltaAngle -= 360;
        }


        currAngle += deltaAngle;
        lastAngles = orientation;
        return currAngle;

    }

    public void turn (double degrees) {
        resetAngle();

        double error = degrees;
        while (opModeIsActive() && Math.abs(error) > 2) {
            double motorPower = (error < 0 ? -0.3 : 0.3);
            robot.frontLeft.setPower(motorPower);
            robot.frontRight.setPower(motorPower);
            robot.backLeft.setPower(motorPower);
            robot.backRight.setPower(motorPower);
            error = degrees - getAngle();
        }
    }

    public void turnTo (double degrees) {
        Orientation orientation = robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder .ZYX, AngleUnit.DEGREES);

        double error = degrees - orientation.firstAngle;

        if (error > 180) {
            error -= 360;
        }
        else if (error < -180) {
            error += 360;
        }
        turn(error);
    }

    public double getAbsoluteAngle() { return robot.imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder .ZYX, AngleUnit.DEGREES).firstAngle; }

}
