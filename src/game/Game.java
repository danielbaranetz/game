package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import geometry.Point;
import geometry.Rectangle;
import primitives.Ball;

import java.awt.*;

public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Sleeper sleeper;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Counter remainingBlocks;
    private Counter remainingBalls;

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

    // Initialize a new game: create the Blocks and primitives.Ball (and game.Paddle)
    // and add them to the game.
    public void initialize() {
        this.gui = new GUI("Arknoid", WIDTH, HEIGHT);
        this.sleeper = new Sleeper();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();

        biuoop.KeyboardSensor keyboard = gui.getKeyboardSensor();

        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);


        geometry.Rectangle paddleRect = new geometry.Rectangle(new geometry.Point(350, 560), 100, 20);
        Paddle paddle = new Paddle(keyboard, paddleRect, 7);
        paddle.addToGame(this);

        Ball ball = new Ball(new geometry.Point(240, 300), 5, Color.RED);
        ball.setVelocity(5, 5);
        ball.setGameEnvironment(this.environment);
        ball.addToGame(this);
        this.remainingBalls.increase(1);

        Ball ball2 = new Ball(new geometry.Point(200,300), 5, Color.RED);
        ball2.setVelocity(5,5);
        ball2.setGameEnvironment(this.environment);
        ball2.addToGame(this);
        this.remainingBalls.increase(1);

        Ball ball3 = new Ball(new geometry.Point(220,300), 5, Color.RED);
        ball3.setVelocity(5,5);
        ball3.setGameEnvironment(this.environment);
        ball3.addToGame(this);
        this.remainingBalls.increase(1);

        Block topWall = new Block(new geometry.Rectangle(new geometry.Point(0, 0), 800, 20), Color.GRAY);
        topWall.addToGame(this);

        Block leftWall = new Block(new geometry.Rectangle(new geometry.Point(0, 20), 20, 600), Color.GRAY);
        leftWall.addToGame(this);

        Block rightWall = new Block(new geometry.Rectangle(new geometry.Point(780, 20), 20, 600), Color.GRAY);
        rightWall.addToGame(this);

        Block bottomWall = new Block(new geometry.Rectangle(new geometry.Point(0, 601), 800, 10), Color.GRAY);
        bottomWall.addToGame(this);
        bottomWall.addHitListener(ballRemover);

        for (int i = 0; i < 12; i++) {
            double x = 780 - 50 - (i * 50);
            Block b = new Block(new geometry.Rectangle(new geometry.Point(x, 100), 50, 20), Color.GRAY);
            b.addToGame(this);
            b.addHitListener(blockRemover);
            this.remainingBlocks.increase(1);
        }

        for (int i = 0; i < 11; i++) {
            double x = 780 - 50 - (i * 50);

            Block b = new Block(new geometry.Rectangle(new geometry.Point(x, 120), 50, 20), Color.RED);
            b.addToGame(this);
            b.addHitListener(blockRemover);
            this.remainingBlocks.increase(1);
        }
        for (int i = 0; i < 10; i++) {
            double x = 780 - 50 - (i * 50);

            Block b = new Block(new geometry.Rectangle(new geometry.Point(x, 140), 50, 20), Color.YELLOW);
            b.addToGame(this);
            b.addHitListener(blockRemover);
            this.remainingBlocks.increase(1);
        }
        for (int i = 0; i < 9; i++) {
            double x = 780 - 50 - (i * 50);

            Block b = new Block(new geometry.Rectangle(new geometry.Point(x, 160), 50, 20), Color.BLUE);
            b.addToGame(this);
            b.addHitListener(blockRemover);
            this.remainingBlocks.increase(1);
        }
        for (int i = 0; i < 8; i++) {
            double x = 780 - 50 - (i * 50);

            Block b = new Block(new geometry.Rectangle(new geometry.Point(x, 180), 50, 20), Color.PINK);
            b.addToGame(this);
            b.addHitListener(blockRemover);
            this.remainingBlocks.increase(1);
        }
        for (int i = 0; i < 7; i++) {
            double x = 780 - 50 - (i * 50);

            Block b = new Block(new Rectangle(new Point(x, 200), 50, 20), Color.GREEN);
            b.addToGame(this);
            b.addHitListener(blockRemover);
            this.remainingBlocks.increase(1);
        }

    }

    // Run the game -- start the animation loop.

    public void run() {
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (this.remainingBlocks.getValue() > 0 && this.remainingBalls.getValue() > 0) {
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
        this.gui.close();
    }

    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
}