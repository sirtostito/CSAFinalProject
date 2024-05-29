import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Gem {
    private BufferedImage gem;
    private Animation gemAnim;
    private double x;
    private double y;

    public Gem() {
        x = 50;
        y = 50;
        try {
            gem = ImageIO.read(new File("src/Assets/download.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ArrayList<BufferedImage> idle_animation = new ArrayList<>();
        for (int i = 0; i <= 12; i++) {
            String filename = "src/Assets/diamond/download (" + i + ").png";
            try {
                idle_animation.add(ImageIO.read(new File(filename)));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        gemAnim = new Animation(idle_animation, 66);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return gem.getWidth();
    }

    public int getHeight() {
        return gem.getHeight();
    }

    public BufferedImage getGem() {
        return gem;
    }

    public Rectangle hitBox() {
        int imgW = getWidth();
        int imgH = getHeight();
        Rectangle rect = new Rectangle((int) x,(int) y, imgW, imgH);
        return rect;
    }

}
