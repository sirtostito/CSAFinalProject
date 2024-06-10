import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Allies {
    private BufferedImage right;
    private boolean facingRight;
    private double xCoord;
    private double yCoord;
    private int upgrade;
    private String name;
    private Animation idle;
    private Animation attackOne;
    private Animation attackTwo;
    private Animation attackThree;
    private String sprite;
    private int cost;
    private boolean endlag;

    public Allies(double x, double y, String rightImg, String sprite, int cost) {
        facingRight = true;
        xCoord = x; // starting position is (50, 435), right on top of ground
        yCoord = y;
        upgrade = 1;
        this.cost = cost;
        this.sprite = sprite;
        endlag = false;
        try {
            right = ImageIO.read(new File(rightImg));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //This function is changed from the previous version to let the player turn left and right
    //This version of the function, when combined with getWidth() and getHeight()
    //Allow the player to turn without needing separate images for left and right
    public int getxCoord() {
        if (facingRight) {
            return (int) xCoord;
        } else {
            return (int) (xCoord + (getPlayerImage("Idle").getWidth()));
        }
    }

    public int getyCoord() {
        return (int) yCoord;
    }

//    public int getScore() {
//        return score;
//    }

    public String getName() {
        return name;
    }

    public void faceRight() {
        facingRight = true;
    }

    public void faceLeft() {
        facingRight = false;
    }

//    public void moveRight() {
//        if (xCoord + MOVE_AMT <= 780) {
//            xCoord += MOVE_AMT;
//        }
//    }
//
//    public void moveLeft() {
//        if (xCoord - MOVE_AMT >= 0) {
//            xCoord -= MOVE_AMT;
//        }
//    }
//
//    public void moveUp() {
//        if (yCoord - MOVE_AMT >= 0) {
//            yCoord -= MOVE_AMT;
//        }
//    }
//
//    public void moveDown() {
//        if (yCoord + MOVE_AMT <= 780) {
//            yCoord += MOVE_AMT;
//        }
//    }
//
//    public void turn() {
//        if (facingRight) {
//            faceLeft();
//        } else {
//            faceRight();
//        }
//    }

//    public void collectCoin() {
//        score++;
//    }

    public BufferedImage getPlayerImage(String action) {
        return switch (action) {
            case "Idle" -> idle.getActiveFrame();
            default -> throw new IllegalStateException("Unexpected value: " + action);
        };
    }

    //These functions are newly added to let the player turn left and right
    //These functions when combined with the updated getxCoord()
    //Allow the player to turn without needing separate images for left and right
    public int getHeight() {
        return getPlayerImage("Idle").getHeight();
    }

    public int getWidth() {
        if (facingRight) {
            return getPlayerImage("Idle").getWidth();
        } else {
            return getPlayerImage("Idle").getWidth() * -1;
        }
    }
    // we use a "bounding Rectangle" for detecting collision
    public Rectangle playerRect() {
        int imageHeight = getPlayerImage("Idle").getHeight();
        int imageWidth = getPlayerImage("Idle").getWidth();
        Rectangle rect = new Rectangle((int) xCoord, (int) yCoord, imageWidth, imageHeight);
        return rect;
    }
    public double getDamage () {
        return 69420;
    }
    public BufferedImage attack() {
        return getPlayerImage("AttackThree");
    }
    public void attackRefresh() {}
    public Rectangle upgradeRect() {
        return new Rectangle();
    }
    public int getCost() {
        return cost;
    }
    public int getUpgrade() {
        return upgrade;
    }
    public boolean isEndlag() {
        return endlag;
    }
    public void setEndlag(boolean endlag) {
        this.endlag = endlag;
    }
    public void upgraded() {
        if (upgrade < 3 && GraphicsPanel.waveManager.getCoins() > cost + cost/2) {
            upgrade += 1;
            cost += cost / 2;
            GraphicsPanel.waveManager.subtractCoins(cost);
        }
    }
}
