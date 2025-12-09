package app;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import geometry.Point;
import primitives.Ball;

import java.awt.Color; // הוספתי import

public class DrawAnimation {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Bouncing primitives.Ball Animation", WIDTH, HEIGHT); // שם חלון ברור יותר
        Sleeper sleeper = new Sleeper();

        // 3. תיקון קטן: אין צורך ליצור 'point' חדש, אפשר להשתמש ב-'start' ישירות
        Ball ball = new Ball(start, 30, java.awt.Color.BLACK);

        // 1. תיקון: השתמש ב-dx ו-dy שהפונקציה קיבלה
        ball.setVelocity(dx, dy);

        while (true) {
            // 2. תיקון: קרא למתודה החדשה עם גבולות החלון
            ball.moveOneStep();

            DrawSurface d = gui.getDrawSurface();

            // 4. שיפור: נקה את המסך (צייר רקע לבן)
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, WIDTH, HEIGHT);

            // צייר את הכדור במיקומו החדש
            ball.drawOn(d);

            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}