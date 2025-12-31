package game;

import primitives.Ball;

public class BallRemover implements HitListener{
    private GameLevel game;
    private Counter remainingBalls;

    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
