public class Cacodaemon extends Enemies {
    private double MAX_HP;
    private final double SPEED = 0.05;
    private final double DAMAGE;
    private double hp;
    private int wave;
    private Animation Run;
    public Cacodaemon(String rightImg, String sprite, int wave) {
        super(rightImg, sprite);
        this.wave = wave;
        MAX_HP = 50 + wave * wave/5.0;
        DAMAGE = 25 + wave * wave/5.0;
        hp = MAX_HP;
        start();
    }

    public String getHP() {
        return hp + " / " + MAX_HP;
    }

    public void death() {
        super.death(8);
    }
}
