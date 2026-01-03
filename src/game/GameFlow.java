package game;

import biuoop.KeyboardSensor;
import animations.AnimationRunner;
import java.util.List;

public class GameFlow {
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private Counter score;
    private Counter lives;

    public GameFlow(AnimationRunner runner, KeyboardSensor keyboard) {
        this.runner = runner;
        this.keyboard = keyboard;
        this.score = new Counter();
        this.lives = new Counter();
        this.lives.increase(7);
    }

    public void runLevels(List<LevelInformation> levels) {
        boolean hasWon = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.score, this.lives);

            level.initialize();
            level.run();

            if (this.lives.getValue() == 0) {
                hasWon = false;
                break;
            }
        }
        EndScreen endScreen = new EndScreen(this.keyboard, this.score.getValue(), hasWon);
        this.runner.run(endScreen);
    }
}
