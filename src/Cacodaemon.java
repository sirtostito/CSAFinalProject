import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Cacodaemon extends Enemies {
    private double MAX_HP;
    private final double SPEED = 0.05;
    private final double DAMAGE;
    private double hp;
    private int wave;
    private Animation run;
    public Cacodaemon(String rightImg, String sprite, int wave) {
        super(rightImg, sprite, 50 + wave * wave/5.0, 0.05,500);
        this.wave = wave;
        MAX_HP = 75 + wave * wave/5.0;
        DAMAGE = 25 + wave * wave/5.0;
        hp = MAX_HP;
        ArrayList<BufferedImage> run_animation = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            String filename = "src/Assets/" + sprite + "/Run/" + sprite + "Run" + i + ".png";
            try {
                run_animation.add(ImageIO.read(new File(filename)));
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        run = new Animation(run_animation,66);
    }

    @Override
    public BufferedImage getEnemyImage(String action) {
        return run.getActiveFrame();
    }
    public String getHP() {
        return hp + " / " + MAX_HP;
    }

    public void death() {
        super.despawn();
        GraphicsPanel.waveManager.removeEnemy(this);
    }

    public void damaged(double damage) {
        if (super.getHp() > 0) {
            super.hurt(damage);
            if (super.getHp() <= 0) {
                super.setHp(0);
                GraphicsPanel.waveManager.addCoins(super.getDrop());
                death();
            }
        }
    }

    @Override
    public int getyCoord() {
        return super.getyCoord() - 40;
    }
}
