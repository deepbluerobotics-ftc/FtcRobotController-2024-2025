package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.external.samples.SampleRevBlinkinLedDriver;

@TeleOp(name="Tele", group="Iterative OpMode")
public class Tele extends OpMode {
    private ElapsedTime runtime;
    private HardwareHandler handler;

    private DcMotor frontLeftDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor intake = null;
    private Servo intakeWheel = null;
    private DcMotor verticalArm = null;
    private DcMotor horizontalArm = null;
    private Servo platform = null;
    private double frontLeftPower = 0;
    private double frontRightPower = 0;
    private double backLeftPower = 0;
    private double backRightPower = 0;
    private double intakePower = 0;
    private double intakeWheelPosition = 0;
    private double horizontalArmPower = 0;
    private double verticalArmPower = 0;
    private double platformPosition = 0;


    private boolean intakeRotating = false;

    private boolean verticalArmUp = false;
    private boolean horizontalArmup = false;

    private boolean intakeState = false;

    private boolean armState = false;

    @Override
    public void init() {
        runtime = new ElapsedTime();
        handler = new HardwareHandler(hardwareMap);

        frontLeftDrive = handler.frontLeftDrive;
        backLeftDrive = handler.backLeftDrive;
        frontRightDrive = handler.frontRightDrive;
        backRightDrive = handler.backRightDrive;
        intake = handler.intake;
        horizontalArm = handler.horizontalArm;
        verticalArm = handler.verticalArm;

        handler.resetRunModes();

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void start() {
        runtime.reset();
    }

    @Override
    public void loop() {
        double max = 0;
        double axial = 0;
        double lateral = 0;
        double yaw = 0;

        axial = -gamepad1.left_stick_y;
        lateral = gamepad1.left_stick_x;
        yaw = gamepad1.right_stick_x;

        frontLeftPower = axial + lateral + yaw;
        frontRightPower = axial - lateral - yaw;
        backLeftPower = axial - lateral + yaw;
        backRightPower = axial + lateral - yaw;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));

        if (max > 0.7) {
            frontLeftPower   /= max;
            frontRightPower /= max;
            backLeftPower /= max;
            backRightPower /= max;
        }

        //Intake Motor logic
        if (gamepad1.dpad_left) {
            intakePower = -0.7;
        } else if (gamepad1.dpad_right) {
            intakePower = 0.7;
        }else {
            intakePower = 0;
        }

        //intake Wheel Logic
        if (gamepad1.y){
            intakeWheelPosition = 1;
        } else if(gamepad1.x){
            intakeWheelPosition = -1;
        } else {
            intakeWheelPosition = 0;
        }
        //platform logic
        if (gamepad1.a) {
            platformPosition = 300;
        } else {
            platformPosition = 0;
        }

        // vert Arm logic
        if (gamepad1.right_bumper){
            verticalArmPower = gamepad1.right_trigger;
        } else{
            verticalArmPower = -gamepad1.right_trigger;
        }
        //horizontal arm Logic
        if (gamepad1.left_bumper){
            horizontalArmPower = gamepad1.left_trigger;
        } else {
            horizontalArmPower = -gamepad1.left_trigger;
        }




        //Set powers and positions
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
        frontLeftDrive.setPower(frontLeftPower);
        frontRightDrive.setPower(frontRightPower);
        intake.setPower(intakePower);
        verticalArm.setPower(horizontalArmPower);
        horizontalArm.setPower(verticalArmPower);

        intakeWheel.setPosition(intakeWheelPosition);
        platform.setPosition(platformPosition);
        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Front Left/Right", "%4.2f, %4.2f", frontLeftPower, frontRightPower);
        telemetry.addData("Back  Left/Right", "%4.2f, %4.2f", backLeftPower, backRightPower);
        telemetry.addData("Arm/Intake", "%4.2f, %4.2f", verticalArmPower, horizontalArmPower, intakePower);
        telemetry.update();
    }
}
