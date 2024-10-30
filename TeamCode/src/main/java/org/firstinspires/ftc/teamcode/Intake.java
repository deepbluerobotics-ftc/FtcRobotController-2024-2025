package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Intake", group="Linear OpMode")

public class Intake extends LinearOpMode {
    private Servo intakeWheel = null;
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor intake = null;

    @Override
    public void runOpMode() {
        intake = hardwareMap.get(DcMotor.class, "intake");
        intakeWheel = hardwareMap.get(Servo.class, "intake_wheel");
        intakeWheel.setDirection(Servo.Direction.REVERSE);
        intakeWheel.setPosition(0);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double intakePower = 0;
            if (gamepad1.dpad_right){
                intakePower = 0.7;
            }else if (gamepad1.dpad_left) {
                intakePower = -0.7;
            }else {
                intakePower = 0;
            }

            intake.setPower(intakePower);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("intake", "%4.2f, %4.2f", intakePower);

            telemetry.update();
        }
    }}
