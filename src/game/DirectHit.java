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

public class DirectHit implements LevelInformation{

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(315,5));
        velocities.add(Velocity.fromAngleAndSpeed(45,5));

        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 85;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                // רקע ירוק כהה
                d.setColor(new Color(25, 100, 25)); // RGB לירוק ספציפי
                d.fillRectangle(0, 0, 800, 600);

                // --- ציור הבניין ---

                // בסיס הבניין
                d.setColor(new Color(45, 45, 45)); // אפור כהה
                d.fillRectangle(65, 450, 100, 150);

                // חלונות (רשת לבנה/צהובה)
                d.setColor(Color.WHITE);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        d.fillRectangle(75 + (j * 18), 460 + (i * 30), 10, 25);
                    }
                }

                // החלק האמצעי של הבניין
                d.setColor(new Color(60, 60, 60));
                d.fillRectangle(100, 400, 30, 50);

                // העמוד הדק למעלה (אנטנה)
                d.setColor(new Color(75, 75, 75));
                d.fillRectangle(110, 200, 10, 200);

                // נורה אדומה בקצה האנטנה
                d.setColor(new Color(216, 172, 50)); // זהב/כתום
                d.fillCircle(115, 200, 12);
                d.setColor(Color.RED);
                d.fillCircle(115, 200, 8);
                d.setColor(Color.WHITE);
                d.fillCircle(115, 200, 3);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int[] rows = {6, 7, 8, 9, 10};
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        int blockHeight = 25;
        int blockWidth = 50;
        for (int i = 0; i < 5; i++){
            Color color = colors[i];
            for (int j = 0; j < rows[j]; j++){
                double x = 800 - 20 - blockWidth - (j * blockWidth);
                double y = 150 + (i * blockHeight);
                Block b = new Block(new Rectangle(new Point(x,y), blockWidth, blockHeight), color);
                blocks.add(b);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
