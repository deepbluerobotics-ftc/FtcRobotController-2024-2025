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
        intake.setDirection(DcMotorSimple.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {

            double intakePower = 0;
            if (gamepad1.dpad_right){
                intakePower = 0.4;
            }else if (gamepad1.dpad_left) {
                intakePower = -0.4;
            }else {
                intakePower = 0;
            }
            if (gamepad1.dpad_up){
                intakeWheel.setPosition(0.1);
            } else if (gamepad1.dpad_down) {
                intakeWheel.setPosition(0.9);
            }else{
                intakeWheel.setPosition(0.5); //number that makes it stop
            }

            intake.setPower(intakePower);
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("intake",  intakePower);

            telemetry.update();
        }
    }}
