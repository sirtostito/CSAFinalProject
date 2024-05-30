public class RatMage extends Enemies {
    private double MAX_HP = 9 + wave * wave/5.0;
    private final double SPEED = 0.05;
    private final int DAMAGE = 5 + wave * wave/5;
    private double hp;
    private int wave;
    private Animation Run;
    public RatMage(String rightImg, String sprite, int wave) {
        super(rightImg, sprite);
        this.wave = wave;
        hp = MAX_HP;
        start();
    }

    public String getHP() {
        return hp + " / " + MAX_HP;
    }

    private void start() {
        while (hp > 0) {

        }
    }

    public void damaged(double damage) {
        hp -= damage;
        if (hp <= 0) {
            death();
        }
    }

    private void death() {
        WaveManager.enemies.remove(this);
    }
}
