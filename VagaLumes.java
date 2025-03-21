package Vaga_lumes;
import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class Vaga_lumes extends AdvancedRobot {
    public void run() {
        setAdjustGunForRobotTurn(true); // Permite movimento independente do canhão
        setAdjustRadarForGunTurn(true); // Permite radar independente do canhão
        
        while (true) {
            setTurnRadarRight(360); // Gira o radar continuamente
            moveRandomly();
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        double enemyBearing = getHeading() + e.getBearing(); // Direção absoluta do inimigo
        double gunTurn = normalRelativeAngleDegrees(enemyBearing - getGunHeading());
        setTurnGunRight(gunTurn); // Ajusta a mira para o inimigo

        // Atira com potência ajustada à distância
        double distance = e.getDistance();
        if (distance > 200) {
            fire(2); // Tiro de potência média para inimigos distantes
        } else {
            fire(3); // Tiro de potência máxima para inimigos próximos
        }
        
        // Mantém o radar no inimigo
        setTurnRadarRight(normalRelativeAngleDegrees(enemyBearing - getRadarHeading()));
    }

    public void onHitWall(HitWallEvent e) {
        back(50); // Recua ao bater em paredes
        turnRight(45); // Gira para evitar nova colisão
    }

    public void onHitByBullet(HitByBulletEvent e) {
        setTurnRight(90); // Gira 90 graus para evitar novos tiros
        setAhead(100); // Avança para fugir
    }

    private void moveRandomly() {
        // Movimentos aleatórios para dificultar a mira dos inimigos
        if (Math.random() > 0.5) {
            setAhead(100);
            setTurnRight(Math.random() * 90 - 45); // Gira entre -45 e +45 graus
        } else {
            setBack(100);
            setTurnLeft(Math.random() * 90 - 45); // Gira entre -45 e +45 graus
        }
    }
}
