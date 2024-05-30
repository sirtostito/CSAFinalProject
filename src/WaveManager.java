import java.lang.reflect.Array;
import java.util.ArrayList;

public class WaveManager {
    private int wave;
    private ArrayList<Enemies> allEnemies;
    public static ArrayList<Enemies> enemies;
    public WaveManager() {
        wave = 0;
        enemies = new ArrayList<>();
        allEnemies = new ArrayList<>();
        startWave();
    }
    private void startWave() {
        wave += 1;
        if (wave == 15) {
            for (int i = 1; i <= wave * 2; i++) {
                // Make a system to add a random number of stronger monsters
            }
        } else if (wave >= 10) {
            for (int i = 1; i <= wave * 4; i++) {
                // Make a system to add a random number of intermediate - stronger monsters
            }
        } else if (wave >= 5) {
            for (int i = 1; i <= wave * 5; i++) {
                // Make a system to add a random number of intermediate monsters
            }
        } else if (wave >= 2) {
            for (int i = 1; i <= wave * 10; i++) {
                // Make a system to add a random number of weak - intermediate monsters
            }
        } else if (wave == 1) {
            for (int i = 1; i <= 5; i++) {
                enemies.add(new RatMage("src/Assets/RatMage/Run/RatMageRun1","RatMage",wave));
            }
            spawn();
        }
    }
    public int getWave () {
        return wave;
    }
    public void spawn() {
        ArrayList<Enemies> array = new ArrayList<>(enemies);

    }
}
