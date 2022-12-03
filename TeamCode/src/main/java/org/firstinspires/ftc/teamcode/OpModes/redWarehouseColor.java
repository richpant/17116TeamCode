package org.firstinspires.ftc.teamcode.OpModes;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.SwitchableLight;

@Autonomous

public class redWarehouseColor extends LinearOpMode {
    private DcMotorEx rightFront;
    private DcMotorEx rightRear;
    private DcMotorEx leftFront;
    private DcMotorEx leftRear;
    private DcMotorEx leftLift;
    private DcMotorEx rightLift;
    private DcMotorEx middleLift;
    private DcMotorEx bill;
    private Servo box;
    NormalizedColorSensor leftCS;
    NormalizedColorSensor rightCS;
    NormalizedColorSensor boxCS;
    private Servo verticalCS;

    View relativeLayout;
    int checker = 3;
    @Override


    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");
        rightLift = hardwareMap.get(DcMotorEx.class, "rightLift");
        leftLift = hardwareMap.get(DcMotorEx.class, "leftLift");
        middleLift = hardwareMap.get(DcMotorEx.class, "middleLift");
        bill = hardwareMap.get(DcMotorEx.class, "bill");
        box = hardwareMap.get(Servo.class, "box");
        verticalCS = hardwareMap.get(Servo.class, "verticalCS");

        int relativeLayoutId = hardwareMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hardwareMap.appContext.getPackageName());
        relativeLayout = ((Activity) hardwareMap.appContext).findViewById(relativeLayoutId);





        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        rightLift.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));
        leftLift.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));
        middleLift.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));
        rightFront.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));
        rightRear.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));
        leftFront.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));
        leftRear.setMode((DcMotorEx.RunMode.RUN_USING_ENCODER));

        rightLift.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));
        leftLift.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));
        middleLift.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));
        rightFront.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));
        rightRear.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));
        leftFront.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));
        leftRear.setMode((DcMotorEx.RunMode.STOP_AND_RESET_ENCODER));

        try {
            runSample();
        } finally {
            relativeLayout.post(new Runnable() {
                public void run() {
                    relativeLayout.setBackgroundColor(Color.WHITE);
                }
            });
        }}

    protected void runSample() {


        leftCS = hardwareMap.get(NormalizedColorSensor.class, "leftCS");
        rightCS = hardwareMap.get(NormalizedColorSensor.class, "rightCS");



        waitForStart();

        while (opModeIsActive()) {
            // raise tape measurer
            tape();
            /*//forward
            drive(750, 750, 750, 750);
            //check
            //turn left
            drive(-450,-450,450,450);
            leftCScolorCheck();
            rightCScolorCheck();
            drive(-200, 200, 200, -200);
            leftCScolorCheck();
            rightCScolorCheck();
            driveslow(400,-400,-400,400);
            leftCScolorCheck();
            rightCScolorCheck();
            driveslow(-200,200,200,-200);*/

            box.setPosition(.25);
            //forward
            drive(725, 725, 725, 725);
            //check
            leftCScolorCheck();
            rightCScolorCheck();
            drive(-200, 200, 200, -200);
            leftCScolorCheck();
            rightCScolorCheck();
            driveslow(250,-250,-250,250);
            leftCScolorCheck();
            rightCScolorCheck();
            driveslow(-100,100,100,-100);

            level();



            sleep(25000);

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
        sleep(300);

    }

    public void driveslow(int rf, int rb, int lf, int lb) {
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


        rightRear.setVelocity(900);
        rightFront.setVelocity(900);

        leftFront.setVelocity(900);
        leftRear.setVelocity(900);

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

            sleep(500);
        }
    }


    /*public void raiseLifttop() {
        rightLift.setTargetPosition(2180);
        leftLift.setTargetPosition(2180);
        middleLift.setTargetPosition(-3089);

        rightLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        middleLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        rightLift.setVelocity(2700);
        leftLift.setVelocity(2700);
        middleLift.setVelocity(1500);

        while (leftLift.isBusy() && rightLift.isBusy() && middleLift.isBusy()) {
            sleep(50);
        }
    }

    public void raiseLiftmiddle() {
        rightLift.setTargetPosition(1450);
        leftLift.setTargetPosition(1450);
        middleLift.setTargetPosition(-1900);

        rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightLift.setVelocity(2700);
        leftLift.setVelocity(2700);
        middleLift.setVelocity(1500);

        while (leftLift.isBusy() && rightLift.isBusy() && middleLift.isBusy()) {
            sleep(50);
        }
    }

    public void raiseLiftbottom() {
        rightLift.setTargetPosition(900);
        leftLift.setTargetPosition(900);
        middleLift.setTargetPosition(-2000);

        rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightLift.setVelocity(2700);
        leftLift.setVelocity(2700);
        middleLift.setVelocity(1500);

        while (leftLift.isBusy() && rightLift.isBusy() && middleLift.isBusy()) {
            sleep(50);
        }
    }




    public void boxDrop() {
        box.setPosition(.4);
        sleep(2750);
        box.setPosition(.25);
        sleep(750);
    }


    public void retractLift() {


        middleLift.setTargetPosition(0);
        middleLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        middleLift.setVelocity(2000); // NEED TO MAKE SLOWER BECAUSE IT HITS INTAKE
        sleep(500);

        rightLift.setTargetPosition(0);
        leftLift.setTargetPosition(0);

        rightLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        rightLift.setVelocity(2100);
        leftLift.setVelocity(2100);

    }

    public void spin() {
        bill.setVelocity(-2400);
        sleep(3000);
        bill.setVelocity(0);
    }*/




    public void raiseLifttop() {
        rightLift.setTargetPosition(2180);
        leftLift.setTargetPosition(2180);
        middleLift.setTargetPosition(-3089);

        rightLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        middleLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        rightLift.setVelocity(2700);
        leftLift.setVelocity(2700);
        middleLift.setVelocity(1500);

        while (leftLift.isBusy() && rightLift.isBusy() && middleLift.isBusy()) {
            sleep(50);
        }
    }

    public void raiseLiftmiddle() {
        rightLift.setTargetPosition(1700);
        leftLift.setTargetPosition(1700);
        middleLift.setTargetPosition(-2275);

        rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightLift.setVelocity(2700);
        leftLift.setVelocity(2700);
        middleLift.setVelocity(1500);

        while (leftLift.isBusy() && rightLift.isBusy() && middleLift.isBusy()) {
            sleep(50);
        }
    }

    public void raiseLiftbottom() {
        rightLift.setTargetPosition(900);
        leftLift.setTargetPosition(900);
        middleLift.setTargetPosition(-1975);

        rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        middleLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        rightLift.setVelocity(2700);
        leftLift.setVelocity(2700);
        middleLift.setVelocity(1500);

        while (leftLift.isBusy() && rightLift.isBusy() && middleLift.isBusy()) {
            sleep(50);
        }
    }




    public void boxDrop() {
        box.setPosition(.4);
        sleep(2750);
        box.setPosition(.25);
        sleep(750);
    }


    public void retractLift() {


        middleLift.setTargetPosition(0);
        middleLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        middleLift.setVelocity(3000); // NEED TO MAKE SLOWER BECAUSE IT HITS INTAKE
        sleep(500);

        rightLift.setTargetPosition(0);
        leftLift.setTargetPosition(0);

        rightLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        leftLift.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        rightLift.setVelocity(2100);
        leftLift.setVelocity(2100);

    }

    public void spin() {
        bill.setVelocity(-2400);
        sleep(3500);
        bill.setVelocity(0);
    }





    public void leftCScolorCheck()
    {
        float gain = 80;
        final float[] hsvValues = new float[3];
        leftCS.setGain(gain);

        NormalizedRGBA colors = leftCS.getNormalizedColors();
        Color.colorToHSV(colors.toColor(), hsvValues);
        telemetry.addLine()
                .addData("Red", "%.3f", colors.red)
                .addData("Green", "%.3f", colors.green)
                .addData("Blue", "%.3f", colors.blue);

        if (colors.red >.05 && colors.green > .1 && colors.blue > .2) {

            checker = 1;
            telemetry.addLine("left");
        }
        telemetry.update();
        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.HSVToColor(hsvValues));
            }
        });
    }
    public void rightCScolorCheck()
    {
        float gain = 80;
        final float[] hsvValues = new float[3];
        rightCS.setGain(gain);

        NormalizedRGBA colors = rightCS.getNormalizedColors();
        Color.colorToHSV(colors.toColor(), hsvValues);
        telemetry.addLine()
                .addData("Red", "%.3f", colors.red)
                .addData("Green", "%.3f", colors.green)
                .addData("Blue", "%.3f", colors.blue);
        if (colors.red >.05 && colors.green > .1 && colors.blue > .2) {

            checker = 2;
            telemetry.addLine("right");
        }
        telemetry.update();
        relativeLayout.post(new Runnable() {
            public void run() {
                relativeLayout.setBackgroundColor(Color.HSVToColor(hsvValues));
            }
        });
    }
    public void level()
    {
        if (checker == 1)
        {

            //bottom level
            telemetry.addLine("bottom");
            telemetry.update();

            /*bottom level*/
            telemetry.addLine("bottom");
            telemetry.update();

            /*turn left*/
            drive(-400, -400, 400, 400);
            /*forward slightly*/
            drive(10,-10, 10, 10);
            /*lift up*/
            raiseLiftbottom();
            sleep(1000);
            rightLift.setVelocity(1500);
            leftLift.setVelocity(1500);
            /*drop cube*/
            boxDrop();
            sleep(1000);
            /*lift down*/
            retractLift();
            verticalCS.setPosition(0);
            //backup same amount of drive forward 11 lines before
            drive(-250,-250,-250,-250);
            drive(-200,-200,-200,-200);
            //turn left more
            drive(-350,-350,350,350);
            //strafe left
            driveslow(1700,-1700,-1700,1700);
            //backwards
            drive(-1750,-1750,-1750,-1750);
            //drive slow into warehouse
            driveslow(-300,-300,-300,-300);

        }
        else if (checker == 2)
        {
            //middle level
            telemetry.addLine("middle");
            telemetry.update();
            //turn left
            drive(-400, -400, 400, 400);
            //forward slightly
            drive(0, 0, 0, 0);
            //lift up
            raiseLiftmiddle();
            sleep(1000);
            rightLift.setVelocity(1500);
            leftLift.setVelocity(1500);
            //drop cube
            boxDrop();
            sleep(1000);
            //lift down
            retractLift();
            verticalCS.setPosition(0);
            //backup same amount of drive forward 11 lines before
            drive(-250,-250,-250,-250);
            drive(-200,-200,-200,-200);
            //turn left more
            drive(-350,-350,350,350);
            //strafe left
            driveslow(1700,-1700,-1700,1700);
            //backwards
            drive(-1750,-1750,-1750,-1750);
            //drive slow into warehouse
            driveslow(-300,-300,-300,-300);


        }
        else
        {
            //top level
            telemetry.addLine("top");
            telemetry.update();
            //turn left
            drive(-400, -400, 400, 400);
            //forward slightly
            drive(250, 250, 250, 250);
            //lift up
            raiseLifttop();
            sleep(1000);
            rightLift.setVelocity(1500);
            leftLift.setVelocity(1500);
            //drop cube
            boxDrop();
            sleep(1000);
            //lift down
            retractLift();
            //backup same amount of drive forward 11 lines before
            drive(-250,-250,-250,-250);
            drive(-200,-200,-200,-200);
            //turn left more
            drive(-350,-350,350,350);
            //strafe left
            driveslow(1700,-1700,-1700,1700);
            //backwards
            drive(-1750,-1750,-1750,-1750);
            //drive slow into warehouse
            driveslow(-300,-300,-300,-300);

        }
    }

    public void tape()
    {
        verticalCS.setPosition(0.7);
        sleep(1000);
    }
}