import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class NightBorne extends Enemies {
    private double MAX_HP;
    private final double SPEED = 0.2;
    private final double DAMAGE;
    private double hp;
    private int wave;
    private Animation run;
    private Animation death;
    public NightBorne(String rightImg, String sprite, int wave) {
        super(rightImg, sprite, 20 + wave * wave/5.0, 0.08,600);
        this.wave = wave;
        MAX_HP = 90 + wave * wave/5.0;
        DAMAGE = 30 + wave * wave/5.0;
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
        ArrayList<BufferedImage> death_animation = new ArrayList<>();
        for (int i = 1; i <= 23; i++) {
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
    public String getHP() {
        return hp + " / " + MAX_HP;
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
        death.resetFrames();
        for (int i = 1; i <= 23; i++) {
            getEnemyImage("Death");
        }
        super.despawn();
        GraphicsPanel.waveManager.removeEnemy(this);
    }

    @Override
    public int getxCoord() {
        if (super.isFacingRight()) {
            return (int) super.getxCoord() - 30;
        } else {
            return (int) (super.getxCoord() - 20);
        }
    }
    @Override
    public int getyCoord() {
        return super.getyCoord() - 45;
    }
}
