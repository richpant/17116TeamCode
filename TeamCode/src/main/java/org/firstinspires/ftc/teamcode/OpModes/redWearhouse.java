package org.firstinspires.ftc.teamcode.OpModes;

import static java.lang.Math.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class redWearhouse extends LinearOpMode {
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor leftLift;
    private DcMotor rightLift;
    private DcMotor middleLift;
    private DcMotor bill;
    private Servo box;
    private Servo verticalCS;
  //  private Servo csLeft;
  //  private Servo csRight;
  //  private Servo csMiddle;

    @Override


    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        rightLift = hardwareMap.get(DcMotor.class, "rightLift");
        leftLift = hardwareMap.get(DcMotor.class, "leftLift");
        middleLift = hardwareMap.get(DcMotor.class, "middleLift");
        bill = hardwareMap.get(DcMotor.class, "bill");
        box = hardwareMap.get(Servo.class, "box");
        verticalCS = hardwareMap.get(Servo.class, "verticalCS");
    //    csLeft = hardwareMap.get(Servo.class, "csLeft");
    //    csRight = hardwareMap.get(Servo.class, "csRight");
    //    csMiddle = hardwareMap.get(Servo.class, "csMiddle");

        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        rightLift.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        leftLift.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        middleLift.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        rightFront.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        rightRear.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        leftFront.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        leftRear.setMode((DcMotor.RunMode.RUN_USING_ENCODER));

        rightLift.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        leftLift.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        middleLift.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        rightFront.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        rightRear.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        leftFront.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        leftRear.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));



        waitForStart();

        while (opModeIsActive()) {
            //tape measurer
            tape();
            //forward
            drive (875,875,875,875);
            //turn left
            drive(-450,-450,450,450);
            //forward slightly
            drive(150,150,150,150);
            //lift up
            raiseLift();
            sleep(1000);
            rightLift.setPower(.5);
            leftLift.setPower(.5);
            //drop cube
            boxDrop();
            sleep(1000);
            //lift down
            retractLift();
            //backwards a little
            drive(-200,-200,-200,-200);
            //turn left more
            drive(-350,-350,350,350);
            //strafe left
            driveslow(1700,-1700,-1700,1700);
            //backwards
            drive(-1750,-1750,-1750,-1750);
            //drive slow into warehouse
            driveslow(-300,-300,-300,-300);



            sleep(25000);




        }

    }



    //----------------------------encoder-----------------
    public void drive(int rf, int rb, int lf, int lb) {
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightFront.setTargetPosition(rf);
        rightRear.setTargetPosition(rb);
        leftFront.setTargetPosition(lf);
        leftRear.setTargetPosition(lb);

        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        rightRear.setPower(0.6);
        rightFront.setPower(0.6);

        leftFront.setPower(0.6);
        leftRear.setPower(0.6);

        while (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(50);
        }
        {
            rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            rightRear.setPower(0);
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftRear.setPower(0);
        }
        sleep(500);

    }

    public void driveslow(int rf, int rb, int lf, int lb) {
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        rightFront.setTargetPosition(rf);
        rightRear.setTargetPosition(rb);
        leftFront.setTargetPosition(lf);
        leftRear.setTargetPosition(lb);

        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        rightRear.setPower(0.3);
        rightFront.setPower(0.3);

        leftFront.setPower(0.3);
        leftRear.setPower(0.3);

        while (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(50);
        }
        {
            rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            rightRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            leftRear.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            rightRear.setPower(0);
            leftFront.setPower(0);
            rightFront.setPower(0);
            leftRear.setPower(0);
        }
        sleep(500);

    }


    public void raiseLift()
    {
        rightLift.setTargetPosition(2180);
        leftLift.setTargetPosition(2180);
        middleLift.setTargetPosition(- 3089);

        rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightLift.setPower(.9);
        leftLift.setPower(.9);
        middleLift.setPower(.5);

        while (leftLift.isBusy() && rightLift.isBusy() && middleLift.isBusy()) {
            sleep(50);
        }


    }



    public void boxDrop()
    {
        box.setPosition(.875);
        sleep(750);
        box.setPosition(-1.0);
        sleep(750);
    }


    public void retractLift() {


        middleLift.setTargetPosition(0);
        middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        middleLift.setPower(.9);
        sleep(1000);

        rightLift.setTargetPosition(0);
        leftLift.setTargetPosition(0);

        rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightLift.setPower(.7);
        leftLift.setPower(.7);

    }

    public void spin()
    {
        bill.setPower(-.8);
        sleep(2500);
        bill.setPower(0);
    }

    public void tape()
    {
        verticalCS.setPosition(0.5);
        sleep(1000);
    }
}


