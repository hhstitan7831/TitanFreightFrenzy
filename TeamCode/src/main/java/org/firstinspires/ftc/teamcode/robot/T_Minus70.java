package org.firstinspires.ftc.teamcode.robot;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class T_Minus70 {
    //Movement / Base Motors
    public DcMotor frontLeft = null, frontRight = null, backLeft = null, backRight;
    //Game-Related
    public DcMotor carousel = null, arm = null;
    public Servo claw = null;

    //This is like big robot hub so we don't have to repetitively put this info in every class

    //public static final double servoStart = 2;
    //public static final double baseSpd = .6;

    HardwareMap hwMap;

    public T_Minus70() {

    }

    public void init (HardwareMap ahwMap) {

        hwMap = ahwMap;

        //Base Motors
        frontLeft = hwMap.get(DcMotor.class, "frontLeft");
        frontRight = hwMap.get(DcMotor.class, "frontRight");
        backLeft = hwMap.get(DcMotor.class, "backLeft");
        backRight = hwMap.get(DcMotor.class, "backRight");

        //Game Elements
        carousel  = hwMap.get(DcMotor.class, "carousel");
        arm = hwMap.get(DcMotor.class, "arm");
        claw = hwMap.get(Servo.class, "claw");

        //Set Behaviors
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
<<<<<<< Updated upstream


    public void armEncoderDrive(double speed,
                                double inches,
                                double timeoutS) {
        double x = speed+inches+timeoutS;
        }
    }
=======
}
>>>>>>> Stashed changes
