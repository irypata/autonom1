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

@Autonomous(name = "AutonomDeuteragonistBLUE")
public class AutonomDeuteragonistBLUE extends Robot {
    private Follower follower;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(86, 14, 90);
    private final Pose path1 = poseFactory.of(94.3845, 7.0417, 90);
    private final Pose path1Control1 = poseFactory.of(97.8638, 18.0492, 0);
    private final Pose point2Start = poseFactory.of(94.3845, 7.0417, 90);
    private final Pose point2 = poseFactory.of(86.1016, 13.874, 90);
    private final Pose point2Control1 = poseFactory.of(97.8799, 18.0147, 0);
    private final Pose point3Start = poseFactory.of(86.1016, 13.874, 90);
    private final Pose point3 = poseFactory.of(132.6308, 25.1606, 90);

    public Path path1() {
        return Paths.curve(start, path1Control1, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.curve(point2Start, point2Control1, point2).linear(point2Start, point2);
    }

    public Path path3() {
        return Paths.line(point3Start, point3).linear(point3Start, point3);
    }

    @Override
    public void runOpMode() {
        initRobot();
        follower.setPose(start);
        waitForStart();

        while(opModeIsActive()) {
            // 1. asteapta pana la tip
            sleep(7000);
            // 2. tragere // 2s
            shoot();
            // 3. move la flower // 1s
            follow(follower, path1());
            // 4. intake bile flower // 1s
            intake();
            // 5. move la tragere //  1s
            follow(follower, path2());
            // 6. tragere // 2s
            shoot();

            // ~7s + ~7s = 14s aici

            // 7. park ca altceva nu are ce sa faca
            follow(follower, path3());
        }
    }

}
