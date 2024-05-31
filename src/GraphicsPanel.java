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
    private Player player;
    private Gem gem;
    private boolean[] pressedKeys;
    private ArrayList<Coin> coins;
    private Timer timer;
    private int time;
    private String anim;
    private ArrayList<Player> pawns;

    public GraphicsPanel(String name) {
        try {
            background = ImageIO.read(new File("src/Assets/maps/rsz_1mnqwyzz9e5r61.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
//        gem = new Gem();
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
        g.drawImage(player.getPlayerImage(anim), player.getxCoord(), player.getyCoord(), player.getWidth(), player.getHeight(), null);
        anim = "Idle";
        // this loop does two things:  it draws each Coin that gets placed with mouse clicks,
        // and it also checks if the player has "intersected" (collided with) the Coin, and if so,
        // the score goes up and the Coin is removed from the arraylist
        for (int i = 0; i < coins.size(); i++) {
            Coin coin = coins.get(i);
            g.drawImage(coin.getImage(), coin.getxCoord(), coin.getyCoord(), null); // draw Coin
            if (player.playerRect().intersects(coin.coinRect())) { // check for collision
                player.collectCoin();
                coins.remove(i);
                i--;
            }
//            if (player.playerRect().intersects(gem.hitBox())) {
//                if (time == 60) {
//
//                }
//            }
        }

//        g.drawImage(gem.getGem(),(int) gem.getX(),(int) gem.getY(), gem.getWidth(), gem.getHeight(), null);

        // draw score
        g.setFont(new Font("Courier New", Font.BOLD, 24));
        g.drawString(player.getName() + "'s Score: " + player.getScore(), 20, 40);
        g.drawString("Time: " + time, 20, 70);

//        // player moves left (A)
//        if (pressedKeys[65]) {
//            anim = "Run";
//            player.faceLeft();
//            player.moveLeft();
//        }
//
//        // player moves right (D)
//        if (pressedKeys[68]) {
//            anim = "Run";
//            player.faceRight();
//            player.moveRight();
//        }
//
//        // player moves up (W)
//        if (pressedKeys[87]) {
//            anim = "Run";
//            player.moveUp();
//        }
//
//        // player moves down (S)
//        if (pressedKeys[83]) {
//            anim = "Run";
//            player.moveDown();
//        }
//
//        if (pressedKeys[80]) {
//            anim = "Attack";
//        }
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
        pressedKeys[key] = false;
    }

    // ----- MouseListener interface methods -----
    public void mouseClicked(MouseEvent e) { }  // unimplemented; if you move your mouse while clicking,
    // this method isn't called, so mouseReleased is best

    public void mousePressed(MouseEvent e) { } // unimplemented

    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {  // left mouse click
            Point mouseClickLocation = e.getPoint();
            Player player = new Player(mouseClickLocation.x, mouseClickLocation.y);
            pawns.add(player);
//        } else {
//            Point mouseClickLocation = e.getPoint();
//            if (player.playerRect().contains(mouseClickLocation)) {
////                player.turn();
//            }
        }
    }

    public void mouseEntered(MouseEvent e) { } // unimplemented

    public void mouseExited(MouseEvent e) { } // unimplemented

    // ACTIONLISTENER INTERFACE METHODS: used for buttons AND timers!
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer) {
            time++;
        }
    }
}
