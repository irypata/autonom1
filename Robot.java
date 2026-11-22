package org.firstinspires.ftc.teamcode;

import static com.pedropathing.ivy.commands.Commands.waitMs;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class Robot extends LinearOpMode {
    ElapsedTime timer = new ElapsedTime();
    DcMotor mfs, mfd, mss, msd, sh0, sh1, mintake, mtransfer;
    ServoImplEx loader;
    boolean lock;
    public void initRobot() {
        mfs = hardwareMap.get(DcMotor.class,"sf");
        mss = hardwareMap.get(DcMotor.class,"ss");
        mfd = hardwareMap.get(DcMotor.class,"df");
        msd = hardwareMap.get(DcMotor.class,"ds");
        sh0 = hardwareMap.get(DcMotor.class,"sh0");
        sh1 = hardwareMap.get(DcMotor.class,"sh1");
        mintake = hardwareMap.get(DcMotor.class,"intake");
        mtransfer = hardwareMap.get(DcMotor.class,"transfer");
        loader = (ServoImplEx) hardwareMap.get(Servo.class,"loader");
        mintake.setDirection(DcMotorSimple.Direction.REVERSE);
        sh1.setDirection(DcMotorSimple.Direction.REVERSE);
        sh0.setDirection(DcMotorSimple.Direction.FORWARD);
        sh0.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        sh1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        mfd.setDirection(DcMotorSimple.Direction.FORWARD);
        msd.setDirection(DcMotorSimple.Direction.FORWARD);
        mfs.setDirection(DcMotorSimple.Direction.REVERSE);
        mss.setDirection(DcMotorSimple.Direction.REVERSE);
        msd.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mfs.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mfd.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        mss.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        timer.milliseconds();
        timer.reset();
    }
    public void shooterON() {
        sh0.setPower(1);
        sh1.setPower(1);
    }
    public void shooterIDLE() {
        sh0.setPower(0.5);
        sh1.setPower(0.5);
    }
    public void shooterOFF() {
        sh0.setPower(0);
        sh1.setPower(0);
    }
    public void intakeON() {
        transferON();
        mintake.setPower(1);
    }
    public void intakeOFF() {
        transferOFF();
        mintake.setPower(0);
    }
    public void transferON() {
        mtransfer.setPower(1);
    }
    public void transferOFF() {
        mtransfer.setPower(0);
    }
    public Command shoot() {
        shooterON();
        // daca fac ca gen daca puterea la shootere e mai mica decat idle sa ofer mai mult timp?
        waitMs(900);
        loaderON();
        transferON();
        waitMs(1000);
        shooterIDLE();
        transferOFF();
        loaderOFF();
        return null;
    }
    public Command intake() {
        intakeON();
        waitMs(500);
        intakeOFF();
        return null;
    }
    public void loaderON() {
        loader.setPosition(1);
    }
    public void loaderOFF() {
        loader.setPosition(0);
    }
}
