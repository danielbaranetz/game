package app;

import biuoop.GUI;
import animations.AnimationRunner;
import game.*;
import java.util.ArrayList;
import java.util.List;

public class Ass2Game {
    public static void main(String[] args) {
        GUI gui = new GUI("Arknoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui);

        GameFlow gameFlow = new GameFlow(runner, gui.getKeyboardSensor());
        List<LevelInformation> levels = new ArrayList<>();
        levels.add(new DirectHit());
        levels.add(new WideEasy());
        levels.add(new Green3());
        levels.add(new FinalFour());

        gameFlow.runLevels(levels);

        gui.close();
    }

}