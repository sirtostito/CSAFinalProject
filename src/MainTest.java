public class MainTest {
    public static void main(String[] args) {
        WaveManager waveManagerTest = new WaveManager();
        System.out.println(waveManagerTest.getWave());
        for (int i = 0; i < waveManagerTest.getEnemies().size(); i++) {
            System.out.println(waveManagerTest.getEnemies().get(i) + " " + (i + 1));
        }
    }
}
