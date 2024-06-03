public class RatAxe extends Enemies{
    private double MAX_HP;
    private final double SPEED = 0.02;
    private final double DAMAGE;
    private double hp;
    private int wave;

    private Animation Run;
    public RatAxe(String rightImg, String sprite, int wave) {
        super(rightImg, sprite);
        this.wave = wave;
        MAX_HP = 15 + wave * wave/5.0;
        DAMAGE = 6 + wave * wave/5.0;
        hp = MAX_HP;
    }

    public void death() {
        super.death(5);
    }

    public String getHP() {
        return hp + " / " + MAX_HP;
    }
}
