package app;

import game.GameLevel;

public class Ass2Game {
    public static void main(String[] args) {
        GameLevel game = new GameLevel();
        game.initialize();
        game.run();
    }
}