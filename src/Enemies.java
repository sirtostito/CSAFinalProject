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
    private double hp;
    private Animation run;
    private Animation death;
    private boolean spawned;
    public Enemies(String rightImg, String sprite, double hp, double speed) {
        facingRight = false;
        this.speed = speed;
        xCoord = 2000;
        yCoord = 2000;
        spawned = false;
        this.sprite = sprite;
        this.hp = hp;
        try {
            right = ImageIO.read(new File(rightImg));
        } catch (IOException e) {
            System.out.println(e.getMessage() + " " + this.sprite);
        }
    }

    public void start() {
        spawned = true;
        xCoord = 713;
        yCoord = 222;
    }

    public void move() {
        if (hp > 0) {
            if (yCoord >= 221 && yCoord <= 223 && xCoord <= 713 && xCoord >= 600) {
                facingRight = false;
                moveLeft();
            }
            if (xCoord <= 601 && xCoord >= 599 && yCoord <= 335 && yCoord >= 222) {
                facingRight = false;
                moveDown();
            }
            if (yCoord <= 336 && yCoord >= 334 && xCoord <= 601 && xCoord >= 360) {
                facingRight = false;
                moveLeft();
            }
            if (yCoord >= 190 && yCoord <= 336 && xCoord <= 361 && xCoord >= 359) {
                facingRight = false;
                moveUp();
            }
            if (xCoord >= 124 && xCoord <= 361 && yCoord <= 191 && yCoord >= 189) {
                facingRight = false;
                moveLeft();
            }
            if (xCoord >= 123 && xCoord <= 125 && yCoord <= 470 && yCoord >= 189) {
                facingRight = false;
                moveDown();
            }
            if (xCoord <= 360 && xCoord >= 123 && yCoord <= 471 && yCoord >= 469) {
                facingRight = true;
                moveRight();
            }
            if (yCoord <= 585 && yCoord >= 469 && xCoord <= 361 && xCoord >= 359) {
                facingRight = true;
                moveDown();
            }
            if (xCoord <= 435 && xCoord >= 359 && yCoord >= 584 && yCoord <= 586) {
                facingRight = true;
                moveRight();
            }
            if (yCoord <= 720 && yCoord >= 584 && xCoord <= 436 && xCoord >= 434) {
                facingRight = true;
                moveDown();
            }
        }
    }

    public void damaged(double damage) {
        hp -= damage;
    }

    public void death(int frames) {
        death.resetFrames();
        for (int i = 1; i <= frames; i++) {
            getEnemyImage("Death");
        }
        spawned = false;
        GraphicsPanel.waveManager.removeEnemy(this);
    }

    public Rectangle enemyRect() {
        int imageHeight = right.getHeight();
        int imageWidth = right.getWidth();
        Rectangle rect = new Rectangle((int) xCoord, (int) yCoord, imageWidth, imageHeight);
        return rect;
    }

    public BufferedImage getEnemyImage(String action) {
        if (action.equals("Run")) {
            return run.getActiveFrame();
        } else {
            return death.getActiveFrame();
        }
    }

    public boolean isFacingRight() {
        return facingRight;
    }

    public int getxCoord() {
        if (facingRight) {
            return (int) xCoord;
        } else {
            return (int) (xCoord + (getEnemyImage("Run").getWidth()));
        }
    }

    public int getyCoord() {
        return (int) yCoord;
    }

    public void moveRight() {
        xCoord += speed;
    }

    public void moveLeft() {
        xCoord -= speed;
    }

    public void moveUp() {
        yCoord -= speed;
    }

    public void moveDown() {
        yCoord += speed;
    }

    public int getHeight() {
        return getEnemyImage("Idle").getHeight();
    }

    public int getWidth() {
        if (facingRight) {
            return getEnemyImage("Idle").getWidth();
        } else {
            return getEnemyImage("Idle").getWidth() * -1;
        }
    }

    public boolean isSpawned() {
        return spawned;
    }

    public String test() {
        return sprite + " " + right;
    }
}