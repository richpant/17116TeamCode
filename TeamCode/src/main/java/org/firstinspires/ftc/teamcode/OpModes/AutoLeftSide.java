package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous

public class AutoLeftSide extends LinearOpMode {
    private DcMotorEx rightFront;
    private DcMotorEx rightRear;
    private DcMotorEx leftFront;
    private DcMotorEx leftRear;
    private DcMotorEx leftLift;
    private DcMotorEx rightLift;
    private Servo lc;
    private Servo rc;

    @Override


    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightLift = hardwareMap.get(DcMotorEx.class, "rightLift");
        leftLift = hardwareMap.get(DcMotorEx.class, "leftLift");
        lc = hardwareMap.get(Servo.class, "lc");
        rc = hardwareMap.get(Servo.class, "rc");


        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        rightLift.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));
        leftLift.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));
        rightFront.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));
        rightRear.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));
        leftFront.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));
        leftRear.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));

        rightLift.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));
        leftLift.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));
        rightFront.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));
        rightRear.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));
        leftFront.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));
        leftRear.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));


        while(opModeIsActive()) {
            clawClose();
            drive(1000,1000,1000,1000);
            clawDrop();

        }

    }


    //----------------------------encoder-----------------
    public void drive(int rf, int rb, int lf, int lb) {
        rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        rightRear.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        leftRear.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        rightFront.setTargetPosition(rf);
        rightRear.setTargetPosition(rb);
        leftFront.setTargetPosition(lf);
        leftRear.setTargetPosition(lb);

        rightFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftFront.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rightRear.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftRear.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);


        rightRear.setVelocity(1000);
        rightFront.setVelocity(1000);

        leftFront.setVelocity(1000);
        leftRear.setVelocity(1000);

        while (leftFront.isBusy() && leftRear.isBusy() && rightFront.isBusy() && rightRear.isBusy()) {
            sleep(50);
        }
        {
            rightFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            leftFront.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            rightRear.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
            leftRear.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

            rightRear.setVelocity(0);
            leftFront.setVelocity(0);
            rightFront.setVelocity(0);
            leftRear.setVelocity(0);
        }
        sleep(200);

    }

    public void clawClose() {
        lc.setPosition(0.5);
        rc.setPosition(0.5);
        sleep(2500);
    }
    public void clawDrop(){
        rc.setPosition(0.0);
        lc.setPosition(0.0);
        sleep(2500);
    }
}