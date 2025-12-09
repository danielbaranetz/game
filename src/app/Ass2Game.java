package app;

import game.Block;
import game.Game;
import geometry.Point;
import geometry.Rectangle;

import java.awt.*;

public class Ass2Game {
    public static void main(String[] args) {
        Block centerBlock = new Block(new Rectangle(new Point(20, 250), 100, 30), Color.GRAY);
        Game game = new Game();
        PrintingHitListener printer = new PrintingHitListener();
        centerBlock.addHitListener(printer);
        game.initialize();
        game.run();
    }
}