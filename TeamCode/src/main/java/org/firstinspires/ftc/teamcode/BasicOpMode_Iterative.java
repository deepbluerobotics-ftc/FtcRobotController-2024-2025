package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="ServoTest", group="Linear OpMode")

public class BasicOpMode_Iterative extends LinearOpMode {
    private ElapsedTime runtime;

    public static Servo platform = null;

    @Override
    public void runOpMode() {
        runtime = new ElapsedTime();

        platform     = hardwareMap.get(Servo.class, "platform");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            platform.setPosition(gamepad1.left_trigger);
            telemetry.addData("platform", platform.getPosition());

            telemetry.update();
        }

    }
}