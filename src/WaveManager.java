import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class WaveManager implements ActionListener {
    private int wave;
    private ArrayList<Enemies> allEnemies;
    private ArrayList<Enemies> enemiesSpawn;
    private ArrayList<Enemies> enemies;
    private ArrayList<Player> chars;
    private Timer timer;
    private int spawnNumber;
    private int enemiesRemaining;
    private int enemyNumber;

    public WaveManager() {
        spawnNumber = 0;
        timer = new Timer(2000,this);
        wave = 14;
        enemies = new ArrayList<>();
        allEnemies = new ArrayList<>();
        startWave();
    }
    private void startWave() {
        wave += 1;
        setSpawnNumber();
        initializeEnemies();
        spawn();
    }
    public int getWave () {
        return wave;
    }
    public ArrayList<Enemies> getEnemies() {
        return enemiesSpawn;
    }
    public void removeEnemy(Enemies enemy) {
        enemiesSpawn.remove(enemy);
        enemiesRemaining -= 1;
    }
    private void setSpawnNumber() {
        if (wave == 15) { spawnNumber = 10; }
        else if (wave >= 10) { spawnNumber = (int) (wave * 1.1); }
        else if (wave >= 5) { spawnNumber = (int) (wave * 1.2); }
        else if (wave >= 2) { spawnNumber = wave * 5; }
        else if (wave == 1) { spawnNumber = 5; }
        enemiesRemaining = spawnNumber;
    }
    private void initializeEnemies() {
        if (wave == 16) {
            enemies.add(new Death("src/Assets/Death/Run/DeathRun (1).png","Death",wave));
        } else if (wave == 15) {
            for (int i = 1; i <= spawnNumber; i++) {
                int strength = (int) (Math.random() * 50) + 1;
                if (strength == 1) {
                    enemies.add(new RatAxe("src/Assets/RatAxe/Run/RatAxeRun1.png","RatAxe",wave));
                } else if (strength == 2) {
                    enemies.add(new RatMage("src/Assets/RatMage/Run/RatMageRun1.png","RatMage",wave));
                } else if (strength >= 3 && strength <= 5) {
                    enemies.add(new Hoodlum("src/Assets/Hoodlum/Run/HoodlumRun1.png","Hoodlum",wave));
                } else if (strength >= 6 && strength <= 10) {
                    enemies.add(new SkullWolf("src/Assets/SkullWolf/Run/SkullWolfRun (1).png","SkullWolf",wave));
                } else if (strength >= 11 && strength <= 15) {
                    enemies.add(new Golem("src/Assets/Golem/Run/GolemRun1.png","Golem",wave));
                } else if (strength >= 16 && strength <= 22) {
                    enemies.add(new Cacodaemon("src/Assets/Cacodaemon/Run/CacodaemonRun1.png","Cacodaemon",wave));
                } else if (strength >= 23 && strength <= 29) {
                    enemies.add(new Death("src/Assets/Death/Run/DeathRun (1).png","Death",wave));
                } else if (strength >= 30 && strength <= 40) {
                    enemies.add(new Minotaur("src/Assets/Minotaur/Run/MinotaurRun (1).png","Minotaur",wave));
                } else if (strength >= 41 && strength <= 50) {
                    enemies.add(new NightBorne("src/Assets/NightBorne/Run/NightBorneRun1.png","NightBorne",wave));
                }
            }
//            enemies.add(); // boss monster
        } else if (wave >= 10) {
            for (int i = 1; i <= spawnNumber; i++) {
                int strength = (int) (Math.random() * 50) + 1;
                if (strength <= 2) {
                    enemies.add(new RatAxe("src/Assets/RatAxe/Run/RatAxeRun1.png","RatAxe",wave));
                } else if (strength <= 5) {
                    enemies.add(new RatMage("src/Assets/RatMage/Run/RatMageRun1.png","RatMage",wave));
                } else if (strength <= 10) {
                    enemies.add(new Hoodlum("src/Assets/Hoodlum/Run/HoodlumRun1.png","Hoodlum",wave));
                } else if (strength <= 25) {
                    enemies.add(new SkullWolf("src/Assets/SkullWolf/Run/SkullWolfRun (1).png","SkullWolf",wave));
                } else if (strength <= 40) {
                    enemies.add(new Golem("src/Assets/Golem/Run/GolemRun1.png","Golem",wave));
                } else if (strength <= 45) {
                    enemies.add(new Cacodaemon("src/Assets/Cacodaemon/Run/CacodaemonRun1.png","Cacodaemon",wave));
                } else if (strength <= 50) {
                    enemies.add(new Death("src/Assets/Death/Run/DeathRun (1).png","Death",wave));
                }
            }
        } else if (wave >= 5) {
            for (int i = 1; i <= spawnNumber; i++) {
                int strength = (int) (Math.random() * 50) + 1;
                if (strength <= 5) {
                    enemies.add(new RatAxe("src/Assets/RatAxe/Run/RatAxeRun1.png","RatAxe",wave));
                } else if (strength <= 10) {
                    enemies.add(new RatMage("src/Assets/RatMage/Run/RatMageRun1.png","RatMage",wave));
                } else if (strength <= 20) {
                    enemies.add(new Hoodlum("src/Assets/Hoodlum/Run/HoodlumRun1.png","Hoodlum",wave));
                } else if (strength <= 35) {
                    enemies.add(new SkullWolf("src/Assets/SkullWolf/Run/SkullWolfRun (1).png","SkullWolf",wave));
                } else if (strength <= 50) {
                    enemies.add(new Golem("src/Assets/Golem/Run/GolemRun1.png","Golem",wave));
                }
            }
        } else if (wave >= 2) {
            for (int i = 1; i <= spawnNumber; i++) {
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
            for (int i = 1; i <= spawnNumber; i++) {
                int strength = (int) (Math.random() * 2) + 1;
                if (strength == 1) {
                    enemies.add(new RatAxe("src/Assets/RatAxe/Run/RatAxeRun1.png", "RatAxe", wave));
                } else if (strength == 2) {
                    enemies.add(new RatMage("src/Assets/RatMage/Run/RatMageRun1.png", "RatMage", wave));
                }
            }
        }
    }
    public void spawn() {
        enemiesSpawn = new ArrayList<>(enemies);
        timer.start();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            if (!enemiesSpawn.isEmpty() && enemyNumber < enemiesSpawn.size()) {
                enemiesSpawn.get(enemyNumber).start();
                enemyNumber++;
            } else {
                timer.stop();
            }
        }
    }
}