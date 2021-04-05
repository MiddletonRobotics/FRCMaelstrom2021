package org.firstinspires.ftc.teamcode.Mako.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Mako.Robot.Mako;

import MasqVision.RingDetector;
import MidnightLibrary.MidnightMath.MidnightWayPoint;
import MidnightLibrary.MidnightResources.MidnightLinearOpMode;

import static MasqVision.RingDetector.TargetZone.A;
import static MasqVision.RingDetector.TargetZone.B;
import static MasqVision.RingDetector.TargetZone.C;
import static MidnightLibrary.MidnightMath.MidnightWayPoint.PointMode.SWITCH;
import static MidnightLibrary.MidnightMath.MidnightWayPoint.PointMode.TANK;
import static MidnightLibrary.MidnightRobot.OpMode.AUTO;

/**
 * Created by Amogh Mehta
 * Project: FtcRobotController_Ultimate-Goal_prod2
 * Last Modified: 3/26/21 1:24 AM
 * Last Updated: 3/26/21 1:24 AM
 **/
@Autonomous(name="Red", group = "MakoAuto")
public class MakoAuto1 extends MidnightLinearOpMode {
    private Mako robot = new Mako();
    private RingDetector.TargetZone zone;
    int iterations;
    private MidnightWayPoint target = new MidnightWayPoint().setTimeout(5).setSwitchMode(SWITCH).setTargetRadius(5).setAngularCorrectionSpeed(0.004).setPointSwitchRadius(24).setName("Drop Zone"),
            strafe = new MidnightWayPoint(-5,-30,0).setSwitchMode(TANK).setAngularCorrectionSpeed(0.002);
            //stack = new MidnightWayPoint(4, 30, 0).setSwitchMode(TANK).setName("Starter Stack");

    @Override
    public void runLinearOpMode() {
        robot.init(hardwareMap, AUTO);
        RingDetector detector = (RingDetector) robot.cameraView.detector;

        while (!opModeIsActive()) {
            zone = detector.findZone();

            dash.create("Zone:", zone);
            dash.create("Control:", detector.getControl());
            dash.create("Top:", detector.getTop());
            dash.create("Bottom:", detector.getBottom());
            dash.update();

            if (isStopRequested()) {
                robot.cameraView.stop();
                break;
            }
        }

        waitForStart();

            //robot.driveTrain.setPower(0.5);
            timeoutClock.reset();
            robot.cameraView.stop();
            robot.tracker.reset();

            /* Here we are setting the zone target coordinates*/
            if (zone == B) {
                iterations = 1;
                target.setPoint(-4, -85, 0);
            } else if (zone == C) {
                iterations = 3;
                target.setPoint(-8, -110, 42);
            } else {
                target.setPoint(-7, -62, 50);
            }

        /*
        if (zone!= A) {
            robot.xyPath(target);
            robot.turnAbsolute(target.getH(), 1);//Chance we may not need this
        }
         */

            //robot.xyPath();

            //robot.xyPath(new MidnightWayPoint(0, 0, 0).setTimeout(5).setDriveCorrectionSpeed(0.008).setAngularCorrectionSpeed(0.07));

            //MidnightWayPoint park = new MidnightWayPoint(0, 42, robot.tracker.getHeading()).setName("Park");

            robot.driveTrain.setPower(0.4);
            sleep(3100);
            robot.driveTrain.setPower(0);
            //robot.xyPath(park);

    }
}