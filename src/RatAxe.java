import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RatAxe extends Enemies{
    private double MAX_HP;
    private final double SPEED = 0.02;
    private final double DAMAGE;
    private double hp;
    private int wave;
    private Animation death;
    private Animation run;
    public RatAxe(String rightImg, String sprite, int wave) {
        super(rightImg, sprite);
        this.wave = wave;
        MAX_HP = 15 + wave * wave/5.0;
        DAMAGE = 6 + wave * wave/5.0;
        hp = MAX_HP;
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
        ArrayList<BufferedImage> death_animation = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String filename = "src/Assets/" + sprite + "/Death/" + sprite + "Death" + i + ".png";
            try {
                death_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        death = new Animation(death_animation,66);
    }
    @Override
    public BufferedImage getEnemyImage(String action) {
        if (action.equals("Run")) {
            return run.getActiveFrame();
        } else {
            return death.getActiveFrame();
        }
    }
    public void death() {
        super.death(5);
    }

    public String getHP() {
        return hp + " / " + MAX_HP;
    }
}
