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
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN};
        int blockY = 250;
        int blockWidth = 50;

        for(int i = 0; i < 15; i++){
            Color color;
            if(i < colors.length){
                color = colors[i];
            } else {
                color = Color.CYAN;
            }
            Block b = new Block(new Rectangle(new Point(10 + (i * 52), blockY), 52, 25), color);
            blocks.add(b);
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
