package MidnightLibrary.MidnightAuxiliary;

import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This is a telemetry wrapper class.
 * It provides additional functionality such as stickied messages.
 * It supports multiple types of inputs, including MidnightHardware objects.
 */

public class MidnightDashBoard {
    private static MidnightDashBoard instance;
    private int dashLength;
    private final Telemetry telemetry;
    private boolean open;

    public MidnightDashBoard(Telemetry telemetry) {
        this.telemetry = telemetry;
        instance = this;
    }

    public static MidnightDashBoard getDash() {
        return instance;
    }

    public void create(String string) {
        telemetry.addLine(string);
    }

    public void create(Object data) {
        telemetry.addLine(data.toString());
    }

    public void create(String string, Object data) {
        telemetry.addData(string, data);
    }

    public void create(Object... data) {
        for (Object dash : data) {
            telemetry.addLine(dash.toString());
        }
    }

    public void create(final MidnightHardware hardware) {
        dashLength = hardware.getDash().length;
        for (int i = 0; i < dashLength; i++) {
            create(hardware.getName(), hardware.getDash()[i]);
        }
    }

    public void create(MidnightHardware... hardwares) {
        for (MidnightHardware hardware : hardwares) {
            create(hardware);
        }
    }

    public void create(final MidnightSubSystem subSystem) {
        for (MidnightHardware hardware : subSystem.getComponents()) {
            create((Object) hardware.getDash());
        }
    }

    public void createSticky(String string) {
        telemetry.log().add(string);
        update();
    }

    public void createSticky(Object data) {
        telemetry.log().add(data.toString());
        update();
    }

    public void createSticky(String string, Object data) {
        telemetry.log().add(string, data);
        update();
    }

    public void createSticky(final MidnightHardware hardware) {
        dashLength = hardware.getDash().length;
        for (int i = 0; i < dashLength; i++) {
            telemetry.log().add(hardware.getName(), hardware.getDash()[i]);
        }
        update();
    }

    public void createSticky(final MidnightSubSystem subSystem) {
        for (MidnightHardware hardware : subSystem.getComponents()) {
            createSticky(hardware.getDash());
        }
    }

    public void log(String string) {
        RobotLog.i(string);
    }

    public void log(Object data) {
        RobotLog.i(data.toString());
    }

    public void log(String string, Object data) {
        RobotLog.i(string, data);
    }

    public void log(final MidnightHardware hardware) {
        dashLength = hardware.getDash().length;
        for (int i = 0; i < dashLength; i++) {
            RobotLog.i(hardware.getName(), hardware.getDash()[i]);
        }
    }

    public void setNewFirst() {
        telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.NEWEST_FIRST);
    }

    public void setNewLast() {
        telemetry.log().setDisplayOrder(Telemetry.Log.DisplayOrder.OLDEST_FIRST);
    }

    public void update() {
        telemetry.update();
    }

    public void clear() {
        telemetry.clearAll();
    }

    public void close() {
        open = false;
    }

    public void open() {
        open = true;
    }

    public void startUpdate() {
        Runnable main = () -> {
            while (MidnightUtils.opModeIsActive() && open) {
                update();
                MidnightUtils.sleep(100);
            }
        };
        Thread t = new Thread(main);
        t.start();
    }
}