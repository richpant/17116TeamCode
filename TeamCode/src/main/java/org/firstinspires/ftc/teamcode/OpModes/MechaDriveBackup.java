package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "MechaDriveBackup")
public class MechaDriveBackup extends OpMode {
    private DcMotorEx rightFront;
    private DcMotorEx rightRear;
    private DcMotorEx leftFront;
    private DcMotorEx leftRear;
    private DcMotorEx leftLift;
    private DcMotorEx rightLift;
    private DcMotorEx middleLift;
    private DcMotorEx bill;
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
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightLift = hardwareMap.get(DcMotorEx.class, "rightLift");
        leftLift = hardwareMap.get(DcMotorEx.class, "leftLift");
        middleLift = hardwareMap.get(DcMotorEx.class, "middleLift");
        bill = hardwareMap.get(DcMotorEx.class, "bill");
        box = hardwareMap.get(Servo.class, "box");
        csLeft = hardwareMap.get(Servo.class, "csLeft");
        csRight = hardwareMap.get(Servo.class, "csRight");
        csMiddle = hardwareMap.get(Servo.class, "csMiddle");

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
        drive();
        lift();
        bill();
        box();
        capstone();
    }
    public void drive() {
        double left;
        double right;
        double rf = (gamepad1.right_stick_y + gamepad1.right_stick_x + gamepad1.left_stick_x);
        double rr = (gamepad1.right_stick_y + gamepad1.right_stick_x - gamepad1.left_stick_x);
        double lf = gamepad1.right_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x;
        double lr = (gamepad1.right_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x);

        double tablePosition = 0;
        if (Math.abs(rf) >= 0.1 || Math.abs(rr) >= 0.1 || Math.abs(lf) >= 0.1 || Math.abs(lr) >= 0.1) {
            rightFront.setVelocity(rf);
            rightRear.setVelocity(rr);
            leftRear.setVelocity(-lr);
            leftFront.setVelocity(lf);
        } else {
            rightFront.setVelocity(0);
            rightRear.setVelocity(0);
            leftRear.setVelocity(0);
            leftFront.setVelocity(0);
        }
    }

    // Lift ------------------------------------------------------------------------------------
    public void lift() {
        if (gamepad2.dpad_up) {
            rightLift.setTargetPosition(2180);
            leftLift.setTargetPosition(2180);
            middleLift.setTargetPosition(-3089);
            rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightLift.setVelocity(.9);
            leftLift.setVelocity(.9);
            middleLift.setVelocity(.5);

        } else if (gamepad2.dpad_left) {
            rightLift.setTargetPosition(680);
            leftLift.setTargetPosition(680);
            middleLift.setTargetPosition(-1800);
            rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightLift.setPower(.9);
            leftLift.setPower(.9);
            middleLift.setPower(.5);
        } else if (gamepad2.dpad_down) {
            rightLift.setTargetPosition(-10);
            leftLift.setTargetPosition(-10);
            middleLift.setTargetPosition(-10);
            rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightLift.setPower(.5);
            leftLift.setPower(.5);
            middleLift.setPower(.9);
            //2180
        } else if (gamepad2.dpad_right) {
            rightLift.setTargetPosition(680);
            leftLift.setTargetPosition(680);
            middleLift.setTargetPosition(-3089);
            rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightLift.setPower(.9);
            leftLift.setPower(.9);
            middleLift.setPower(.5);
        }
    }

    //Bill -----------------------------------------------------------------------------------
    public void bill() {
        if (gamepad2.right_trigger >= .1) {
            bill.setPower(1.0);
        } else if (gamepad2.left_trigger >= .1) {
            bill.setPower(-1.0);
        } else if (gamepad1.right_bumper) {
            bill.setPower(1);
        } else if (gamepad1.left_bumper) {
            bill.setPower(-1);
        } else {
            bill.setPower(0.0);
        }
    }

    //Box----------------------------------------------------------------------------------------
    public void box() {
        if (gamepad2.b) {
            box.setPosition(0.37);
        } else if (gamepad2.a) {
            box.setPosition(-0.37);
        }
    }

    //Capstone----------------------------------------------------------------------------------
    public void capstone()
    {
        if (gamepad2.x) {
            csRight.setPosition(0.37);
            csLeft.setPosition(0.37);
        } else if (gamepad2.y) {
            csLeft.setPosition(-0.37);
            csRight.setPosition(-0.37);
        } else if (gamepad2.right_stick_button) {
            csMiddle.setPosition(0.37);
        }
    }

    @Override
    public void stop() {
    }
}
