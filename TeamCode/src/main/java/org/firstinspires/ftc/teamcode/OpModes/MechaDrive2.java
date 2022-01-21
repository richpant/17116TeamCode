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
    private DcMotor leftLift;
    private DcMotor rightLift;
    private DcMotor middleLift;
    private DcMotor frontIntake;
    private Servo box;
    private Servo csLeft;
    private Servo csRight;
    private Servo csMiddle;


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
        rightLift = hardwareMap.get(DcMotor.class, "rightLift");
        leftLift = hardwareMap.get(DcMotor.class, "leftLift");
        middleLift = hardwareMap.get(DcMotor.class, "middleLift");
        frontIntake = hardwareMap.get(DcMotor.class, "frontIntake");
        box = hardwareMap.get(Servo.class, "box");
        csLeft = hardwareMap.get(Servo.class, "csLeft");
        csRight = hardwareMap.get(Servo.class, "csRight");
        csMiddle = hardwareMap.get(Servo.class, "csMiddle");

       // ducky = hardwareMap.get(DcMotor.class, "ducky");


        telemetry.addData("Say", "Hello Driver");
    }

    @Override
    public void loop() {
        double left;
        double right;
        double rf = (gamepad1.right_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x);
        double rr = (gamepad1.right_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x);
        double lf = gamepad1.right_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x;
        double lr = (gamepad1.right_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x) * -1;
        double tablePosition = 0;
        if (Math.abs(rf) >= 0.1 || Math.abs(rr) >= 0.1 || Math.abs(lf) >= 0.1 || Math.abs(lr) >= 0.1) {
            rightFront.setPower(rf);
            rightRear.setPower(rr);
            leftRear.setPower(lr);
            leftFront.setPower(lf);
        } else {
            rightFront.setPower(0);
            rightRear.setPower(0);
            leftRear.setPower(0);
            leftFront.setPower(0);
        }
        /*rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
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
        }*/


        // Lift ------------------------------------------------------------------------------------
        if (gamepad2.dpad_up) {
            rightLift.setPower(0.9);
            leftLift.setPower(0.9);
        } else if (gamepad2.dpad_right) {
            middleLift.setPower(0.9);     //Top Section
        } else if (gamepad2.dpad_left) {
            middleLift.setPower(-0.9);
        } else if (gamepad2.dpad_down) {
            rightLift.setPower(0);
            leftLift.setPower(0);
            middleLift.setPower(0);
        }

        //Intake-----------------------------------------------------------------------------------
        if (gamepad2.right_trigger >= .1) {
            frontIntake.setPower(1.0);
        } else if (gamepad2.left_trigger >= .1) {
            frontIntake.setPower(-1.0);
        } else {
            frontIntake.setPower(0.0);
        }

        //Box----------------------------------------------------------------------------------------
            if (gamepad2.b) {
                box.setPosition(0.37);
                //box.setPosition(0.7);                        //Still need to check at Meet
            } else if (gamepad2.a) {
                box.setPosition(-0.7);
            }

        //Capstone----------------------------------------------------------------------------------
        if (gamepad2.x) {
            csRight.setPosition(0.37);
            csLeft.setPosition(0.37);
        } else if (gamepad2.y) {
            csLeft.setPosition(-0.37);
            csRight.setPosition(-0.37);
        } else if (gamepad2.right_stick_button) {
            csMiddle.setPosition(0.37);
        }


        // Bill-------------------------------------------------------------------------------------
            if (gamepad1.right_bumper) {
                frontIntake.setPower(0.36);
            } else if (gamepad1.left_bumper) {
                frontIntake.setPower(-0.36);
            } else {
                frontIntake.setPower(0.0);
            }
    }

    @Override
    public void stop() {
    }
}