package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "MechaDrive2")
public class MechaDrive2 extends OpMode {
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor lift;
    private DcMotor frontIntake;
    private CRServo boxLeft;
    private CRServo boxRight;
    private DcMotor ducky;


    /*private DcMotor liftMotor;
    public static final double MID_SERVO       =  0.5 ;
    public static final double LIFT_UP_POWER    =  0.45 ;
    public static final double LIFT_DOWN_POWER  = -0.45 ;
    double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.02 ;                 // sets rate to move servo*/
    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        lift = hardwareMap.get(DcMotor.class, "lift");
        frontIntake = hardwareMap.get(DcMotor.class, "frontIntake");
        boxRight = hardwareMap.get(CRServo.class, "boxRight");
        boxLeft = hardwareMap.get(CRServo.class, "boxLeft");
        ducky = hardwareMap.get(DcMotor.class, "ducky");


        telemetry.addData("Say", "Hello Driver");
    }

    @Override
    public void loop() {
        double left;
        double right;
        /*double rf = gamepad1.right_stick_y -gamepad1.right_stick_x -gamepad1.left_stick_x;
        double rr = (gamepad1.right_stick_y - gamepad1.right_stick_x +gamepad1.left_stick_x);
        double lf = gamepad1.right_stick_y + gamepad1.right_stick_x  -gamepad1.left_stick_x;
        double lr = (-gamepad1.right_stick_y +gamepad1.right_stick_x +gamepad1.left_stick_x);
        double tablePosition = 0;
        if(Math.abs(rf) >= 0.1 || Math.abs(rr) >= 0.1 || Math.abs(lf) >= 0.1 || Math.abs(lr) >= 0.1){
            rightFront.setPower(rf);
            rightRear.setPower(rr);
            leftRear.setPower(lr);
            leftFront.setPower(lf);
        }
        else{
            rightFront.setPower(0);
            rightRear.setPower(0);
            leftRear.setPower(0);
            leftFront.setPower(0);
        }*/
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        if (gamepad1.right_stick_y >= 0.1 || gamepad1.right_stick_y <= -0.1 || gamepad1.right_stick_x >= 0.1 || gamepad1.right_stick_x <= -0.1 || gamepad1.left_stick_x >= 0.1 || gamepad1.left_stick_x <= -0.1) {
            rightFront.setPower((gamepad1.right_stick_y) - (gamepad1.right_stick_x) - (gamepad1.left_stick_x));
            rightRear.setPower((gamepad1.right_stick_y) + (gamepad1.right_stick_x) - (gamepad1.left_stick_x));
            leftFront.setPower((gamepad1.right_stick_y) + (gamepad1.right_stick_x) + (gamepad1.left_stick_x));
            leftRear.setPower((gamepad1.right_stick_y) - (gamepad1.right_stick_x) + (gamepad1.left_stick_x));
        } else {
            rightFront.setPower(0);
            rightRear.setPower(0);
            leftFront.setPower(0);
            leftRear.setPower(0);
        }


        // Lift ------------------------------------------------------------------------------------
        int hold = 4;
        if (gamepad2.dpad_right) {
            lift.setPower(0.5);
            hold = 1;
        } else if (gamepad2.dpad_left) {
            lift.setPower(-0.5);
            hold = 2;
        } else {
            lift.setPower(0.0);
            hold = 0;
        }
        // Hold Lift-------------------------------------------------------------------------------
        /*if(hold == 1 && !gamepad1.dpad_up && !gamepad1.dpad_down ) {
            lift.setPower(0.1);
        }
        else if (hold == 2 && !gamepad1.dpad_up && !gamepad1.dpad_down){
            lift.setPower(0.1);
        }  else {
            lift.setPower(0.0);
        }*/
        //Intake-----------------------------------------------------------------------------------
        if (gamepad2.right_trigger >= .1) {
            frontIntake.setPower(1.0);
        } else if (gamepad2.left_trigger >= .1) {
            frontIntake.setPower(-1.0);
        } else {
            frontIntake.setPower(0.0);
        }

        //Box----------------------------------------------------------------------------------------
        if (gamepad2.right_bumper) {
            boxRight.setPower(0.4);
            boxLeft.setPower(-0.4);
        } else if (gamepad2.left_bumper) {
            boxRight.setPower(-0.4);
            boxLeft.setPower(0.4);
        } else if (gamepad2.x) {
            boxLeft.setPower(0.4);
        } else if (gamepad2.y){
            boxRight.setPower((0.4));
        } else if (gamepad2.b) {
            boxLeft.setPower(-0.4);
        } else if (gamepad2.a) {
            boxRight.setPower(-0.4);
        } else {
            boxRight.setPower(0);
            boxLeft.setPower(0);
        }

        // Duck Spinner-------------------------------------------------------------------------------

        if (gamepad1.right_bumper) {
            ducky.setPower(0.36);
        } else if (gamepad1.left_bumper) {
            ducky.setPower(-0.36);
        } else {
            ducky.setPower(0.0);
        }
    }

    @Override
    public void stop() {
    }
}