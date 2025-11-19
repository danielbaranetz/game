import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    public Game(){
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
    }

    public void addCollidable(Collidable c){
        this.environment.addCollidable(c);
    }
    public void addSprite(Sprite s){
        this.sprites.addSprite(s);

    }

    // Initialize a new game: create the Blocks and Ball (and Paddle)
    // and add them to the game.
    public void initialize() {
        this.gui = new GUI("Arkanoid", WIDTH, HEIGHT);
        this.sleeper = new Sleeper();

        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();

        Rectangle paddleRect = new Rectangle(new Point(350, 560), 100, 20);
        Paddle paddle = new Paddle(keyboard, paddleRect, 5);
        paddle.addToGame(this);
        Ball ball = new Ball(new Point(400, 300), 5, java.awt.Color.RED);
        ball.setVelocity(5, 5);
        ball.setGameEnvironment(this.environment);

        ball.addToGame(this);

        Block topWall = new Block(new Rectangle(new Point(0, 0), 800, 20), Color.GRAY);
        topWall.addToGame(this);

        Block leftWall = new Block(new Rectangle(new Point(0, 20), 20, 600), Color.GRAY);
        leftWall.addToGame(this);

        Block rightWall = new Block(new Rectangle(new Point(780, 20), 20, 600), Color.GRAY);
        rightWall.addToGame(this);

        Block bottomWall = new Block(new Rectangle(new Point(0, 580), 800, 20), Color.GRAY);
        bottomWall.addToGame(this);

        for (int i = 0; i < 12; i++) {
            double x = 780 - 50 - (i * 50);
            Block b = new Block(new Rectangle(new Point(x, 100), 50, 20), Color.GRAY);
            b.addToGame(this);
        }

        for (int i = 0; i < 11; i++) {
            double x = 780 - 50 - (i * 50);

            Block b = new Block(new Rectangle(new Point(x, 120), 50, 20), Color.RED);
            b.addToGame(this);
        }
        for (int i = 0; i < 10; i++) {
            double x = 780 - 50 - (i * 50);

            Block b = new Block(new Rectangle(new Point(x, 140), 50, 20), Color.YELLOW);
            b.addToGame(this);
        }
        for (int i = 0; i < 9; i++) {
            double x = 780 - 50 - (i * 50);

            Block b = new Block(new Rectangle(new Point(x, 160), 50, 20), Color.BLUE);
            b.addToGame(this);
        }
        for (int i = 0; i < 8; i++) {
            double x = 780 - 50 - (i * 50);

            Block b = new Block(new Rectangle(new Point(x, 180), 50, 20), Color.PINK);
            b.addToGame(this);
        }
        for (int i = 0; i < 7; i++) {
            double x = 780 - 50 - (i * 50);

            Block b = new Block(new Rectangle(new Point(x, 200), 50, 20), Color.GREEN);
            b.addToGame(this);
        }
    }

    // Run the game -- start the animation loop.

    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (true) {
            long startTime = System.currentTimeMillis();

            DrawSurface d = gui.getDrawSurface();

            d.setColor(java.awt.Color.BLUE);
            d.fillRectangle(0, 0, 800, 600);

            this.sprites.drawAllOn(d);

            gui.show(d);

            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;

            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}