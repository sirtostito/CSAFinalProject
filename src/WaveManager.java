import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class WaveManager {
    private int wave;
    private ArrayList<Enemies> allEnemies;
    public static ArrayList<Enemies> enemies;
    public static ArrayList<Player> chars;

    public WaveManager() {
        wave = 0;
        enemies = new ArrayList<>();
        allEnemies = new ArrayList<>();
        startWave();
    }
    private void startWave() {
        wave += 1;
        if (wave == 15) {
            for (int i = 1; i <= wave * 2 - 1; i++) {
                int strength = (int) (Math.random() * 50) + 1;
                if (strength == 1) {
                    enemies.add(new RatAxe("src/Assets/RatAxe/Run/RatAxeRun1.png","RatAxe",wave));
                } else if (strength == 2) {
                    enemies.add(new RatMage("src/Assets/RatMage/Run/RatMageRun1.png","RatMage",wave));
                } else if (strength >= 3 && strength <= 5) {
                    enemies.add(new Hoodlum("src/Assets/Hoodlum/Run/HoodlumRun1.png","Hoodlum",wave));
                } else if (strength >= 6 && strength <= 10) {
                    enemies.add(new SkullWolf("src/Assets/SkullWolf/Run/SkullWolfRun1.png","SkullWolf",wave));
                } else if (strength >= 11 && strength <= 15) {
                    enemies.add(new Golem("src/Assets/Golem/Run/GolemRun1.png","Golem",wave)); // intermediate
                } else if (strength >= 16 && strength <= 22) {
                    enemies.add(new Cacodaemon("src/Assets/Cacodaemon/Run/CacodaemonRun1","Cacodaemon",wave));
                } else if (strength >= 23 && strength <= 29) {
                    enemies.add(new Death("src/Assets/Death/Run/DeathRun1.png","Death",wave));
                } else if (strength >= 30 && strength <= 40) {
                    enemies.add(new Minotaur("src/Assets/Minotaur/Run/MinotaurRun1.png","Minotaur",wave));
                } else if (strength >= 41 && strength <= 50) {
                    enemies.add(new NightBorne("src/Assets/NightBorne/Run/NightBorneRun1.png","NightBorne",wave));
                }
            }
            enemies.add(); // boss monster
        } else if (wave >= 10) {
            for (int i = 1; i <= wave * 4; i++) {
                int strength = (int) (Math.random() * 50) + 1;
                if (strength <= 2) {
                    enemies.add(new RatAxe("src/Assets/RatAxe/Run/RatAxeRun1.png","RatAxe",wave));
                } else if (strength <= 5) {
                    enemies.add(new RatMage("src/Assets/RatMage/Run/RatMageRun1.png","RatMage",wave));
                } else if (strength <= 10) {
                    enemies.add(new Hoodlum("src/Assets/Hoodlum/Run/HoodlumRun1.png","Hoodlum",wave));
                } else if (strength <= 25) {
                    enemies.add(new SkullWolf("src/Assets/SkullWolf/Run/SkullWolfRun1.png","SkullWolf",wave));
                } else if (strength <= 40) {
                    enemies.add(); // intermediate
                } else if (strength <= 45) {
                    enemies.add(new Cacodaemon("src/Assets/Cacodaemon/Run/CacodaemonRun1","Cacodaemon",wave));
                } else if (strength <= 50) {
                    enemies.add(); // intermediate to high
                }
            }
        } else if (wave >= 5) {
            for (int i = 1; i <= wave * 5; i++) {
                int strength = (int) (Math.random() * 50) + 1;
                if (strength <= 5) {
                    enemies.add(new RatAxe("src/Assets/RatAxe/Run/RatAxeRun1.png","RatAxe",wave));
                } else if (strength <= 10) {
                    enemies.add(new RatMage("src/Assets/RatMage/Run/RatMageRun1.png","RatMage",wave));
                } else if (strength <= 20) {
                    enemies.add(new Hoodlum("src/Assets/Hoodlum/Run/HoodlumRun1.png","Hoodlum",wave));
                } else if (strength <= 35) {
                    enemies.add(new SkullWolf("src/Assets/SkullWolf/Run/SkullWolfRun1.png","SkullWolf",wave));
                } else if (strength <= 50) {
                    enemies.add(); // intermediate
                }
            }
        } else if (wave >= 2) {
            for (int i = 1; i <= wave * 10; i++) {
                int strength = (int) (Math.random() * 50) + 1;
                if (strength <= 15) {
                    enemies.add(new RatAxe("src/Assets/RatAxe/Run/RatAxeRun1.png","RatAxe",wave));
                } else if (strength <= 30) {
                    enemies.add(new RatMage("src/Assets/RatMage/Run/RatMageRun1.png","RatMage",wave));
                } else if (strength <= 50) {
                    enemies.add(new Hoodlum("src/Assets/Hoodlum/Run/HoodlumRun1.png","Hoodlum",wave));
                }
            }
        } else if (wave == 1) {
            for (int i = 1; i <= 5; i++) {
                int strength = (int) (Math.random() * 2) + 1;
                if (strength == 1) {
                    enemies.add(new RatAxe("src/Assets/RatAxe/Run/RatAxeRun1.png","RatAxe",wave));
                } else if (strength == 2) {
                    enemies.add(new RatMage("src/Assets/RatMage/Run/RatMageRun1.png","RatMage",wave));
                }
            }
            spawn();
        }
    }
    public int getWave () {
        return wave;
    }
    public void spawn() {
        ArrayList<Enemies> array = new ArrayList<>(enemies);
        for (int i = 0; i < array.size(); i++) {
            array.get(i).start();
            try {
                wait(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
