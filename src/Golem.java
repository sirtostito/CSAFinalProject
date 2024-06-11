import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Golem extends Enemies {
    private double MAX_HP;
    private final double SPEED = 0.01;
    private final double DAMAGE;
    private double hp;
    private int wave;
    private Animation run;
    public Golem(String rightImg, String sprite, int wave) {
        super(rightImg, sprite, 100 + wave * wave/5.0, 0.01,800);
        this.wave = wave;
        MAX_HP = 100 + wave * wave/5.0;
        DAMAGE = 30 + wave * wave/5.0;
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
    }
    @Override
    public BufferedImage getEnemyImage(String action) {
        return run.getActiveFrame();
    }
    public String getHP() {
        return hp + " / " + MAX_HP;
    }

    @Override
    public int getxCoord() {
        if (super.isFacingRight()) {
            return (int) super.getxCoord() - 25;
        } else {
            return (int) (super.getxCoord() - 120 + (getEnemyImage("Run").getWidth()));
        }
    }

    @Override
    public int getyCoord() {
        return super.getyCoord() - 50;
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
    public void death() {
        super.despawn();
        GraphicsPanel.waveManager.removeEnemy(this);
    }
}
