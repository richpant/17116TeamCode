package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
@TeleOp(name = "TeleAll")
public class TeleAll extends LinearOpMode {
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor leftLift;
    private DcMotor rightLift;
    private DcMotor middleLift;
    private DcMotor bill;
    private Servo box;
   // private Servo csLeft;
    //private Servo csRight;
    //private Servo csMiddle;

    @Override
    public void runOpMode() throws InterruptedException{

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        rightLift = hardwareMap.get(DcMotor.class, "rightLift");
        leftLift = hardwareMap.get(DcMotor.class, "leftLift");
        middleLift = hardwareMap.get(DcMotor.class, "middleLift");
        bill = hardwareMap.get(DcMotor.class, "bill");
        box = hardwareMap.get(Servo.class, "box");
      //  csLeft = hardwareMap.get(Servo.class, "csLeft");
      //  csRight = hardwareMap.get(Servo.class, "csRight");
        //csMiddle = hardwareMap.get(Servo.class, "csMiddle");
        telemetry.addData("Say", "Hello Dillion");
        middleLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        waitForStart();
        while(opModeIsActive()){
            telemetry.addData("lift", leftLift.getCurrentPosition());
            telemetry.addData("lift2", leftLift.getCurrentPosition());
            telemetry.update();

        }


    }
}
