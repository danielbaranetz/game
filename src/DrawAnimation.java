import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

public class DrawAnimation {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title",WIDTH,HEIGHT);
        Sleeper sleeper = new Sleeper();
        Point point = new Point(start.getX(), start.getY());
        Ball ball = new Ball(point, 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
