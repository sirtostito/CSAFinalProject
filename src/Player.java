import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player {
    private final double MOVE_AMT = 0.3;
    private BufferedImage right;
    private boolean facingRight;
    private double xCoord;
    private double yCoord;
    private int score;
    private String name;
    private Animation idle;
    private Animation run;
    private Animation jump;
    private Animation roll;
    private Animation attack;
    private Animation death;
    private Animation special;
    private String sprite;
    private boolean stun;

    public Player(String rightImg, String name, String sprite) {
        this.name = name;
        facingRight = true;
        xCoord = 50; // starting position is (50, 435), right on top of ground
        yCoord = 435;
        score = 0;
        stun = false;
        this.sprite = sprite;
        try {
            right = ImageIO.read(new File(rightImg));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //The code below is used to programatically create an ArrayList of BufferedImages to use for an Animation object
        //By creating all the BufferedImages beforehand, we don't have to worry about lagging trying to read image files during gameplay
        ArrayList<BufferedImage> idle_animation = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            String filename = "src/Assets/" + sprite + "/Idle/" + sprite + "Idle" + i + ".png";
            try {
                idle_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        idle = new Animation(idle_animation,66);

        ArrayList<BufferedImage> run_animation = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
            String filename = "src/Assets/" + sprite + "/Run/" + sprite + "Run" + i + ".png";
            try {
                run_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        run = new Animation(run_animation,66);

        ArrayList<BufferedImage> attack1_animation = new ArrayList<>();
        for (int i = 1; i <= 22; i++) {
            String filename = "src/Assets/" + sprite + "/Attack/" + sprite + "Attack" + i + ".png";
            try {
                attack1_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        attack = new Animation(attack1_animation,66);

        ArrayList<BufferedImage> death_animation = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            String filename = "src/Assets/" + sprite + "/Death/" + sprite + "_Death" + i + ".png";
            try {
                death_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        death = new Animation(death_animation,66);

        ArrayList<BufferedImage> jump_animation = new ArrayList<>();
        for (int i = 1; i <= 14; i++) {
            String filename = "src/Assets/" + sprite + "/Jump/" + sprite + "Jump" + i + ".png";
            try {
                jump_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        jump = new Animation(jump_animation,66);

        ArrayList<BufferedImage> special_animation = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            String filename = "src/Assets/" + sprite + "/Special/" + sprite + "Special" + i + ".png";
            try {
                special_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        special = new Animation(special_animation,66);
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

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    public void faceRight() {
        facingRight = true;
    }

    public void faceLeft() {
        facingRight = false;
    }

    public void moveRight() {
        if (xCoord + MOVE_AMT <= 920) {
            xCoord += MOVE_AMT;
        }
    }

    public void moveLeft() {
        if (xCoord - MOVE_AMT >= 0) {
            xCoord -= MOVE_AMT;
        }
    }

    public void moveUp() {
        if (yCoord - MOVE_AMT >= 0) {
            yCoord -= MOVE_AMT;
        }
    }

    public void moveDown() {
        if (yCoord + MOVE_AMT <= 435) {
            yCoord += MOVE_AMT;
        }
    }

    public void turn() {
        if (facingRight) {
            faceLeft();
        } else {
            faceRight();
        }
    }

    public void collectCoin() {
        score++;
    }

    public BufferedImage getPlayerImage(String action) {
        return switch (action) {
            case "Idle" -> idle.getActiveFrame();
            case "Run" -> run.getActiveFrame();
            case "Attack" -> attack.getActiveFrame();
            case "Jump" -> jump.getActiveFrame();
            case "Death" -> death.getActiveFrame();
            default -> special.getActiveFrame();
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
}
