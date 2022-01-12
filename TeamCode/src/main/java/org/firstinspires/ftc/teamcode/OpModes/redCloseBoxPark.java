package org.firstinspires.ftc.teamcode.OpModes;

import static java.lang.Math.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class redCloseBoxPark extends LinearOpMode {
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    //private Servo flipper;
    private DcMotor lift;
    private DcMotor ducky;
    private CRServo boxRight;
    private CRServo boxLeft;

    @Override


    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        //flipper = hardwareMap.get(Servo.class,"flipper");
        lift = hardwareMap.get(DcMotor.class,"lift");
        boxRight = hardwareMap.get(CRServo.class, "boxRight");
        boxLeft = hardwareMap.get(CRServo.class, "boxLeft");
        ducky = hardwareMap.get(DcMotor.class, "ducky");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();

        while (opModeIsActive()) {
           /* liftExtend(100);
            sleep(-100);
            move(1000,1000,1000,1000);
            sleep(500);
            boxDrop();
            sleep(500);
            liftRetract(100);
            sleep(500);
            move(-1000,-1000,-1000,-1000);
            sleep(1000);
            move(200,200,-200,-200);
            move(200,200,200,200);
            sleep(2000);*/

            move(.5,.5,.5,.5);
            sleep(30000);


        }

    }

    public void move(double lf, double rf, double lr, double rr) {


        rightRear.setPower(rr);
        leftRear.setPower(lr);
        rightFront.setPower(-rf);
        leftFront.setPower(-lf);


        sleep(1200);

        rightRear.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        leftFront.setPower(0);

        sleep(500);

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
    }




    //---------------------Raise Lift--------------------------------------------------------------
    public void liftExtend(int encode){
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift.setTargetPosition(encode);

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lift.setPower(0.4);
        while (lift.isBusy()){
            sleep(50);
        }
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift.setPower(0);

    }

    //----------------------Box Drop Second Way-----------------------------------------------------------------
    public void boxDrop() {
        boxLeft.setPower(-0.25);
        boxRight.setPower(-0.25);
        sleep(500);
        boxLeft.setPower(0.25);
        boxRight.setPower(0.25);
        sleep(500);
    }

    //------------------------Lower Lift One Way-------------------------------------------------------------
    public void liftRetract(int encode){
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift.setTargetPosition(encode);

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lift.setPower(-0.4);
        while (lift.isBusy()){
            sleep(50);
        }
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift.setPower(0);

    }

    //-----------------------------------Duck Spinner-------------------------------------------------
    public void ducky(){
        ducky.setPower(-0.36);
        sleep(500);
        ducky.setPower(0.0);
    }
}
