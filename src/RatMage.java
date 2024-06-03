public class RatMage extends Enemies {
    private double MAX_HP;
    private final double SPEED = 0.05;
    private final double DAMAGE;
    private double hp;
    private int wave;
    private Animation Run;
    public RatMage(String rightImg, String sprite, int wave) {
        super(rightImg, sprite);
        this.wave = wave;
        MAX_HP = 9 + wave * wave/5.0;
        DAMAGE = 5 + wave * wave/5.0;
        hp = MAX_HP;
        start();
    }

    public String getHP() {
        return hp + " / " + MAX_HP;
    }

    @Override
    public void start() {
        super.start();
        if (hp <= 0) {
            super.death(5);
        }
    }
}
