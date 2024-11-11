package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareHandler {
    public static DcMotor frontLeftDrive = null;
    public static DcMotor backLeftDrive = null;
    public static DcMotor frontRightDrive = null;
    public static DcMotor backRightDrive = null;
    public static DcMotor intake = null;
    public static DcMotor horizontalArm = null;
    public static DcMotor verticalArm = null;

    public static Servo intakeWheel = null;
    public static Servo platform = null;

    public HardwareMap hardwareMap;

    public HardwareHandler(HardwareMap hm)
    {
        hardwareMap = hm;
        frontLeftDrive  = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backLeftDrive  = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        intake = hm.get(DcMotor.class, "intake");
        verticalArm = hm.get(DcMotor.class, "horizontal_arm");
        horizontalArm = hm.get(DcMotor.class, "vertical_arm");
        intakeWheel = hm.get(Servo.class, "intake_wheel");
        platform = hm.get(Servo.class, "platform");

        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        verticalArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        horizontalArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        verticalArm.setDirection(DcMotor.Direction.REVERSE);
        horizontalArm.setDirection(DcMotor.Direction.REVERSE);
        intake.setDirection(DcMotor.Direction.FORWARD);
        intakeWheel.setDirection(Servo.Direction.REVERSE);
        platform.setDirection(Servo.Direction.REVERSE);

        resetPower();
        resetRunModes();
    }
    public static void resetPower()
    {
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        verticalArm.setPower(0);
        horizontalArm.setPower(0);
        intake.setPower(0);

        intakeWheel.setPosition(0.5);
        platform.setPosition(0.5);
    }
    public static void setPower(double frontLeft, double frontRight, double backLeft, double backRight,
                                double intakePower, double verticalArmPower, double horizontalArmPower,
                                double intakeWheelPosition, double platformPosition)
    {
        backLeftDrive.setPower(backLeft);
        backRightDrive.setPower(backRight);
        frontLeftDrive.setPower(frontLeft);
        frontRightDrive.setPower(frontRight);
        intake.setPower(intakePower);
        verticalArm.setPower(horizontalArmPower);
        horizontalArm.setPower(verticalArmPower);

        intakeWheel.setPosition(intakeWheelPosition);
        platform.setPosition(platformPosition);
    }
    public static void resetRunModes() {
        backLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontLeftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public static void resetTargetPositions() {
        backLeftDrive.setTargetPosition(0);
        backRightDrive.setTargetPosition(0);
        frontLeftDrive.setTargetPosition(0);
        frontRightDrive.setTargetPosition(0);
        verticalArm.setTargetPosition(0);
        horizontalArm.setTargetPosition(0);
        intake.setTargetPosition(0);
    }
    public static void setAutoRunModes() {
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        horizontalArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        backLeftDrive.setPower(1);
        backRightDrive.setPower(1);
        frontLeftDrive.setPower(1);
        frontRightDrive.setPower(1);
        verticalArm.setPower(1);
        horizontalArm.setPower(1);
        intake.setPower(0);
    }
    public static void setAutoRunModesSimple() {
        backLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontLeftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        verticalArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        horizontalArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public static void setDrivePower(double leftFront, double leftBack, double rightFront, double rightBack) {
        frontLeftDrive.setPower(leftFront);
        frontRightDrive.setPower(rightFront);
        backRightDrive.setPower(rightBack);
        backLeftDrive.setPower(leftBack);
    }

    public static void inchesForward(double distance) {
        int counts = (int) (distance * 1440 / 3.77953);
        frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + counts);
        frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + counts);
        backLeftDrive.setTargetPosition(backLeftDrive.getCurrentPosition() + counts);
        backRightDrive.setTargetPosition(backRightDrive.getCurrentPosition() + counts);
    }

    public static void inchesRotate(double distance) {
        int counts = (int) (distance * 1440 / 3.77953);
        frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + counts);
        frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() + counts);
        backLeftDrive.setTargetPosition(backLeftDrive.getCurrentPosition() - counts);
        backRightDrive.setTargetPosition(backRightDrive.getCurrentPosition() - counts);
    }

    public static void inchesSideways(double distance) {
        int counts = (int) (distance * 1440 / 3.77953);
        frontLeftDrive.setTargetPosition(frontLeftDrive.getCurrentPosition() + counts);
        frontRightDrive.setTargetPosition(frontRightDrive.getCurrentPosition() - counts);
        backLeftDrive.setTargetPosition(backLeftDrive.getCurrentPosition() - counts);
        backRightDrive.setTargetPosition(backRightDrive.getCurrentPosition() + counts);
    }
}
