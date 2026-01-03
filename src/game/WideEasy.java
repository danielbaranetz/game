package game;


import primitives.Velocity;
import biuoop.DrawSurface;
import primitives.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.util.List;

public class WideEasy implements LevelInformation{

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            double angle = 300 + (i * 12);
            if (i >= 5) angle += 10;

            velocities.add(Velocity.fromAngleAndSpeed(angle, 5));
        }        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(new Color(239, 231, 176));
                d.fillCircle(150,150, 60);
                for (int i = 0; i < 100; i++) {
                    d.drawLine(150, 150, 20 + (i * 7), 250);
                }

                d.setColor(new Color(236, 215, 73));
                d.fillCircle(150, 150, 50);

                d.setColor(new Color(255, 225, 24));
                d.fillCircle(150, 150, 40);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        Color[] colors = {
                Color.RED, Color.RED,
                Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW,
                Color.GREEN, Color.GREEN, Color.GREEN,
                Color.BLUE, Color.BLUE,
                Color.PINK, Color.PINK,
                Color.CYAN, Color.CYAN
        };

        int blockY = 250;
        int blockWidth = 50; // רוחב כל בלוק

        for (int i = 0; i < colors.length; i++) {
            // חישוב חדש: מתחילים מ-25, וקופצים ב-50 בדיוק (בלי רווחים מיותרים)
            double x = 25 + (i * blockWidth);

            Block b = new Block(new Rectangle(new Point(x, blockY), blockWidth, 25), colors[i]);
            blocks.add(b);
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
