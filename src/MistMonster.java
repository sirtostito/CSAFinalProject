public class MistMonster extends Enemies{
    private double MAX_HP;
    private final double SPEED = 0.05;
    private final double DAMAGE;
    private double hp;
    private int wave;

    private Animation Run;
    public MistMonster(String rightImg, String sprite, int wave) {
        super(rightImg, sprite);
        this.wave = wave;
        MAX_HP = 15 + wave * wave/5.0;
        DAMAGE = 6 + wave * wave/5.0;
        hp = MAX_HP;
    }

    private void start() {
        while (hp > 0) {

        }
    }

    public String getHP() {
        return hp + " / " + MAX_HP;
    }

    public void damaged(double damage) {
        hp -= damage;
        if (hp <= 0) {

        }
    }

    private void death() {
        WaveManager.enemies.remove(this);
    }
}
