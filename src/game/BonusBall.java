package game;

import primitives.Ball;
import primitives.Velocity;
import geometry.Point;
import java.awt.Color;
import java.util.Random;

public class BonusBall implements HitListener {
    private Game game;
    private Counter remainingBalls;

    public BonusBall(Game game, Counter remainingBalls){
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        double blockX = beingHit.getCollisionRectangle().getUpperLeft().getX();
        double blockY = beingHit.getCollisionRectangle().getUpperLeft().getY();
        double blockHeight = beingHit.getCollisionRectangle().getHeight();
        double blockWidth = beingHit.getCollisionRectangle().getWidth();

        double newX = blockX + (blockWidth / 2);
        double newY = blockY + blockHeight + 5;

        Ball newBall = new Ball(new geometry.Point(newX, newY), 5, Color.RED);

        Random rand = new Random();
        Velocity v = Velocity.fromAngleAndSpeed(rand.nextInt(360), 5);
        newBall.setVelocity(v);
        newBall.setGameEnvironment(this.game.getEnvironment());
        newBall.addToGame(this.game);
        this.remainingBalls.increase(1);
    }
}