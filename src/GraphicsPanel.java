import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GraphicsPanel extends JPanel implements KeyListener, MouseListener, ActionListener {
    private BufferedImage background;
    private boolean[] pressedKeys;
    private Timer timer;
    private int time;
    private String anim;
    private ArrayList<Allies> pawns;
    public static WaveManager waveManager;
    private int tempX;
    private int tempY;
    private static boolean lose;
    private String name;
    private BufferedImage black;
    private BufferedImage blank;

    public GraphicsPanel(String name) {
        this.name = name;
        lose = false;
        try {
            background = ImageIO.read(new File("src/Assets/maps/rsz_1mnqwyzz9e5r61.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            black = ImageIO.read(new File("src/Assets/bUKrna.jpeg"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        try {
            blank = ImageIO.read(new File("src/Assets/Blank.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
//        gem = new Gem();
        waveManager = new WaveManager();
        pawns = new ArrayList<>();
        anim = "Idle";
//        player = new Player("src/Assets/Knight/Idle/KnightIdle1.png", name, "Knight");
        pressedKeys = new boolean[128];
        time = 0;
        timer = new Timer(1000, this); // this Timer will call the actionPerformed interface method every 1000ms = 1 second
        timer.start();
        addKeyListener(this);
        addMouseListener(this);
        setFocusable(true); // this line of code + one below makes this panel active for keylistener events
        requestFocusInWindow(); // see comment above
    }

    @Override
    public void paintComponent(Graphics g) {
        if (!lose) {
            super.paintComponent(g);  // just do this
            if (waveManager.getWave() <= 5) {
                g.drawImage(background, 0, 0, null);  // the order that things get "painted" matter; we put background down first
                //Here we use drawImage with additional parameters for width and height
                //Combined with the modified functions in the Player class, this does not modify the actual player image
                //Instead, it allows us to modify how the player image is drawn on the graphics object
                //However, this could potentially introduce desyncs between the graphics and the game logic

                // draw score
                g.setFont(new Font("Courier New", Font.BOLD, 24));
//        g.drawString(player.getName() + "'s Score: " + player.getScore(), 20, 40);
                g.drawString("Time: " + waveManager.getTime(), 20, 170);
                g.drawString("Enemies " + waveManager.getEnemiesRemaining(), 20, 30);
                g.drawString("Wave: " + waveManager.getWave(), 330, 30);
                g.drawString("Cord: " + tempX + ", " + tempY, 300, 700);
                g.drawString("Coins: " + waveManager.getCoins(), 550, 30);
//        Enemies test = new Enemies("src/Assets/")
//        g.drawImage()
                allyAttack();
                updateEnemyLocations();
                for (Enemies enemy : waveManager.getEnemies()) {
                    g.drawImage(enemy.getEnemyImage("Run"), enemy.getxCoord(), enemy.getyCoord(), enemy.getWidth(), enemy.getHeight(), null);
                }
                for (Allies ally : waveManager.getAllies()) {
                    if (!ally.isEndlag()) {
                        g.drawImage(ally.getPlayerImage("Idle"), ally.getxCoord(), ally.getyCoord(), ally.getWidth(), ally.getHeight(), null);
                    } else {
                        g.drawImage(ally.attack(), ally.getxCoord(), ally.getyCoord(), null);
                        System.out.println("attacked");
                    }
                }
            } else {
                for (Enemies enemy : waveManager.getEnemies()) {
                    g.drawImage(enemy.getEnemyImage("Run"), enemy.getxCoord(), enemy.getyCoord(), enemy.getWidth(), enemy.getHeight(), null);
                }
                for (Allies ally : waveManager.getAllies()) {
                    if (!ally.isEndlag()) {
                        g.drawImage(ally.getPlayerImage("Idle"), ally.getxCoord(), ally.getyCoord(), ally.getWidth(), ally.getHeight(), null);
                    } else {
                        g.drawImage(ally.attack(), ally.getxCoord(), ally.getyCoord(), null);
                        System.out.println("attacked");
                    }
                }
            }
        } else {
            g.drawImage(black,0,0,null);
            BufferedImage grave = null;
            try {
                grave = ImageIO.read(new File("src/Assets/gravestone.png"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            g.drawImage(grave,200,200,null);
            g.setFont(new Font("Courier New", Font.BOLD, 40));
            g.drawString("Here Lies",293,465);
            if (name.isEmpty()) {
                g.drawString("You",365,550);
            } else if (name.length() >= 5) {
                g.drawString(name.substring(0, 5), 350, 550);
            } else {
                g.drawString(name,370,550);
            }
        }
    }
    private void allyAttack() {
        for (int ally = 0; ally < waveManager.getAllies().size(); ally++) {
            if (!waveManager.getAllies().get(ally).isEndlag()) {
                for (int enemy = 0; enemy < waveManager.getEnemies().size(); enemy++) {
                    if (waveManager.getAllies().get(ally).playerRect().intersects(waveManager.getEnemies().get(enemy).enemyRect())) {
                        waveManager.getEnemies().get(enemy).damaged(waveManager.getAllies().get(ally).getDamage());
//                    System.out.println("Attack refresh");
                        waveManager.getAllies().get(ally).attackRefresh();
                    }
                }
            }
        }
//        System.out.println("allies array list: " + waveManager.getAllies().size());
    }
    private void updateEnemyLocations() {
        for (Enemies enemy : waveManager.getEnemies()) {
            if (enemy.isSpawned()) {
//                System.out.println("Enemy moved");
                enemy.move();
            }
        }
    }

    // ----- KeyListener interface methods -----
    public void keyTyped(KeyEvent e) { } // unimplemented

    public void keyPressed(KeyEvent e) {
        // see this for all keycodes: https://stackoverflow.com/questions/15313469/java-keyboard-keycodes-list
        // A = 65, D = 68, S = 83, W = 87, left = 37, up = 38, right = 39, down = 40, space = 32, enter = 10
        int key = e.getKeyCode();
        pressedKeys[key] = true;
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (pressedKeys[49] && waveManager.getWave() == 69) {
            System.out.println("AttackOne");
            for (Allies ally : waveManager.getAllies()) {
                ally.setUpgrade(1);
                ally.attackRefresh();
            }
        }
        if (pressedKeys[50] && waveManager.getWave() == 69) {
            System.out.println("AttackTwo");
            for (Allies ally : waveManager.getAllies()) {
                ally.setUpgrade(2);
                ally.attackRefresh();
            }
        }
        if (pressedKeys[51] && waveManager.getWave() == 69) {
            System.out.println("AttackThree");
            for (Allies ally : waveManager.getAllies()) {
                ally.setUpgrade(3);
                ally.attackRefresh();
            }
        }
        if (pressedKeys[74]) {
            System.out.println("J");
            if (waveManager.getCoins() > 500) {
                waveManager.addAlly(new Jones(tempX - 30, tempY - 31));
                waveManager.subtractCoins(500);
            }
        }
        if (pressedKeys[32]) {
            System.out.println("space");
            if (waveManager.getWave() < 5 && waveManager.getEnemies().isEmpty()) {
                System.out.println("end wave");
                timer.stop();
                waveManager.endWave();
                timer.start();
            }
        }
        if (pressedKeys[70] && waveManager.getWave() == 69) {
            System.out.println("F");
//            if (waveManager.getCoins() >= 1000) {
            waveManager.addAlly(new FireKnight(tempX - 141,tempY - 126));
//                waveManager.subtractCoins(1000);
//            }
        }
        if (pressedKeys[87] && waveManager.getWave() == 69) {
            System.out.println("W");
//            if (waveManager.getCoins() >= 1500) {
            waveManager.addAlly(new WaterPriestess(tempX - 146,tempY - 128));
//                waveManager.subtractCoins(1500);
//            }
        }
        if (pressedKeys[75] && waveManager.getWave() == 69) {
            System.out.println("K");
//            if (waveManager.getCoins() >= ) {
            waveManager.addAlly(new Knight(tempX - 30,tempY - 45));
//                waveManager.subtractCoins(500);
//            }
        }
        if (pressedKeys[82] && waveManager.getWave() == 69) {
        }
        pressedKeys[key] = false;
    }

    // ----- MouseListener interface methods -----
    public void mouseClicked(MouseEvent e) { }  // unimplemented; if you move your mouse while clicking,
    // this method isn't called, so mouseReleased is best

    public void mousePressed(MouseEvent e) { } // unimplemented

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {  // left mouse click
            System.out.println("LMB CLICKED");
            Point mouseClickLocation = e.getPoint();
            tempX = mouseClickLocation.x;
            tempY = mouseClickLocation.y;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            System.out.println("RMB CLICKED");
            for (Allies ally : waveManager.getAllies()) {
                Rectangle rect = new Rectangle(tempX - 1, tempY - 1, tempX + 1, tempY + 1);
                if (rect.intersects(ally.upgradeRect())) {
                    System.out.println("upgrade attempted");
                    ally.upgraded();
                }
            }
        }
        if (e.getButton() == MouseEvent.BUTTON2) {
            System.out.println("Scrollbutton clicked");
            if (waveManager.getWave() == 69) {
                waveManager.clearAllies();
                waveManager.clearEnemies();
            }
        }
    }

    public void mouseEntered(MouseEvent e) { } // unimplemented

    public void mouseExited(MouseEvent e) { } // unimplemented

    // ACTIONLISTENER INTERFACE METHODS: used for buttons AND timers!
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            time++;
            updateEnemyLocations();
        }
    }
    public static void lose() {
        GraphicsPanel.lose = true;
    }
}
