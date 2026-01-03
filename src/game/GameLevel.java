package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import primitives.Ball;
import animations.Animation;
import animations.AnimationRunner;
import primitives.Velocity;

import java.awt.*;

public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;

    private LevelInformation levelInformation;
    private KeyboardSensor keyboard;

    public GameLevel(
            LevelInformation levelInformation,
            KeyboardSensor keyboard,
            AnimationRunner runner,
            Counter score
    ){
        this.levelInformation = levelInformation;
        this.keyboard = keyboard;
        this.runner = runner;
        this.score = score;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
    }
    public GameEnvironment getEnvironment(){
        return this.environment;
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
        this.sprites.addSprite(this.levelInformation.getBackground());


        Block topWall = new Block(new Rectangle(new Point(0, 20), 800, 20), Color.GRAY);
        topWall.addToGame(this);
        Block leftWall = new Block(new Rectangle(new Point(0, 20), 20, 600), Color.GRAY);
        leftWall.addToGame(this);
        Block rightWall = new Block(new Rectangle(new Point(780, 20), 20, 600), Color.GRAY);
        rightWall.addToGame(this);

        Block deathRegion = new Block(new Rectangle(new Point(0, 600), 800, 20), Color.GRAY);
        deathRegion.addToGame(this);

        BallRemover ballRemover = new BallRemover(this, this.remainingBalls);
        deathRegion.addHitListener(ballRemover);

// בתוך הלולאה שיוצרת כדורים:
        for(Velocity v: this.levelInformation.initialBallVelocities()){
            // תיקון סופי: מיקום 550.
            // זה קרוב מאוד לפאדל (שמתחיל ב-560) אבל לא נוגע בו,
            // ככה זה נראה כמו שיגור מושלם.
            Ball ball = new Ball(new Point(400, 550), 5, Color.WHITE);

            ball.setVelocity(v);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            this.remainingBalls.increase(1);
        }
        int paddleWidth = this.levelInformation.paddleWidth();
        int paddleSpeed = this.levelInformation.paddleSpeed();
        Point paddleStart = new Point(400 - paddleWidth / 2, 560);
        Paddle paddle = new Paddle(
                this.keyboard,
                new Rectangle(paddleStart, paddleWidth, 20),
                paddleSpeed);
        paddle.addToGame(this);

        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlocks);
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);

        for (Block b : this.levelInformation.blocks()) {
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreListener);
            this.remainingBlocks.increase(1);
        }

        // 6. הוספת תצוגת הניקוד ושם השלב
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);

        LevelNameIndicator levelname = new LevelNameIndicator(this.levelInformation.levelName());
        levelname.addToGame(this);
    }

    // Run the game -- start the animation loop.

    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
    }

    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        this.sprites.drawAllOn(d);

        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("פ") ) {
            this.runner.run(new PauseScreen(this.keyboard, this.sprites));
            this.runner.run(new CountdownAnimation(2, 3, this.sprites));

        }

        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }

        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
    }
    @Override
    public boolean shouldStop(){
        return !this.running;
    }
    public int getRemainingBalls() {
        return this.remainingBalls.getValue();
    }


}