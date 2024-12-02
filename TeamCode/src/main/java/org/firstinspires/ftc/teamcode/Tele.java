/* Copyright (c) 2021 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="Tele", group="Linear OpMode")

public class Tele extends LinearOpMode {

    // Declare OpMode members for each of the 4 motors.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
    private DcMotor horizontalArm = null;
    private DcMotor verticalArm = null;
    private DcMotor intake = null;

    private Servo intakeWheel = null;
    private Servo platform = null;

    private double horizontalArmPower = 0;
    private double verticalArmPower = 0;
    private double intakePower = 0;

    private int intakePos = 0;
    private int verticalArmPos = 0;
    private int horizontalArmPos = 0;

    @Override
    public void runOpMode() {

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "front_left_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "back_left_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "front_right_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "back_right_drive");
        verticalArm = hardwareMap.get(DcMotor.class, "horizontal_arm");
        horizontalArm = hardwareMap.get(DcMotor.class, "vertical_arm");
        intake = hardwareMap.get(DcMotor.class, "intake");
        intakeWheel = hardwareMap.get(Servo.class, "intake_wheel");
        platform = hardwareMap.get(Servo.class, "platform");

        intakeWheel.setDirection(Servo.Direction.REVERSE);
        intake.setDirection(DcMotorSimple.Direction.FORWARD);
        verticalArm.setDirection(DcMotor.Direction.REVERSE);
        horizontalArm.setDirection(DcMotor.Direction.REVERSE);
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);



        platform.setPosition(0);
        
        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            double max;

            // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
            double axial   = -gamepad1.right_stick_y;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.right_stick_x;
            double yaw     =  gamepad1.left_stick_x;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower  = (axial + lateral + yaw)/ 3;
            double rightFrontPower = (axial - lateral - yaw) /3;
            double leftBackPower   = (axial - lateral + yaw) /3;
            double rightBackPower  = (axial + lateral - yaw) /3;

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1) { 
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
            }

            //intake motor stuff
            //Rotates normally
            intakePower = (gamepad1.left_trigger - gamepad1.right_trigger)/3;
            /* No idea if works was an attempt for limits, but rotation?
            if (Math.abs(intakePower)>0.1){
                intakePos += (int)(intakePower);
                intakePos = Math.max(intakePos, 360);
                intakePos = Math.min(intakePos, 10);
                intake.setPower(intakePower);
            }
            */
            intake.setPower(intakePower);

            /*
             //Moves intake motor to be 90?
            if (gamepad1.y){
                intake.setTargetPosition((int)(90*7.5)); //-> needs an encoder to test
                intake.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                intake.setPower(0.3);

            }

             */


            //bucket = platform
            //platform stuff
            if(gamepad1.left_bumper){
                platform.setPosition(1.2);
            } else if ( gamepad1.right_bumper) {
                platform.setPosition(0);
            }

            //Veritcal arm stuff
            if (gamepad1.dpad_up){
                verticalArmPower = 1;
            }else if (gamepad1.dpad_down){
                verticalArmPower = -1;
            } else{
                verticalArmPower = 0;
            }

            if (Math.abs(verticalArmPower)>0.1){
                verticalArmPos += (int)(verticalArmPower);
                verticalArmPos = Math.min(intakePos, 2700);
                verticalArmPos = Math.min(intakePos, 0);
                verticalArm.setTargetPosition((int)(verticalArmPos*7.5));
                verticalArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                verticalArm.setPower(verticalArmPower);
            }

            //Horizontal Arm stuff
            if (gamepad1.dpad_right){
                horizontalArmPower = -1;
            }else if (gamepad1.dpad_left){
                horizontalArmPower = 1;
            }else {
                horizontalArmPower = 0;
            }

            horizontalArm.setPower(horizontalArmPower);

            //horizontalArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            
            
            //intake "wheel"
            if(gamepad1.b){
                intakeWheel.setPosition(0.9);
            } else if (gamepad1.x) {
                intakeWheel.setPosition(0.1);
            }else {
                intakeWheel.setPosition(0.5);
            }

            // Send calculated power to wheels
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);
            // Show the elapsed game time and powers/positions.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.addData("Vertical Arm/Horizontal Arm", "%4.2f, %4.2f", verticalArmPower, horizontalArmPower);
            telemetry.addData("intake",  intakePower);
            telemetry.addData("platform", platform.getPosition());
            telemetry.addData("Intake wheel", intakeWheel.getPosition());
            telemetry.update();
        }
    }}
