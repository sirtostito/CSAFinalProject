import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Enemies {
    private double moveAmt;
    private BufferedImage right;
    private boolean facingRight;
    private double xCoord;
    private double yCoord;
    private String sprite;
    private ArrayList<Enemies> enemies; //move this to GraphicsPanel
    private Animation idle;
    private Animation run;
    private Animation attack;
    private Animation death;
    private Animation special;
    private Animation damage;
    public Enemies(String rightImg, String sprite, int moveAmt) {
        enemies = new ArrayList<>();
        facingRight = true;
        xCoord = 90;
        yCoord = 90;
        this.sprite = sprite;
        this.moveAmt = moveAmt;
        try {
            right = ImageIO.read(new File(rightImg));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Rectangle coinRect() {
        int imageHeight = right.getHeight();
        int imageWidth = right.getWidth();
        Rectangle rect = new Rectangle((int) xCoord, (int) yCoord, imageWidth, imageHeight);
        return rect;
    }

    public BufferedImage getEnemyImage(String action) {
        if (action.equals("Idle")) {
            return idle.getActiveFrame();
        } else if (action.equals("Run")) {
            return run.getActiveFrame();
        } else if (action.equals("Attack")) {
            return attack.getActiveFrame();
        } else if (action.equals("Damage")) {
            return damage.getActiveFrame();
        } else if (action.equals("Death")) {
            return death.getActiveFrame();
        } else return special.getActiveFrame();
    }

    public int getxCoord() {
        if (facingRight) {
            return (int) xCoord;
        } else {
            return (int) (xCoord + (getEnemyImage("Idle").getWidth()));
        }
    }

    public int getyCoord() {
        return (int) yCoord;
    }

    public void enemyAdd(Enemies enemy) {
        enemies.add(enemy);
    }

    public void faceRight() {
        facingRight = true;
    }

    public void faceLeft() {
        facingRight = false;
    }

    public void moveRight() {
        if (xCoord + moveAmt <= 920) {
            xCoord += moveAmt;
        }
    }

    public void moveLeft() {
        if (xCoord - moveAmt >= 0) {
            xCoord -= moveAmt;
        }
    }

    public void moveUp() {
        if (yCoord - moveAmt >= 0) {
            yCoord -= moveAmt;
        }
    }

    public void moveDown() {
        if (yCoord + moveAmt <= 435) {
            yCoord += moveAmt;
        }
    }

    public void turn() {
        if (facingRight) {
            faceLeft();
        } else {
            faceRight();
        }
    }
}
