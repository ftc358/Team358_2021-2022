package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;

public abstract class RobotMain358 extends LinearOpMode {

    protected DcMotor lf;
    protected DcMotor lb;
    protected DcMotor rf;
    protected DcMotor rb;

    protected CRServo carouselServo;

    protected CRServo slideServo;

    public double driveFactor = 1;
    public long lastTime = System.currentTimeMillis();
    public int timeElapsed = 2000; // this is in milliseconds

    public void initialize() throws InterruptedException{
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");

        carouselServo = hardwareMap.crservo.get("carouselServo");
        slideServo = hardwareMap.crservo.get("slideServo");

        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rf.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.REVERSE);
    }

    // TeleOp Switch Drive
    public double switchDrive(double df){
        if (df == 0.3) {
            return 0.7;
        }
        else if (df == 0.7){
            return 1;
        }
        else if (df == 1){
            return 0.3;
        }
        return df;
    }

}
