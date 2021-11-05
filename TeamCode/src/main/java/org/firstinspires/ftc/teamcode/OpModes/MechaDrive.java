package org.firstinspires.ftc.teamcode.OpModes;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name = "MechaDrive")
public class MechaDrive extends OpMode {
    private DcMotor rightFront;
    private DcMotor rightRear;
    private DcMotor leftFront;
    private DcMotor leftRear;
    private DcMotor lift;
    private DcMotor backIntake;
    private DcMotor frontIntake;
    private Servo turnTable;
    private Servo trapDoor;
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
        backIntake = hardwareMap.get(DcMotor.class,"backIntake");
        turnTable = hardwareMap.get(Servo.class,"turntable");
        ducky = hardwareMap.get(DcMotor.class, "Duck Spinner");

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
        double rr = -gamepad1.right_stick_y + gamepad1.right_stick_x -gamepad1.left_stick_x;
        double lf = gamepad1.right_stick_y + gamepad1.right_stick_x  -gamepad1.left_stick_x;
        double lr = gamepad1.right_stick_y -gamepad1.right_stick_x -gamepad1.left_stick_x;
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
            turnTable.setPosition(0.5);
                 }
        else {
            turnTable.setPosition(-0.5);
        }
        // Lift ------------------------------------------------------------------------------------
        if(gamepad2.dpad_up){
            lift.setPower(0.5);
        }
        else if(gamepad2.dpad_down){
            lift.setPower(-0.1);
        }
        else {
            lift.setPower(0.15);
        }

        //Intake-----------------------------------------------------------------------------------
        if(gamepad2.right_bumper){
          frontIntake.setPower(1.0);
          backIntake.setPower(-1.0);
        }
        else if(gamepad2.left_bumper){
            frontIntake.setPower(-1.0);
            backIntake.setPower(1.0);
        } else {
            frontIntake.setPower(0.0);
            backIntake.setPower(0.0);
        }
       // Box----------------------------------------------------------------------------------------
       if (gamepad2.a){
            trapDoor.setPosition(0.25);
           }
       else {
            trapDoor.setPosition(-0.25);
       }
       // Duck Spinner-------------------------------------------------------------------------------

        if(gamepad1.right_bumper){
            ducky.setPower(0.33);
        } else if(gamepad1.left_bumper){
            ducky.setPower(-0.33);
        } else {
            ducky.setPower(0.0);
        }

    }

    //hello









    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
