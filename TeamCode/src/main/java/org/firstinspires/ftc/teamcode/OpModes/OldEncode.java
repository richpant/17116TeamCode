package org.firstinspires.ftc.teamcode.OpModes;

import static java.lang.Math.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class OldEncode extends LinearOpMode {
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private Servo flipper;
    private DcMotor lift;
    private Servo box;
    private Servo frontTurnTable;
    private Servo backTurnTable;

    @Override


    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        //flipper = hardwareMap.get(Servo.class,"flipper");
        lift = hardwareMap.get(DcMotor.class,"lift");
        box = hardwareMap.get(Servo.class, "box");
        frontTurnTable = hardwareMap.get(Servo.class, "frontTurnTable");
        backTurnTable = hardwareMap.get(Servo.class, "backTurnTable");
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            //gabemove(200,200,-200,-200);
            //gabemove(200,200,200,200);
            //flipper.setPosition(1.0);


            sleep(4000);


        }

    }

    public void move(double lf, double rf, double lr, double rr) {


        rightRear.setPower(rr);
        leftRear.setPower(lr);


        sleep(500);

        rightRear.setPower(0);
        leftRear.setPower(0);

        sleep(500);

    }

    //----------------------------encoder-----------------
    public void mf(int rf, int rb, int lf, int lb) {
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

//----------------------Turn Turn Table-------------------------------------------------------------
    public void turnTable(){
        frontTurnTable.setPosition(0.5);
        backTurnTable.setPosition(-0.5);
            sleep(500);
    }

    //---------------------Extend Lift--------------------------------------------------------------
    public void liftExtend(int encode){
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift.setTargetPosition(encode);

        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        lift.setPower(0.6);
        while (lift.isBusy()){
            sleep(50);
        }
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        lift.setPower(0);

    }

    //----------------------Box Drop-----------------------------------------------------------------
    public void boxDrop(){
        box.setPosition(0.5);
        box.setPosition(-0.5);
            sleep(500);
    }

  //------------------------Retract Lift-------------------------------------------------------------
  public void liftRetract(int encode){
      lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      lift.setTargetPosition(encode);

      lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

      lift.setPower(-0.6);
      while (lift.isBusy()){
          sleep(50);
      }
      lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

      lift.setPower(0);

  }
}


//h