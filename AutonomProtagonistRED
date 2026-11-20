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

@Autonomous(name = "AutonomProtagonistRED")
public class AutonomProtagonistRED extends Robot {
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(56, 14, 90);
    private final Pose path1 = poseFactory.of(3.7931, 2.9854, 0);
    private final Pose path1Control1 = poseFactory.of(20.7074, 21.9101, 0);
    private final Pose path1Control2 = poseFactory.of(19.4547, 2.6011, 0);
    private final Pose point2Start = poseFactory.of(3.7931, 2.9854, 0);
    private final Pose point2 = poseFactory.of(55.3907, 14.1374, 90);
    private final Pose point2Control1 = poseFactory.of(38.6963, 20.1864, 0);
    private final Pose point3Start = poseFactory.of(55.3907, 14.1374, 90);
    private final Pose point3 = poseFactory.of(10.0521, 46.8306, 0);
    private final Pose point3Control1 = poseFactory.of(48.878, 47.0031, 0);
    private final Pose point4Start = poseFactory.of(10.0521, 46.8306, 0);
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

    @Override
    public void runOpMode() {
        initRobot();
        follower.setPose(start);
        waitForStart();

        while(opModeIsActive()) {
            // 1. tragere (=> tip) // 2s
            shoot();
            // 2. move la side // 2s
            follow(follower, path1());
            // 3. intake bile side // 1s
            intake();
            // 4. move la tragere // 2s
            follow(follower, path2());

            // ~7 aici

            // 5. asteapta pana la tip
            sleep(7000);

            // ~14s aici

            // 6. tragere // 2s
            shoot();
            // 7. move la flower // 2s
            follow(follower, path3());
            // 8. intake bile flower // 1s
            intake();
            // 9. move la tragere // 2s
            follow(follower, path4());
            // 10. tragere (=> tip) // 2s
            shoot();

            // ~14s + ~9s = 23s aici

            // park // ~3s
            follow(follower, path5());

            // total = 26-27s
            // la aproape toate am aproximat prin scadere, deci probabil e mai mult, dar asa e safe
        }
    }


}
