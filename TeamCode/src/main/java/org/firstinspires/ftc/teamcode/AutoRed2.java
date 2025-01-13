package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutonomousRed2", group="Linear OpMode")
public class AutoRed2 extends LinearOpMode {

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

        // Step 1: Move forward 53 inches
        moveForward(24); // Move 53 inches <-works moves far

        // Step 2: Turn left 43.82 degrees
        //turnLeft(43.82*1.5); // Turn left 43.82 degrees <- not

        //Step 3 move arm up
        //moveArm(38.75);
        //Step 4 Rotate platform
        //platform.setPosition(1);
        //setPlatformServo(70); //doesn't work
        //Step 5 Move arm down
        //moveArm(-38.75);
        //Step 7 move backwards
        //moveForward(-66.5);
        //moveArm(2);
    }

    // Move forward by a given distance in inches
    public void moveForward(double inches) {
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

        frontLeftDrive.setPower(1.0);
        frontRightDrive.setPower(1.0);
        backLeftDrive.setPower(1.0);
        backRightDrive.setPower(1.0);

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
    public void moveArm(double inches) {
        double distanceInMeters = inches;
        double WHEEL_CIRCUMFERENCE = 11.780972451;
        double rotations = distanceInMeters / WHEEL_CIRCUMFERENCE;
        int targetTicks = (int)(rotations * 1120); // 1120 ticks per full rotation

        // Reset and set target positions for each motor
        verticalArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        verticalArm.setTargetPosition(targetTicks);

        verticalArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        verticalArm.setPower(1.0);

        while (opModeIsActive() && verticalArm.isBusy()) {
            telemetry.addData("Moving Arm", "Distance: %2.5f", inches);
            telemetry.update();
        }

        // Stop all motors
        verticalArm.setPower(0);
    }
    // Turn the robot left by a given number of degrees
    public void turnLeft(double degrees) {
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

        frontLeftDrive.setPower(1.0);
        frontRightDrive.setPower(1.0);
        backLeftDrive.setPower(1.0);
        backRightDrive.setPower(1.0);

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
