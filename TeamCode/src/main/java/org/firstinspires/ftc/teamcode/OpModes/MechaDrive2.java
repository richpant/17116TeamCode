package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
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
    private CRServo frontTurnTable;
    private CRServo backTurnTable;
    private CRServo box;
    private DcMotor ducky;


    /*private DcMotor liftMotor;
    public static final double MID_SERVO       =  0.5 ;
    public static final double LIFT_UP_POWER    =  0.45 ;
    public static final double LIFT_DOWN_POWER  = -0.45 ;
    double          clawOffset  = 0.0 ;                  // Servo mid position
    final double    CLAW_SPEED  = 0.02 ;                 // sets rate to move servo*/
    @Override
    public void init(){
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        lift = hardwareMap.get(DcMotor.class, "lift");
        frontIntake = hardwareMap.get(DcMotor.class, "frontIntake");
        frontTurnTable = hardwareMap.get(CRServo.class,"frontTurnTable");
        backTurnTable = hardwareMap.get(CRServo.class, "backTurnTable");
        box = hardwareMap.get(CRServo.class, "box");
        ducky = hardwareMap.get(DcMotor.class, "ducky");


        //liftMotor = hardwareMap.get(DcMotor.class,"liftMotor");
       // rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
       // rightRear.setDirection(DcMotorSimple.Direction.REVERSE);
        int pos = 0;
        int pos2 = 0;

        telemetry.addData("Say", "Hello Driver");
    }
    @Override
    public void loop() {
        double left;
        double right;
        double rf = -gamepad1.right_stick_y -gamepad1.right_stick_x -gamepad1.left_stick_x;
        double rr = (-gamepad1.right_stick_y + gamepad1.right_stick_x -gamepad1.left_stick_x)*-1;
        double lf = gamepad1.right_stick_y + gamepad1.right_stick_x  -gamepad1.left_stick_x;
        double lr = (gamepad1.right_stick_y -gamepad1.right_stick_x -gamepad1.left_stick_x)*-1;
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
        }
        //Turn Table--------------------------------------------------------------------------------
        if(gamepad2.dpad_right){
            frontTurnTable.setPower(1.0);
            backTurnTable.setPower(-1.0);
                 }
        else if(gamepad2.dpad_left)         // Still Need to Change Values for Turn Tables
        {                                   // Just basic starter Code
            frontTurnTable.setPower(-1.0);
            backTurnTable.setPower(1.0);
        }
        else{
            frontTurnTable.setPower(0);
            backTurnTable.setPower(0);
        }
        // Lift ------------------------------------------------------------------------------------
        if(gamepad2.dpad_up){
            lift.setPower(0.5);
        }
        else if(gamepad2.dpad_down){
            lift.setPower(-0.5);
        }
        else {
            lift.setPower(0.0);
        }

        //Intake-----------------------------------------------------------------------------------
        if(gamepad2.right_bumper){
          frontIntake.setPower(1.0);
        }
        else if(gamepad2.left_bumper){
            frontIntake.setPower(-1.0);
        } else {
            frontIntake.setPower(0.0);
        }
       // Box----------------------------------------------------------------------------------------
       if (gamepad2.a){
            box.setPower(5);
           }
       else {
            box.setPower(-5);
       }
       // Duck Spinner-------------------------------------------------------------------------------

        if(gamepad1.right_bumper){
            ducky.setPower(0.36);
        } else if(gamepad1.left_bumper){
            ducky.setPower(-0.36);
        } else {
            ducky.setPower(0.0);
        }

    }

    @Override
    public void stop() {
    }
}
// he