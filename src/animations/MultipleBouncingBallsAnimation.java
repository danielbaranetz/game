package animations;

import biuoop.GUI;
import biuoop.DrawSurface;
import geometry.Point;
import primitives.Ball;
import primitives.Velocity;

import java.awt.Color;
import java.util.Random;

public class MultipleBouncingBallsAnimation {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static void main(String[] args) {

        int numBalls = args.length;
        Ball[] balls = new Ball[numBalls];
        Random rand = new Random();

        for (int i = 0; i < numBalls; i++) {
            int radius = Integer.parseInt(args[i]);
            int x = rand.nextInt(WIDTH - 2 * radius) + radius;
            int y = rand.nextInt(HEIGHT - 2 * radius) + radius;
            Point center = new Point(x, y);

            double speed;
            if (radius > 50) {
                speed = 1; // מהירות איטית קבועה לגדולים
            } else {
                speed = 6.0 / radius; // מהירות גבוהה לקטנים
            }
            double angle = rand.nextInt(360);

            Ball ball = new Ball(center, radius, Color.BLACK);
            ball.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
            balls[i] = ball;
        }

        GUI gui = new GUI("Multiple Bouncing Balls", 800, 600);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, 800, 600);

            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(20);
        }
    }
}
