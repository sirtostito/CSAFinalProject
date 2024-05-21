import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Character {
    private double moveAmt;
    private BufferedImage right;
    private boolean facingRight;
    private double xCoord;
    private double yCoord;
    private String sprite;

    public Character(String rightImg, String sprite) {
        try {
            right = ImageIO.read(new File(rightImg));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
