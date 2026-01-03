package game;

import biuoop.KeyboardSensor;
import animations.AnimationRunner;
import java.util.List;

public class GameFlow {
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private Counter score;

    public GameFlow(AnimationRunner runner, KeyboardSensor keyboard){
        this.runner = runner;
        this.keyboard = keyboard;
        this.score = new Counter();
    }

    public void runLevels(List<LevelInformation> levels){
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.score);
            level.initialize();
            level.run();

            if(level.getRemainingBalls() == 0){
                break;
        }

        }    }
}
