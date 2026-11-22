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
    private final Pose path1 = poseFactory.of(81.3045, 132.1019, 270);
    private final Pose point2Start = poseFactory.of(81.3045, 132.1019, 270);
    private final Pose point2 = poseFactory.of(136.5399, 135.7204, 250);
    private final Pose point2Control1 = poseFactory.of(101.1446, 116.3519, 0);
    private final Pose point3Start = poseFactory.of(136.5399, 135.7204, 250);
    private final Pose point3 = poseFactory.of(81.5928, 9.3428, 90);
    private final Pose point3Control1 = poseFactory.of(86.589, 116.2833, 0);
    private final Pose point3Control2 = poseFactory.of(119.1592, 19.5707, 0);
    private final Pose point3Control3 = poseFactory.of(132.3738, 30.9807, 0);
    private final Pose point3Control4 = poseFactory.of(76.9585, 38.0781, 0);
    private final Pose point4Start = poseFactory.of(81.5928, 9.3428, 90);
    private final Pose point4 = poseFactory.of(94.3641, 9.6147, 90);
    private final Pose point4Control1 = poseFactory.of(87.9995, 21.7415, 0);
    private final Pose point5Start = poseFactory.of(94.3641, 9.6147, 90);
    private final Pose point5 = poseFactory.of(81.7056, 8.4704, 90);
    private final Pose point5Control1 = poseFactory.of(87.1429, 21.8396, 0);
    private final Pose point6Start = poseFactory.of(81.7056, 8.4704, 90);
    private final Pose point6 = poseFactory.of(133.2236, 94.2476, 180);
    private final Pose point6Control1 = poseFactory.of(75.9666, 34.7413, 0);
    private final Pose point6Control2 = poseFactory.of(134.3215, 38.4375, 0);
    private final Pose point6Control3 = poseFactory.of(109.9934, 20.3129, 0);
    private final Pose point6Control4 = poseFactory.of(93.5346, 102.4244, 0);
    private final Pose point7Start = poseFactory.of(133.2236, 94.2476, 180);
    private final Pose point7 = poseFactory.of(81.3603, 131.5973, 270);
    private final Pose point7Control1 = poseFactory.of(86.9198, 89.9832, 0);
    private final Pose point7Control2 = poseFactory.of(127.9049, 135.232, 0);
    private final Pose point8Start = poseFactory.of(81.3603, 131.5973, 270);
    private final Pose point8 = poseFactory.of(132.4493, 44.6771, 180);
    private final Pose point8Control1 = poseFactory.of(107.06, 109.9444, 0);

    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.curve(point2Start, point2Control1, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.curve(point3Start, point3Control1, point3Control2, point3Control3, point3Control4, point3).linear(point3Start, point3);
    }

    public Path path4() {
        return Paths.curve(point4Start, point4Control1, point4).linear(point4Start, point4);
    }

    public Path path5() {
        return Paths.curve(point5Start, point5Control1, point5).linear(point5Start, point5);
    }

    public Path path6() {
        return Paths.curve(point6Start, point6Control1, point6Control2, point6Control3, point6Control4, point6).linear(point6Start, point6);
    }

    public Path path7() {
        return Paths.curve(point7Start, point7Control1, point7Control2, point7).linear(point7Start, point7);
    }

    public Path path8() {
        return Paths.curve(point8Start, point8Control1, point8).linear(point8Start, point8);
    }

    public Command autoRoutine() {
        return sequential(
                follow(follower, path1()), // 3s
                shoot(), // 2s
                follow(follower, path2()), // 2s
                intake(), // 1s
                follow(follower, path3()), // ~4s
                shoot(), // 2s
                follow(follower, path4()), // 1s
                intake(), // 1s
                follow(follower, path5()), // 1s
                shoot(), // 2s
                follow(follower, path6()), // 3s
                intake(), // 1s
                follow(follower, path7()), // 3s
                shoot(), // 2s
                follow(follower, path8()) // 3s

                // => ~31s
                // peste 30s :((((((
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
