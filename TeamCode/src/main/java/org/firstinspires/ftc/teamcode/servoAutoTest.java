package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoServoTest", group="Linear OpMode")

public class servoAutoTest extends LinearOpMode{
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


        platform.setPosition(0);
        sleep(5000);
        
    }
}
