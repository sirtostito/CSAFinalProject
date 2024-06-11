import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Knight extends Allies {
    private Animation idle;
    private Animation attackOne;
    private Animation attackTwo;
    private Animation attackThree;
    private String sprite;
    private double damage;
    public Knight(double x, double y) {
        super(x,y,"src/Assets/Knight/Idle/KnightIdle1.png","Knight",1000);
        this.sprite = "Knight";
        damage = 10 * getUpgrade();
        ArrayList<BufferedImage> idle_animation = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            String filename = "src/Assets/" + sprite + "/Idle/" + sprite + "Idle" + i + ".png";
            try {
                idle_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage() + " " + sprite + " idle");
            }
        }
        idle = new Animation(idle_animation,66);
        ArrayList<BufferedImage> attackOne_animation = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            String filename = "src/Assets/" + sprite + "/AttackOne/" + sprite + "AttackOne" + i + ".png";
            try {
                attackOne_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage() + " " + sprite + " attack one");
            }
        }
        attackOne = new Animation(attackOne_animation,66);
        ArrayList<BufferedImage> attackTwo_animation = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            String filename = "src/Assets/" + sprite + "/AttackTwo/" + sprite + "AttackTwo" + i + ".png";
            try {
                attackTwo_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage() + " " + sprite + " attack two");
            }
        }
        attackTwo = new Animation(attackTwo_animation,66);
        ArrayList<BufferedImage> attackThree_animation = new ArrayList<>();
        for (int i = 1; i <= 22; i++) {
            String filename = "src/Assets/" + sprite + "/AttackThree/" + sprite + "AttackThree" + i + ".png";
            try {
                attackThree_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage() + " " + sprite + " attack three");
            }
        }
        attackThree = new Animation(attackThree_animation,66);
    }
    public BufferedImage getPlayerImage(String action) {
        return switch (action) {
            case "Idle" -> idle.getActiveFrame();
            case "AttackOne" -> attackOne.getActiveFrame();
            case "AttackTwo" -> attackTwo.getActiveFrame();
            case "AttackThree" -> attackThree.getActiveFrame();
            default -> throw new IllegalStateException("Unexpected value: " + action);
        };
    }
    public void attackRefresh() {
        if (!isEndlag()) {
            if (getUpgrade() == 1) {
                attackOne.resetFrames();
            } else if (getUpgrade() == 2) {
                attackTwo.resetFrames();
            } else if (getUpgrade() == 3) {
                attackThree.resetFrames();
            }
            setEndlag(true);
        }
    }
    public BufferedImage attack() {
        setEndlag(true);
        if (getUpgrade() == 1) {
            if (!(attackOne.getCurrentFrame() < 8)) { setEndlag(false); }
            return getPlayerImage("AttackOne");
        } else if (getUpgrade() == 2) {
            if (!(attackTwo.getCurrentFrame() < 12)) { setEndlag(false); }
            return getPlayerImage("AttackTwo");
        } else if (getUpgrade() == 3) {
            if (!(attackThree.getCurrentFrame() < 21)) { setEndlag(false); }
            return getPlayerImage("AttackThree");
        }
        return getPlayerImage("Idle");
    }
    public double getDamage() {
        return damage;
    }
    public Rectangle upgradeRect() {
        int imageHeight = getPlayerImage("Idle").getHeight();
        int imageWidth = getPlayerImage("Idle").getWidth();
        return new Rectangle(getxCoord(), getyCoord(), imageWidth,imageHeight);
    }
}
