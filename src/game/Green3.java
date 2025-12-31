package game;

import biuoop.DrawSurface;
import primitives.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
public class Green3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0,5));
        return List.of();
    }

    @Override
    public int paddleSpeed() {
        return 0;
    }

    @Override
    public int paddleWidth() {
        return 0;
    }

    @Override
    public String levelName() {
        return "";
    }

    @Override
    public Sprite getBackground() {
        return null;
    }

    @Override
    public List<Block> blocks() {
        return List.of();
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 0;
    }
}
