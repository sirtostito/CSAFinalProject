import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Jones extends Allies {
    private Animation idle;
    private Animation attackOne;
    private Animation attackTwo;
    private Animation attackThree;
    private String sprite;
    private double damage;
    public Jones(double x, double y) {
        super(x,y,"src/Assets/Jones/Idle/JonesIdle1.png","Jones",500);
        this.sprite = "Jones";
        damage = 5 * getUpgrade();
        ArrayList<BufferedImage> idle_animation = new ArrayList<>();
        for (int i = 1; i <= 8; i++) {
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
        for (int i = 1; i <= 6; i++) {
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
        for (int i = 1; i <= 8; i++) {
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
        for (int i = 1; i <= 4; i++) {
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
            if (!(attackOne.getCurrentFrame() < 5)) { setEndlag(false); }
            return getPlayerImage("AttackOne");
        } else if (getUpgrade() == 2) {
            if (!(attackTwo.getCurrentFrame() < 7)) { setEndlag(false); }
            return getPlayerImage("AttackTwo");
        } else if (getUpgrade() == 3) {
            if (!(attackThree.getCurrentFrame() < 3)) { setEndlag(false); }
            return getPlayerImage("AttackThree");
        }
        return getPlayerImage("Idle");
    }
    public double getDamage() {
        return damage;
    }
    public Rectangle playerRect() {
        int imageHeight = getPlayerImage("Idle").getHeight();
        int imageWidth = getPlayerImage("Idle").getWidth();
        return new Rectangle(getxCoord() - 30, getyCoord() + 20, imageWidth + 150, imageHeight + 20);
    }
    public Rectangle upgradeRect() {
        int imageHeight = getPlayerImage("Idle").getHeight();
        int imageWidth = getPlayerImage("Idle").getWidth();
        return new Rectangle(getxCoord() + 20, getyCoord() + 31, imageWidth,imageHeight);
    }
}
