package org.firstinspires.ftc.teamcode.OpModes;


import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "blueDrive")
public class blueDrive extends OpMode {
    private DcMotorEx rightFront;
    private DcMotorEx rightRear;
    private DcMotorEx leftFront;
    private DcMotorEx leftRear;
    private DcMotorEx bill;
    private Servo box;
    private Servo horizontalCS;
    private Servo verticalCS;
    private CRServo inOut;
    private DcMotorEx leftLift;
    private DcMotorEx rightLift;
    private DcMotorEx middleLift;
    private ColorSensor boxCS;
    private RevBlinkinLedDriver lights;


    double xPos = .95;
    double yPos = .7;

    View relativeLayout;
    int checker = 0;

    /*private DcMotor liftMotor;
    public static final double MID_SERVO       =  0.5 ;
    public static final double LIFT_UP_POWER    =  0.45 ;
    public static final double LIFT_DOWN_POWER  = -0.45 ;
    double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.02 ;                 // sets rate to move servo*/
    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightLift = hardwareMap.get(DcMotorEx.class, "rightLift");
        leftLift = hardwareMap.get(DcMotorEx.class, "leftLift");
        middleLift = hardwareMap.get(DcMotorEx.class, "middleLift");
        bill = hardwareMap.get(DcMotorEx.class, "bill");
        box = hardwareMap.get(Servo.class, "box");
        horizontalCS = hardwareMap.get(Servo.class, "horizontalCS");
        verticalCS = hardwareMap.get(Servo.class, "verticalCS");
        inOut = hardwareMap.get(CRServo.class, "inOut");
        boxCS = hardwareMap.get(ColorSensor.class, "boxCS");
        lights = hardwareMap.get(RevBlinkinLedDriver.class,"lights");
        lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLACK);
        // rightCS = hardwareMap.get(ColorSensor.class, "rightCS");
        // leftCS = hardwareMap.get(ColorSensor.class, "leftCS");




        rightLift.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        leftLift.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        middleLift.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        rightLift.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        leftLift.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        middleLift.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));

        telemetry.addData("Say", "Hello Driver");
    }

    @Override
    public void loop() {
        double left;
        double right;
        double rf = (gamepad1.right_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x);
        double rr = (gamepad1.right_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x);
        double lf = gamepad1.right_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x;
        double lr = (gamepad1.right_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x);

        double tablePosition = 0;
        if (Math.abs(rf) >= 0.1 || Math.abs(rr) >= 0.1 || Math.abs(lf) >= 0.1 || Math.abs(lr) >= 0.1) {
            rightFront.setVelocity(rf * 3000);
            rightRear.setVelocity(rr * 3000);
            leftRear.setVelocity(-lr * 3000);
            leftFront.setVelocity(lf * 3000);
        } else {
            rightFront.setVelocity(0);
            rightRear.setVelocity(0);
            leftRear.setVelocity(0);
            leftFront.setVelocity(0);
        }

        // Lift ------------------------------------------------------------------------------------
        if (gamepad2.dpad_up) {
            rightLift.setTargetPosition(2200);
            leftLift.setTargetPosition(2200);
            middleLift.setTargetPosition(-3089);
            rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightLift.setVelocity(2700);
            leftLift.setVelocity(2700);
            middleLift.setVelocity(1500);

        } else if (gamepad2.dpad_left) {
            rightLift.setTargetPosition(680);
            leftLift.setTargetPosition(680);
            middleLift.setTargetPosition(-1800);
            rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightLift.setVelocity(2700);
            leftLift.setVelocity(2700);
            middleLift.setVelocity(1500);
        } else if (gamepad2.dpad_down) {
            rightLift.setTargetPosition(-10);
            leftLift.setTargetPosition(-10);
            middleLift.setTargetPosition(-10);
            rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightLift.setVelocity(1100);
            leftLift.setVelocity(1100);
            middleLift.setVelocity(3200);
            //2180
        } else if (gamepad2.dpad_right) {
            rightLift.setTargetPosition(680);
            leftLift.setTargetPosition(680);
            middleLift.setTargetPosition(-3089);
            rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightLift.setVelocity(2700);
            leftLift.setVelocity(2700);
            middleLift.setVelocity(1500);

               /* rightLift.setTargetPosition(900);
                leftLift.setTargetPosition(900);
                middleLift.setTargetPosition(-2300);

                rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                rightLift.setVelocity(2700);
                leftLift.setVelocity(2700);
                middleLift.setVelocity(1500);*/

        }

        //Bill -----------------------------------------------------------------------------------

            if (gamepad2.right_trigger >= .1) {
                bill.setVelocity(3000);
            } else if (gamepad2.left_trigger >= .1) {
                bill.setVelocity(-3000);
            } else if (gamepad1.right_bumper) {
                bill.setVelocity(3000);
            } else if (gamepad1.left_bumper) {
                bill.setVelocity(-3000);
            } else {
                bill.setVelocity(0.0);
            }


        //Box----------------------------------------------------------------------------------------
        if (gamepad2.b) {
            box.setPosition(.25);
        } else if (gamepad2.a) {
            box.setPosition(0.37);
        } else if (gamepad2.x) {
            box.setPosition(.4);
        }

        //Tape Measure------------------------------------------------------------------------------

        if (gamepad2.right_stick_y >= .5) {
            yPos = yPos - .002;
            verticalCS.setPosition(yPos);
        } else if (gamepad2.right_stick_y <= -.5) {
            yPos = yPos + .002;
            verticalCS.setPosition(yPos);
        } else {
            verticalCS.setPosition(yPos);
        }

        if (gamepad2.left_stick_x >= .5) {
            xPos = xPos + .0005;
            horizontalCS.setPosition(xPos);
        } else if (gamepad2.left_stick_x <= -.5) {
            xPos = xPos - .0005;
            horizontalCS.setPosition(xPos);
        } else {
            horizontalCS.setPosition(xPos);
        }


        if (gamepad2.right_bumper) {
            inOut.setPower(1);
        } else if (gamepad2.left_bumper) {
            inOut.setPower(-1);
        } else {
            inOut.setPower(0);
        }

        // Color Sensor-----------------------------------------------------------------------------
            if (boxCS.green() >=300) {
                lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BEATS_PER_MINUTE_LAVA_PALETTE);
            } else {
                lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.COLOR_WAVES_OCEAN_PALETTE);
            }


    }
}
