package org.firstinspires.ftc.teamcode;

import static com.pedropathing.api.Paths.*;
import com.pedropathing.api.Paths;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.*;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous(name = "AeschylusBLUE")
public class AutonomAeschylusBLUE extends Robot{
    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(81, 8, 90);
    private final Pose path1 = poseFactory.of(81, 114.2641, 270);
    private final Pose point2Start = poseFactory.of(81, 114.2641, 270);
    private final Pose point2 = poseFactory.of(134.3731, 134.0088, 250);
    private final Pose point2Control1 = poseFactory.of(107.562, 114.9446, 0);
    private final Pose point3Start = poseFactory.of(134.3731, 134.0088, 250);
    private final Pose point3 = poseFactory.of(81.2394, 9, 90);
    private final Pose point3Control1 = poseFactory.of(59.7103, 114.7325, 0);
    private final Pose point3Control2 = poseFactory.of(86.7465, 103.562, 0);
    private final Pose point3Control3 = poseFactory.of(80.6497, 16.6212, 0);
    private final Pose point4Start = poseFactory.of(81.2394, 9, 90);
    private final Pose point4 = poseFactory.of(94.9186, 9.9093, 90);
    private final Pose point4Control1 = poseFactory.of(86.4032, 17.3452, 0);
    private final Pose point5Start = poseFactory.of(94.9186, 9.9093, 90);
    private final Pose point5 = poseFactory.of(81.4022, 8.8221, 90);
    private final Pose point5Control1 = poseFactory.of(86.4184, 18.1975, 0);
    private final Pose point6Start = poseFactory.of(81.4022, 8.8221, 90);
    private final Pose point6 = poseFactory.of(131.6231, 94.6204, 180);
    private final Pose point6Control1 = poseFactory.of(80.6146, 33.0511, 0);
    private final Pose point6Control2 = poseFactory.of(135.1111, 31.4304, 0);
    private final Pose point6Control3 = poseFactory.of(109.2394, 99.9324, 0);
    private final Pose point7Start = poseFactory.of(131.6231, 94.6204, 180);
    private final Pose point7 = poseFactory.of(81.2311, 114.435, 270);
    private final Pose point8Start = poseFactory.of(81.2311, 114.435, 270);
    private final Pose point8 = poseFactory.of(131.9836, 45.7048, 270);
    private final Pose point8Control1 = poseFactory.of(131.0463, 97.0148, 0);


    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.curve(point2Start, point2Control1, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.curve(point3Start, point3Control1, point3Control2, point3Control3, point3).linear(point3Start, point3);
    }

    public Path path4() {
        return Paths.curve(point4Start, point4Control1, point4).linear(point4Start, point4);
    }

    public Path path5() {
        return Paths.curve(point5Start, point5Control1, point5).linear(point5Start, point5);
    }

    public Path path6() {
        return Paths.curve(point6Start, point6Control1, point6Control2, point6Control3, point6).linear(point6Start, point6);
    }

    public Path path7() {
        return Paths.line(point7Start, point7).linear(point7Start, point7);
    }

    public Path path8() {
        return Paths.curve(point8Start, point8Control1, point8).linear(point8Start, point8);
    }

    public Command autoRoutine() {
        return sequential(
                follow(follower, path1()), // 2.9s
                shoot(), // 2s
                follow(follower, path2()), // 2.2s
                intake(), // 1s
                follow(follower, path3()), // 3.6s
                shoot(), // 2s
                follow(follower, path4()), // 1.1s
                intake(), // 1s
                follow(follower, path5()), // 1.2s
                shoot(), // 2s
                follow(follower, path6()), // 2.9s
                intake(), // 1s
                follow(follower, path7()), // 2.1s
                shoot(), // 2s
                follow(follower, path8()) // 2.7s

                // 29.7
        );
    }

    @Override
    public void runOpMode() {
        initRobot();
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        follower.setPose(start);
        follower.update();

        waitForStart();
        schedule(autoRoutine());

        while(opModeIsActive()) {
            follower.update();
            Scheduler.execute();
        }

    }
}
