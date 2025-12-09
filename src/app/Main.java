package app;

import geometry.Point;
import primitives.Ball;
import primitives.Velocity;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

//        LinesApp app = new LinesApp();
//        app.run();
        int numBalls = args.length;
        Ball[] balls = new Ball[numBalls];
        for (int i = 0; i < args.length; i++) {
            int radius = Integer.parseInt(args[i]);
            Random rand = new Random();
            int x = rand.nextInt(800 - 2 * radius) + radius;
            int y = rand.nextInt(600 - 2 * radius) + radius;
            Point center = new Point(x, y);
            double speed;
            if(radius > 50){
                speed = 1;
            } else{
                speed = 6.0 / radius;
            }
            double angle = rand.nextInt(360);
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            Ball ball = new Ball(center, radius, java.awt.Color.RED);
            ball.setVelocity(v);
            balls[i] = ball;

        }
        DrawAnimation app = new DrawAnimation();
        Point point = new Point(0,0);
        app.drawAnimation(point, 10, 10);

    }
}