package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.lang.*;

public abstract class RobotMain358 extends VuforiaConceptClass {
    protected DcMotor lf;
    protected DcMotor lb;
    protected DcMotor rf;
    protected DcMotor rb;
    protected DcMotor slideMotor;
    protected DcMotor crMotor;
    protected CRServo intakeServo;

    public double driveFactor = 0.5; //for TeleOp
    public long lastTime = System.currentTimeMillis();
    public int timeElapsed = 1000; // this is in milliseconds

    final double DRIVE_FACTOR = 30 * (10/9.2) * (10/10.2);
    final double TURN_FACTOR = 5 * (90.0/95);

    public void TEST_CHASSIS_INITIALIZE() throws InterruptedException{
        lf = hardwareMap.dcMotor.get("lf");
        lb = hardwareMap.dcMotor.get("lb");
        rf = hardwareMap.dcMotor.get("rf");
        rb = hardwareMap.dcMotor.get("rb");

        intakeServo = hardwareMap.crservo.get("intakeServo");

        slideMotor = hardwareMap.dcMotor.get("slideMotor");
        slideMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        crMotor = hardwareMap.dcMotor.get("crMotor");
        crMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setZeroPowerBehavior(DcMotor. ZeroPowerBehavior.BRAKE);

        rf.setDirection(DcMotor.Direction.REVERSE);
        rb.setDirection(DcMotor.Direction.REVERSE);
        slideMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    // TeleOp Switch Drive
    public double switchDriveUp(double df){
        if (df == 0.2) {
            return 0.5;
        }
        else if (df == 0.5){
            return 0.8;
        }
        else if (df == 0.8){
            return 0.8;
        }
        return df;
    }

    public double switchDriveDown(double df){
        if (df == 0.2) {
            return 0.2;
        }
        else if (df == 0.5){
            return 0.2;
        }
        else if (df == 0.8){
            return 0.5;
        }
        return df;
    }

    public void forward (int inch, double power){
        int ticks = (int) (inch * DRIVE_FACTOR);

        //Reset Encoders
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        lf.setTargetPosition(lf.getCurrentPosition() - ticks);
        lb.setTargetPosition(lb.getCurrentPosition() - ticks);
        rf.setTargetPosition(rf.getCurrentPosition() - ticks);
        rb.setTargetPosition(rb.getCurrentPosition() - ticks);

        //Set Drive Power
        lf.setPower(0.5 * power);
        lb.setPower(0.5 * power);
        rf.setPower(0.5 * power);
        rb.setPower(0.5 * power);

        //Set to RUN_TO_POSITION mode
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (lf.isBusy() && lb.isBusy() && rf.isBusy() && rb.isBusy()){
            telemetry.addData("lf", -lf.getCurrentPosition());
            telemetry.addData("rf", -rf.getCurrentPosition());
            telemetry.addData("lb", -lb.getCurrentPosition());
            telemetry.addData("rb", -rb.getCurrentPosition());
            telemetry.update();
            //Wait Until Target Position is Reached
        }
        sleep(500);
    }

    public void turn (int degree, double power){
        int ticks = (int) (degree * TURN_FACTOR);

        //Reset Encoders
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        lf.setTargetPosition(lf.getCurrentPosition() - ticks);
        lb.setTargetPosition(lb.getCurrentPosition() - ticks);
        rf.setTargetPosition(rf.getCurrentPosition() + ticks);
        rb.setTargetPosition(rb.getCurrentPosition() + ticks);

        //Set Drive Power
        lf.setPower(0.5 * power);
        lb.setPower(0.5 * power);
        rf.setPower(0.5 * power);
        rb.setPower(0.5 * power);

        //Set to RUN_TO_POSITION mode
        lf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rf.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (lf.isBusy() && lb.isBusy() && rf.isBusy() && rb.isBusy()){
            telemetry.addData("lf", -lf.getCurrentPosition());
            telemetry.addData("rf", -rf.getCurrentPosition());
            telemetry.addData("lb", -lb.getCurrentPosition());
            telemetry.addData("rb", -rb.getCurrentPosition());
            telemetry.update();
            //Wait Until Target Position is Reached
        }
        sleep(500);
    }

    public void carousel(String state){

        //Reset Encoders
        crMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        crMotor.setTargetPosition(crMotor.getCurrentPosition() + 3000);

        //Set Drive Power
        if (state == "red") {
            crMotor.setPower(0.3);
        } else if (state == "blue") {
            crMotor.setPower(-0.3);
        }

        //Set to RUN_TO_POSITION mode
        crMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (crMotor.isBusy()){
        }
    }

    public void slide(int position){
        /**
         * LEVEL 1 = 500
         * LEVEL 2 = 1000
         * MAX / LEVEL 3 = 1700
         * */
        //Set Target Position
        slideMotor.setTargetPosition(slideMotor.getCurrentPosition() + position);

        //Set Drive Power
        if (position > slideMotor.getCurrentPosition()) {
            slideMotor.setPower(0.5);
        } else if (position < slideMotor.getCurrentPosition()){
            slideMotor.setPower(-0.3);
        }


        //Set to RUN_TO_POSITION mode
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (slideMotor.isBusy()){
        }
    }

    public void intake (){
        //Reset Encoders
        crMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Set Target Position
        crMotor.setTargetPosition(crMotor.getCurrentPosition() + 2000);

        crMotor.setPower(0.5);

        //Set to RUN_TO_POSITION mode
        crMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        while (crMotor.isBusy()){
            intakeServo.setPower(0.3);
        }
    }

}
