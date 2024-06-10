import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class WaveManager implements ActionListener {
    private int wave;
    private ArrayList<Enemies> enemies;
    private Timer timer;
    private int spawnNumber;
    private int enemiesRemaining;
    private int enemyNumber;
    private ArrayList<Allies> allies;
    private int coins;
    private int time;

    public WaveManager() {
        coins = 5000;
        allies = new ArrayList<>();
        spawnNumber = 0;
        wave = 0;
        enemies = new ArrayList<>();
    }
    private void startWave() {
        wave += 1;
        timer = new Timer(2000,this);
        enemies = new ArrayList<>();
        setSpawnNumber();
        initializeEnemies();
        timer.start();
        spawn();
    }
    public int getWave () {
        return wave;
    }
    public ArrayList<Enemies> getEnemies() {
        return enemies;
    }
    public void removeEnemy(Enemies enemy) {
        enemies.remove(enemy);
        enemyNumber -= 1;
        enemiesRemaining = enemies.size();
    }
    private void setSpawnNumber() {
        if (wave == 5) { spawnNumber = 5; }
        else if (wave == 4) { spawnNumber = 5; }
        else if (wave == 3) { spawnNumber = 5; }
        else if (wave == 2) { spawnNumber = 5; }
        else if (wave == 1) { spawnNumber = 5; }
    }
    private void initializeEnemies() {
        if (wave == 6) {
            enemies.add(new Death("src/Assets/Death/Run/DeathRun (1).png","Death",wave));
        } else if (wave == 5) {
            for (int i = 1; i <= spawnNumber; i++) {
                int strength = (int) (Math.random() * 6) + 1;
                if (strength == 1) {
                    enemies.add(new Cacodaemon("src/Assets/Cacodaemon/Run/CacodaemonRun1.png","Cacodaemon",wave));
                } else if (strength == 2) {
                    enemies.add(new Death("src/Assets/Death/Run/DeathRun (1).png","Death",wave));
                } else if (strength <= 4) {
                    enemies.add(new Minotaur("src/Assets/Minotaur/Run/MinotaurRun (1).png","Minotaur",wave));
                } else if (strength <= 6) {
                    enemies.add(new NightBorne("src/Assets/NightBorne/Run/NightBorneRun1.png","NightBorne",wave));
                }
            }
//            enemies.add(); // boss monster
        } else if (wave == 4) {
            for (int i = 1; i <= spawnNumber; i++) {
                int strength = (int) (Math.random() * 6) + 1;
                if (strength == 1) {
                    enemies.add(new SkullWolf("src/Assets/SkullWolf/Run/SkullWolfRun (1).png","SkullWolf",wave));
                } else if (strength == 2) {
//                    enemies.add(new Golem("src/Assets/Golem/Run/GolemRun1.png","Golem",wave));
                } else if (strength <= 4) {
                    enemies.add(new Cacodaemon("src/Assets/Cacodaemon/Run/CacodaemonRun1.png","Cacodaemon",wave));
                } else if (strength <= 6) {
                    enemies.add(new Death("src/Assets/Death/Run/DeathRun (1).png","Death",wave));
                }
            }
        } else if (wave == 3) {
            for (int i = 1; i <= spawnNumber; i++) {
                int strength = (int) (Math.random() * 4) + 1;
                if (strength <= 2) {
                    enemies.add(new Hoodlum("src/Assets/Hoodlum/Run/HoodlumRun1.png","Hoodlum",wave));
                } else if (strength == 3) {
                    enemies.add(new SkullWolf("src/Assets/SkullWolf/Run/SkullWolfRun (1).png","SkullWolf",wave));
                } else if (strength == 4) {
//                    enemies.add(new Golem("src/Assets/Golem/Run/GolemRun1.png","Golem",wave));
                    enemies.add(new SkullWolf("src/Assets/SkullWolf/Run/SkullWolfRun (1).png","SkullWolf",wave));
                }
            }
        } else if (wave == 2) {
            for (int i = 1; i <= spawnNumber; i++) {
                int strength = (int) (Math.random() * 4) + 1;
                if (strength == 1) {
                    enemies.add(new RatAxe("src/Assets/RatAxe/Run/RatAxeRun1.png","RatAxe",wave));
                } else if (strength == 2) {
                    enemies.add(new RatMage("src/Assets/RatMage/Run/RatMageRun1.png","RatMage",wave));
                } else if (strength <= 4) {
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
    public void endWave() {
        enemies = new ArrayList<>();
        enemyNumber = 0;
        startWave();
    }
    public int getEnemiesRemaining() {
        return enemiesRemaining;
    }
    public void subtractCoins(int subtract) {
        coins -= subtract;
    }
    public void addCoins(int add) {
        coins += add;
    }
    public int getCoins() {
        return coins;
    }
    public ArrayList<Allies> getAllies() {
        return allies;
    }
    public void addAlly(Allies ally) {
        allies.add(ally);
    }
    public void spawn() {
        timer.start();
    }
    public int getTime() {
        return time;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            time++;
            if (!enemies.isEmpty() && enemyNumber < enemies.size()) {
                enemies.get(enemyNumber).start();
                enemyNumber++;
            } else {
                timer.stop();
            }
        }
    }
}