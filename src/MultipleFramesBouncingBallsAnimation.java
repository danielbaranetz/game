import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.Random;

/**
 * משימה 3.4: אנימציה של כדורים קופצים בשתי מסגרות נפרדות.
 * חצי מהכדורים קופצים במסגרת אפורה, והחצי השני במסגרת צהובה.
 */
public class MultipleFramesBouncingBallsAnimation {

    // גודל החלון הראשי
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    // מסגרת 1 (אפורה): (50, 50) עד (500, 500)
    public static final int FRAME1_MIN_X = 50;
    public static final int FRAME1_MIN_Y = 50;
    public static final int FRAME1_MAX_X = 500;
    public static final int FRAME1_MAX_Y = 500;

    // מסגרת 2 (צהובה): (450, 450) עד (600, 600)
    public static final int FRAME2_MIN_X = 450;
    public static final int FRAME2_MIN_Y = 450;
    public static final int FRAME2_MAX_X = 600;
    public static final int FRAME2_MAX_Y = 600;

    public static void main(String[] args) {
        int numBalls = args.length;
        Ball[] balls = new Ball[numBalls];
        Random rand = new Random();

        // --- שלב 1: יצירת הכדורים ---
        for (int i = 0; i < numBalls; i++) {
            int radius = Integer.parseInt(args[i]);

            // הגבלה על גודל הרדיוס כדי למנוע באגים במסגרות קטנות
            if (radius > 40) {
                radius = 40;
            }

            int x, y;

            // לוגיקת מהירות: כדורים גדולים יותר איטיים יותר
            double speed;
            if (radius > 50) {
                speed = 1; // מהירות קבועה לכדורים מעל גודל 50
            } else {
                speed = 6.0 / radius; // מהירות הפוכה לגודל
            }
            double angle = rand.nextInt(360); // זווית התחלתית אקראית

            // --- חלוקת הכדורים לשתי מסגרות ---
            if (i < numBalls / 2) {
                // חצי ראשון: מסגרת 1 (אפורה)
                // יצירת מיקום אקראי שכולו בתוך מסגרת 1
                x = rand.nextInt(FRAME1_MAX_X - FRAME1_MIN_X - 2 * radius)
                        + FRAME1_MIN_X + radius;
                y = rand.nextInt(FRAME1_MAX_Y - FRAME1_MIN_Y - 2 * radius)
                        + FRAME1_MIN_Y + radius;
            } else {
                // חצי שני: מסגרת 2 (צהובה)
                // יצירת מיקום אקראי שכולו בתוך מסגרת 2
                x = rand.nextInt(FRAME2_MAX_X - FRAME2_MIN_X - 2 * radius)
                        + FRAME2_MIN_X + radius;
                y = rand.nextInt(FRAME2_MAX_Y - FRAME2_MIN_Y - 2 * radius)
                        + FRAME2_MIN_Y + radius;
            }
            // ------------------------------------

            Point center = new Point(x, y);
            Ball ball = new Ball(center, radius, Color.BLACK); // צבע הכדור שחור
            ball.setVelocity(Velocity.fromAngleAndSpeed(angle, speed));
            balls[i] = ball;
        }

        // --- שלב 2: הגדרת האנימציה ---
        GUI gui = new GUI("Multiple Frames Bouncing Balls", WIDTH, HEIGHT);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        // --- שלב 3: לולאת האנימציה הראשית ---
        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // 1. צבע רקע לבן (אופציונלי, מנקה פריימים קודמים)
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, WIDTH, HEIGHT);

            // 2. צייר את המסגרות
            // מסגרת 1 (אפורה)
            d.setColor(Color.GRAY);
            d.fillRectangle(FRAME1_MIN_X, FRAME1_MIN_Y,
                    FRAME1_MAX_X - FRAME1_MIN_X, FRAME1_MAX_Y - FRAME1_MIN_Y);

            // מסגרת 2 (צהובה)
            d.setColor(Color.YELLOW);
            d.fillRectangle(FRAME2_MIN_X, FRAME2_MIN_Y,
                    FRAME2_MAX_X - FRAME2_MIN_X, FRAME2_MAX_Y - FRAME2_MIN_Y);

            // 3. הזז וצייר את כל הכדורים
            for (int i = 0; i < balls.length; i++) {
                Ball ball = balls[i];

                // ספק את הגבולות הנכונים עבור כל כדור
                if (i < balls.length / 2) {
                    // כדור זה שייך למסגרת 1
                    ball.moveOneStep(FRAME1_MIN_X, FRAME1_MIN_Y, FRAME1_MAX_X, FRAME1_MAX_Y);
                } else {
                    // כדור זה שייך למסגרת 2
                    ball.moveOneStep(FRAME2_MIN_X, FRAME2_MIN_Y, FRAME2_MAX_X, FRAME2_MAX_Y);
                }

                ball.drawOn(d); // צייר את הכדור במיקומו החדש
            }

            // 4. הצג את הציור על המסך
            gui.show(d);
            sleeper.sleepFor(20); // המתן 20 מילישניות (כ-50 פריימים לשנייה)
        }
    }
}