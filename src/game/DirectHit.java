package game;

import biuoop.DrawSurface;
import primitives.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class DirectHit implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 5)); // כדור אחד ישר למעלה
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 80;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, 800, 600);

                // ציור המטרה
                d.setColor(Color.RED);
                d.drawCircle(400, 162, 60);
                d.drawCircle(400, 162, 90);
                d.drawCircle(400, 162, 120);
                d.drawLine(400, 162 - 140, 400, 162 + 140);
                d.drawLine(400 - 140, 162, 400 + 140, 162);
            }

            @Override
            public void timePassed() {
            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        // בלוק בודד במרכז המטרה
        blocks.add(new Block(new Rectangle(new Point(385, 147), 30, 30), Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}