package game;

import biuoop.DrawSurface;
import primitives.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class FinalFour implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(315, 6)); // מהירות 6 - מהר!
        velocities.add(Velocity.fromAngleAndSpeed(360, 6)); // ישר למעלה
        velocities.add(Velocity.fromAngleAndSpeed(45, 6));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10; // הפאדל חייב להיות מהיר כדי לתפוס 3 כדורים מהירים
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                // רקע כחול שמיים
                d.setColor(new Color(51, 153, 255));
                d.fillRectangle(0, 0, 800, 600);

                // --- ענן שמאלי ---
                d.setColor(Color.WHITE);
                for(int i=0; i<10; i++) {
                    d.drawLine(100 + i*10, 400, 100 + i*10 - 20, 600); // גשם
                }
                d.setColor(new Color(204, 204, 204)); // אפור בהיר
                d.fillCircle(100, 400, 25);
                d.fillCircle(120, 420, 28);
                d.setColor(new Color(180, 180, 180)); // אפור כהה יותר
                d.fillCircle(140, 390, 30);


                // --- ענן ימני ---
                d.setColor(Color.WHITE);
                for(int i=0; i<10; i++) {
                    d.drawLine(600 + i*10, 500, 600 + i*10 - 20, 600); // גשם
                }
                d.setColor(new Color(204, 204, 204));
                d.fillCircle(600, 500, 25);
                d.fillCircle(620, 520, 28);
                d.setColor(new Color(180, 180, 180));
                d.fillCircle(640, 490, 30);
            }

            @Override
            public void timePassed() {
            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        // 7 שורות מלאות
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        int blockHeight = 25;

        for (int i = 0; i < 7; i++) { // 7 שורות
            Color color = colors[i];
            for (int j = 0; j < 15; j++) { // 15 בלוקים בשורה (ממלא את המסך)
                // חישוב מיקום X (מתחיל מ-10 וקופץ ב-52)
                double x = 10 + (j * 52);
                double y = 100 + (i * blockHeight);

                Block b = new Block(new Rectangle(new Point(x, y), 52, blockHeight), color);
                blocks.add(b);
            }
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105; // 7 * 15
    }
}