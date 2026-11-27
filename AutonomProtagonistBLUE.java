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

@Autonomous(name = "ProtagonistBLUE")
public class AutonomProtagonistBLUE extends Robot {
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees().mirrorX(70.75).mirrorY(70.75);
    private final Pose start = poseFactory.of(56, 14, 90);
    private final Pose path1 = poseFactory.of(3.7931, 2.9854, 0);
    private final Pose path1Control1 = poseFactory.of(20.7074, 21.9101, 0);
    private final Pose path1Control2 = poseFactory.of(19.4547, 2.6011, 0);
    private final Pose point2Start = poseFactory.of(3.7931, 2.9854, 0);
    private final Pose point2 = poseFactory.of(55.3907, 14.1374, 90);
    private final Pose point2Control1 = poseFactory.of(38.6963, 20.1864, 0);
    private final Pose point3Start = poseFactory.of(55.3907, 14.1374, 90);
    private final Pose point3 = poseFactory.of(9.0117, 46.8306, 0);
    private final Pose point3Control1 = poseFactory.of(48.878, 47.0031, 0);
    private final Pose point4Start = poseFactory.of(9.0117, 46.8306, 0);
    private final Pose point4 = poseFactory.of(55.6151, 14.4185, 90);
    private final Pose point4Control1 = poseFactory.of(49.643, 45.952, 0);
    private final Pose point5Start = poseFactory.of(55.6151, 14.4185, 0);
    private final Pose point5 = poseFactory.of(8.8493, 97.8159, 0);
    private final Pose point5Control1 = poseFactory.of(8.8011, 48.8307, 0);

    public Path path1() {
        return Paths.curve(start, path1Control1, path1Control2, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.curve(point2Start, point2Control1, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.curve(point3Start, point3Control1, point3).linear(point3Start, point3);
    }

    public Path path4() {
        return Paths.curve(point4Start, point4Control1, point4).linear(point4Start, point4);
    }

    public Path path5() {
        return Paths.curve(point5Start, point5Control1, point5).linear(point5Start, point5);
    }

    public Command autoRoutine() {
        return sequential(
                shoot(),
                follow(follower, path1()),
                intake(),
                follow(follower, path2()),
                waitMs(7000),
                shoot(),
                follow(follower, path3()),
                intake(),
                follow(follower, path4()),
                shoot(),
                follow(follower, path5())
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
