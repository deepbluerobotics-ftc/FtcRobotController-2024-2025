package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="Autonomous", group="Linear OpMode")

public class AutoRed1 extends LinearOpMode {
    private ElapsedTime runtime;
    private HardwareHandler handler;
    private HardwareMap hardwareMap; //might not be right

    public static DcMotor frontLeftDrive = null;
    public static DcMotor backLeftDrive = null;
    public static DcMotor frontRightDrive = null;
    public static DcMotor backRightDrive = null;
    public static DcMotor intake = null;
    public static DcMotor horizontalArm = null;
    public static DcMotor verticalArm = null;

    public static Servo intakeWheel = null;
    public static Servo platform = null;

    @Override
    public void runOpMode() throws InterruptedException{
        runtime = new ElapsedTime();
        handler = new HardwareHandler(hardwareMap);


        frontLeftDrive  = hardwareMap.get(DcMotor.class, "front_left_drive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        backLeftDrive  = hardwareMap.get(DcMotor.class, "back_left_drive");
        backRightDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        intake = hardwareMap.get(DcMotor.class, "intake");
        verticalArm = hardwareMap.get(DcMotor.class, "horizontal_arm");
        horizontalArm = hardwareMap.get(DcMotor.class, "vertical_arm");
        intakeWheel = hardwareMap.get(Servo.class, "intake_wheel");
        platform = hardwareMap.get(Servo.class, "platform");

        handler.resetTargetPositions();
        handler.setAutoRunModes();
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();



    }


}
