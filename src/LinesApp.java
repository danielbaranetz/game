import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;
import java.util.Random;

public class LinesApp {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final int RADIUS = 3;
    private static final int LINES = 10;
    private static final Random RAND = new Random();

    public void run() {
        GUI gui = new GUI("Lines Example", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();

        // צור מערך של קווים רנדומליים
        Line[] lines = generateLines(LINES);

        // צייר קווים ונקודות אמצע
        for (Line l : lines) {
            drawLine(l, d, Color.BLACK);

            // משתמש רק בפונקציה שלך לבדיקה של middle
            Point middle = l.middle();
            d.setColor(Color.RED);
            d.fillCircle((int) middle.getX(), (int) middle.getY(), RADIUS);
        }

        // צייר נקודות חיתוך בין כל הקווים
        drawIntersections(lines, d);

        gui.show(d);

    }

    private Line generateRandomLine() {
        double x1 = RAND.nextInt(WIDTH);
        double y1 = RAND.nextInt(HEIGHT);
        double x2 = RAND.nextInt(WIDTH);
        double y2 = RAND.nextInt(HEIGHT);
        return new Line(x1, y1, x2, y2);
    }

    private Line[] generateLines(int count) {
        Line[] lines = new Line[count];
        for (int i = 0; i < count; i++) {
            lines[i] = generateRandomLine();
        }
        return lines;
    }

    private void drawLine(Line l, DrawSurface d, Color color) {
        d.setColor(color);
        d.drawLine(
                (int) l.start().getX(),
                (int) l.start().getY(),
                (int) l.end().getX(),
                (int) l.end().getY()
        );
    }

    private void drawIntersections(Line[] lines, DrawSurface d) {
        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                if (lines[i].isIntersecting(lines[j])) {
                    Point intersection = lines[i].intersectionWith(lines[j]);
                    if (intersection != null) {
                        d.setColor(Color.BLUE);
                        d.fillCircle((int) intersection.getX(),
                                (int) intersection.getY(), 3);
                    }
                }
            }
        }
    }
}
