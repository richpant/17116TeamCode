package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "driver")
public class driver extends OpMode {
    private DcMotorEx rightFront;
    private DcMotorEx rightRear;
    private DcMotorEx leftFront;
    private DcMotorEx leftRear;
    private DcMotorEx leftLift;
    private DcMotorEx rightLift;
    private Servo claw;
    //private Servo rc;
    private DigitalChannel touch;


    @Override
    public void init() {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        leftLift = hardwareMap.get(DcMotorEx.class,"leftLift");
        rightLift = hardwareMap.get(DcMotorEx.class,"rightLift");
        claw = hardwareMap.get(Servo.class,"claw");
        //rc = hardwareMap.get(Servo.class,"rc");

        touch = hardwareMap.get(DigitalChannel.class,"touch");
        touch.setMode(DigitalChannel.Mode.INPUT);

        rightLift.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        leftLift.setMode((DcMotor.RunMode.RUN_USING_ENCODER));
        rightLift.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
        leftLift.setMode((DcMotor.RunMode.STOP_AND_RESET_ENCODER));
    }

public void loop()  {
//Drive Code---------------------------------------------------------------------------------------
    double rf = (gamepad1.right_stick_y + gamepad1.right_stick_x);
    double rr = (gamepad1.right_stick_y - gamepad1.right_stick_x);
    double lf = (gamepad1.left_stick_y - gamepad1.left_stick_x);
    double lr = (gamepad1.left_stick_y + gamepad1.left_stick_x);

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

        // Claw Code-------------------------------------------------------------------------------

    if(gamepad2.a){
        claw.setPosition(0.1);
        //rc.setPosition(0.1);
    } else if (gamepad2.b){
        claw.setPosition(0);
        //lc.setPosition(0);
    }

    //Touch Sensor Lift Code-----------------------------------------------------------------------
    if (touch.getState() == false) {
        telemetry.addData("touch", "is not pressed");
        leftLift.setVelocity(3000);
        rightLift.setVelocity(3000);
    }
    if(touch.getState() == false) {
        rightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftLift.setVelocity(0);
        rightLift.setVelocity(0);
        while (getRuntime() < 5 && touch.getState() == false) {
            rightLift.setVelocity(3000);
            leftLift.setVelocity(3000);
        }

        telemetry.addData("touch", "pressed");
    } else {
        touch.setState(true);
    }
}
}
