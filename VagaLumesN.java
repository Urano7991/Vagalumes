package Vaga_lumes;
import robocode.*;
public class Vaga_lumes extends AdvancedRobot {
    public void run() {
        while (true) {
            setAhead(100);
            setTurnGunRight(360);
            execute();
        }
    }
    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }
    public void onHitWall(HitWallEvent e) {
        back(50);
        turnRight(90);
    }
}
