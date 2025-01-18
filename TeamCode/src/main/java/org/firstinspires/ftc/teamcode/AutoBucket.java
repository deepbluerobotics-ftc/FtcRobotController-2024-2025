package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoBucket", group="Linear OpMode")
public class AutoBucket extends LinearOpMode {

    private ElapsedTime runtime;
    //private HardwareHandler handler;
    //private HardwareMap hardwareMap;

    // Motor definitions
    public static DcMotor frontLeftDrive = null;
    public static DcMotor backLeftDrive = null;
    public static DcMotor frontRightDrive = null;
    public static DcMotor backRightDrive = null;
    public static DcMotor intake = null;
    public static DcMotor horizontalArm = null;
    public static DcMotor verticalArm = null;

    // Servo definitions
    public static Servo intakeWheel = null;
    public static Servo platform = null;

    @Override
    public void runOpMode() {
        runtime = new ElapsedTime();
        //handler = new HardwareHandler(hardwareMap);

        // Hardware mapping
        frontLeftDrive  = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backLeftDrive   = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightDrive  = hardwareMap.get(DcMotor.class, "back_right_drive");
        intake     = hardwareMap.get(DcMotor.class, "intake");
        verticalArm = hardwareMap.get(DcMotor.class, "vertical_arm");
        horizontalArm = hardwareMap.get(DcMotor.class, "horizontal_arm");
        intakeWheel = hardwareMap.get(Servo.class, "intake_wheel");
        platform     = hardwareMap.get(Servo.class, "platform");

        intakeWheel.setDirection(Servo.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        verticalArm.setDirection(DcMotor.Direction.REVERSE);
        horizontalArm.setDirection(DcMotor.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        //handler.resetTargetPositions();
        //handler.setAutoRunModes();
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        //Tilt platform to flat
        platform.setPosition(0.3412);
        sleep(500);
        //Move to bucket
        moveForward(9, 1);
        //rotate
        turnLeft(100,1);
        //Move arm up
        moveArm(39.5,1);
        //Tilt platform
        platform.setPosition(0.1529);
        sleep(500);
        //move backword
        moveForward(-2,1);
        //Move Arm Down
        moveArm(-17, 1);
        //Unrotate
        turnLeft(225,1);
        //untilt
        platform.setPosition(0.3412);
        sleep(500);
        //move forward
        moveForward(-4,1);
        //rotate intake
        rotateIntake(-60,0.25);
        //close claw
        intakeWheel.setPosition(0.7);
        sleep(800);
        //unrotate intake
        rotateIntake(60,1);
        //open claw
        intakeWheel.setPosition(0.1);
        sleep(500);
        //move forward
        moveForward(4,1);
        //turn left
        turnLeft(-125,1);
        //move arm
        moveArm(39.5,1);
        //tilt platform
        platform.setPosition(0.1529);
        sleep(500);
        //move away
        //moveForward(-2,1);
        //move arm down
        //moveArm(-10,1);
        //unturn
        //turnLeft(-100,1);

    }

    // Move forward by a given distance in inches
    public void moveForward(double inches, double speed) {
        double distanceInMeters = inches;
        double WHEEL_CIRCUMFERENCE = 11.780972451;
        double rotations = distanceInMeters / WHEEL_CIRCUMFERENCE;
        int targetTicks = (int)(rotations * 1120); // 1120 ticks per full rotation

        // Reset and set target positions for each motor
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftDrive.setTargetPosition(targetTicks);
        frontRightDrive.setTargetPosition(targetTicks);
        backLeftDrive.setTargetPosition(targetTicks);
        backRightDrive.setTargetPosition(targetTicks);

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);

        while (opModeIsActive() && frontLeftDrive.isBusy() && frontRightDrive.isBusy() && backLeftDrive.isBusy() && backRightDrive.isBusy()) {
            telemetry.addData("Moving Forward", "Distance: %2.5f", inches);
            telemetry.update();
        }

        // Stop all motors
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }
    public void movelEFT(double inches, double speed) {
        double distanceInMeters = inches;
        double WHEEL_CIRCUMFERENCE = 11.780972451;
        double rotations = distanceInMeters / WHEEL_CIRCUMFERENCE;
        int targetTicks = (int)(rotations * 1120); // 1120 ticks per full rotation

        // Reset and set target positions for each motor
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftDrive.setTargetPosition(targetTicks);
        frontRightDrive.setTargetPosition(targetTicks);
        backLeftDrive.setTargetPosition(targetTicks);
        backRightDrive.setTargetPosition(targetTicks);

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftDrive.setPower(-speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(-speed);

        while (opModeIsActive() && frontLeftDrive.isBusy() && frontRightDrive.isBusy() && backLeftDrive.isBusy() && backRightDrive.isBusy()) {
            telemetry.addData("Moving Forward", "Distance: %2.5f", inches);
            telemetry.update();
        }

        // Stop all motors
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }
    public void moveArm(double inches, double speed) {
        double distanceInMeters = inches;
        double WHEEL_CIRCUMFERENCE = 11.780972451;
        double rotations = distanceInMeters / WHEEL_CIRCUMFERENCE;
        int targetTicks = (int)(rotations * 1120); // 1120 ticks per full rotation

        // Reset and set target positions for each motor
        verticalArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        verticalArm.setTargetPosition(targetTicks);

        verticalArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        verticalArm.setPower(speed);

        while (opModeIsActive() && verticalArm.isBusy()) {
            telemetry.addData("Moving Arm", "Distance: %2.5f", inches);
            telemetry.update();
        }

        // Stop all motors
        verticalArm.setPower(0);
    }
    public void rotateIntake(double degrees, double speed) {
        double radians = Math.toRadians(degrees);
        int targetTicks = (int) (radians / (2 * Math.PI) * 1120); // Estimate number of encoder ticks

        // Reset encoders
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        intake.setTargetPosition(targetTicks);

        intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        intake.setPower(speed);

        while (opModeIsActive() && intake.isBusy()) {
            telemetry.addData("Turning Left", "Angle: %2.5f", degrees);
            telemetry.update();
        }

        // Stop all motors after the turn

        intake.setPower(0);
    }
    // Turn the robot left by a given number of degrees
    public void turnLeft(double degrees, double speed) {
        double radians = Math.toRadians(degrees);
        int targetTicks = (int) (radians / (2 * Math.PI) * 1120); // Estimate number of encoder ticks

        // Reset encoders
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeftDrive.setTargetPosition(-targetTicks);
        frontRightDrive.setTargetPosition(targetTicks);
        backLeftDrive.setTargetPosition(-targetTicks);
        backRightDrive.setTargetPosition(targetTicks);

        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);

        while (opModeIsActive() && frontLeftDrive.isBusy() && frontRightDrive.isBusy() && backLeftDrive.isBusy() && backRightDrive.isBusy()) {
            telemetry.addData("Turning Left", "Angle: %2.5f", degrees);
            telemetry.update();
        }

        // Stop all motors after the turn
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }


}
