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
    private Allies allies;
    private Gem gem;
    private boolean[] pressedKeys;
    private ArrayList<Coin> coins;
    private Timer timer;
    private int time;
    private String anim;
    private ArrayList<Allies> pawns;
    public static WaveManager waveManager;
    private int tempX;
    private int tempY;

    public GraphicsPanel(String name) {
        try {
            background = ImageIO.read(new File("src/Assets/maps/rsz_1mnqwyzz9e5r61.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
//        gem = new Gem();
        waveManager = new WaveManager();
        pawns = new ArrayList<>();
        anim = "Idle";
//        player = new Player("src/Assets/Knight/Idle/KnightIdle1.png", name, "Knight");
        coins = new ArrayList<>();
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
        super.paintComponent(g);  // just do this
        g.drawImage(background, 0, 0, null);  // the order that things get "painted" matter; we put background down first
        //Here we use drawImage with additional parameters for width and height
        //Combined with the modified functions in the Player class, this does not modify the actual player image
        //Instead, it allows us to modify how the player image is drawn on the graphics object
        //However, this could potentially introduce desyncs between the graphics and the game logic

        // draw score
        g.setFont(new Font("Courier New", Font.BOLD, 24));
//        g.drawString(player.getName() + "'s Score: " + player.getScore(), 20, 40);
//        g.drawString("Time: " + time, 20, 30);
        g.drawString("Enemies " + waveManager.getEnemiesRemaining(), 20, 30);
        g.drawString("Wave: " + waveManager.getWave(), 330, 30);
//        g.drawString("Cord: " + tempX + ", " + tempY, 550, 30);
        g.drawString("Coins: " + waveManager.getCoins(),550,30);
//        Enemies test = new Enemies("src/Assets/")
//        g.drawImage()

        for (Enemies enemy : waveManager.getEnemies()) {
            updateEnemyLocations();
            g.drawImage(enemy.getEnemyImage("Run"), enemy.getxCoord(), enemy.getyCoord(), enemy.getWidth(), enemy.getHeight(),null);
        }
        allyAttack();
        for (Allies ally : waveManager.getAllies()) {
            if (!ally.isEndlag()) {
                g.drawImage(ally.getPlayerImage("Idle"), ally.getxCoord(), ally.getyCoord(), ally.getWidth(), ally.getHeight(), null);
            } else g.drawImage(ally.attack(),ally.getxCoord(),ally.getyCoord(),null);
        }
    }
    private void allyAttack() {
        for (Allies ally : waveManager.getAllies()) {
            for (Enemies enemy : waveManager.getEnemies()) {
                if (ally.playerRect().intersects(enemy.enemyRect())) {
                    if (!ally.isEndlag()) {
                        enemy.damaged(ally.getDamage());
                    }
                    ally.attackRefresh();
                }
            }
        }
    }
    private void updateEnemyLocations() {
        for (Enemies enemy : waveManager.getEnemies()) {
            if (enemy.isSpawned()) {
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
        if (pressedKeys[74]) {
            System.out.println("J");
            if (waveManager.getCoins() > 500) {
                waveManager.addAlly(new Jones(tempX - 30, tempY - 31));
                waveManager.subtractCoins(500);
            }
        }
        if (pressedKeys[32]) {
            System.out.println("space");
            if (waveManager.getWave() < 15 && waveManager.getEnemies().isEmpty()) {
                System.out.println("end wave");
                waveManager.endWave();
            }
        }
        pressedKeys[key] = false;
    }

    // ----- MouseListener interface methods -----
    public void mouseClicked(MouseEvent e) { }  // unimplemented; if you move your mouse while clicking,
    // this method isn't called, so mouseReleased is best

    public void mousePressed(MouseEvent e) { } // unimplemented

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {  // left mouse click
            System.out.println("clicked");
            Point mouseClickLocation = e.getPoint();
            tempX = mouseClickLocation.x;
            tempY = mouseClickLocation.y;
            for (Allies ally : waveManager.getAllies()) {
                Rectangle rect = new Rectangle(tempX - 1, tempY - 1, tempX + 1, tempY + 1);
                if (rect.intersects(ally.upgradeRect())) {
                    ally.upgraded();
                }
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
}
