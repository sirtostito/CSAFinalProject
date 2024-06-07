public class Golem extends Enemies {
    private double MAX_HP;
    private final double SPEED = 0.01;
    private final double DAMAGE;
    private double hp;
    private int wave;
    private Animation Run;
    public Golem(String rightImg, String sprite, int wave) {
        super(rightImg, sprite);
        this.wave = wave;
        MAX_HP = 100 + wave * wave/5.0;
        DAMAGE = 30 + wave * wave/5.0;
        hp = MAX_HP;
        start();
    }
    @Override
    public BufferedImage getEnemyImage(String action) {
        if (action.equals("Run")) {
            return Run.getActiveFrame();
        } else {
            return death.getActiveFrame();
        }
    }
    public String getHP() {
        return hp + " / " + MAX_HP;
    }

    public void death() {
        super.death(24);
    }
}
