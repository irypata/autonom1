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

@Autonomous(name = "ThespianBLUE")
public class AutonomThespianBLUE extends Robot {
    private Follower follower;

    private final PoseFactory poseFactory = PoseFactory.degrees().mirrorX(70.75).mirrorY(70.75);
    private final Pose start = poseFactory.of(56, 8, 90);
    private final Pose path1 = poseFactory.of(4.9942, 4.5239, 50);
    private final Pose path1Control1 = poseFactory.of(30.8074, 12.5149, 0);
    private final Pose point2Start = poseFactory.of(4.9942, 4.5239, 50);
    private final Pose point2 = poseFactory.of(57.8679, 124.2703, 270);
    private final Pose point2Control1 = poseFactory.of(45.226, 27.8229, 0);
    private final Pose point2Control2 = poseFactory.of(0, 97.4364, 0);
    private final Pose point3Start = poseFactory.of(57.8679, 124.2703, 270);
    private final Pose point3 = poseFactory.of(47.1539, 132.7987, 270);
    private final Pose point3Control1 = poseFactory.of(45.8444, 123.0964, 0);
    private final Pose point4Start = poseFactory.of(47.1539, 132.7987, 270);
    private final Pose point4 = poseFactory.of(57.7479, 124.2304, 270);
    private final Pose point4Control1 = poseFactory.of(45.7556, 123.1551, 0);
    private final Pose point5Start = poseFactory.of(57.7479, 124.2304, 270);
    private final Pose point5 = poseFactory.of(8.3555, 46.5212, 0);
    private final Pose point5Control1 = poseFactory.of(13.6625, 102.5017, 0);
    private final Pose point5Control2 = poseFactory.of(38.0615, 40.498, 0);
    private final Pose point6Start = poseFactory.of(8.3555, 46.5212, 0);
    private final Pose point6 = poseFactory.of(56.4117, 8.2043, 90);
    private final Pose point6Control1 = poseFactory.of(36.6141, 46.8947, 0);
    private final Pose point7Start = poseFactory.of(56.4117, 8.2043, 90);
    private final Pose point7 = poseFactory.of(9.24, 98.0306, 0);
    private final Pose point7Control1 = poseFactory.of(9.2278, 70.1487, 0);


    public Path path1() {
        return Paths.curve(start, path1Control1, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.curve(point2Start, point2Control1, point2Control2, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.curve(point3Start, point3Control1, point3).linear(point3Start, point3);
    }

    public Path path4() {
        return Paths.curve(point4Start, point4Control1, point4).linear(point4Start, point4);
    }

    public Path path5() {
        return Paths.curve(point5Start, point5Control1, point5Control2, point5).linear(point5Start, point5);
    }

    public Path path6() {
        return Paths.curve(point6Start, point6Control1, point6).linear(point6Start, point6);
    }

    public Path path7() {
        return Paths.curve(point7Start, point7Control1, point7).linear(point7Start, point7);
    }

    public Command autoRoutine() {
        return sequential(
                shoot(),
                follow(follower, path1()),
                intake(),
                follow(follower, path2()),
                shoot(),
                follow(follower, path3()),
                intake(),
                follow(follower, path4()),
                shoot(),
                follow(follower, path5()),
                intake(),
                follow(follower, path6()),
                shoot(),
                follow(follower, path7())
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

        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();
        }
    }
}
