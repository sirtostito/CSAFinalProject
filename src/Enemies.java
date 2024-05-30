import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Enemies {
    private double speed;
    private BufferedImage right;
    private boolean facingRight;
    private double xCoord;
    private double yCoord;
    private String sprite;
    private Animation idle;
    private int hp;
    private Animation run;
    private Animation death;
    public Enemies(String rightImg, String sprite) {
        facingRight = true;
        this.speed = speed;
        xCoord = 90;
        yCoord = 90;
        this.sprite = sprite;
        try {
            right = ImageIO.read(new File(rightImg));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Rectangle enemyRect() {
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
        } else {
            return death.getActiveFrame();
        }
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

    public void faceRight() {
        facingRight = true;
    }

    public void faceLeft() {
        facingRight = false;
    }

    public void moveRight() {
        if (xCoord + speed <= 920) {
            xCoord += speed;
        }
    }

    public void moveLeft() {
        if (xCoord - speed >= 0) {
            xCoord -= speed;
        }
    }

    public void moveUp() {
        if (yCoord - speed >= 0) {
            yCoord -= speed;
        }
    }

    public void moveDown() {
        if (yCoord + speed <= 435) {
            yCoord += speed;
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
