import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

public class CollisionTest {
    public static void main(String[] args) {
        // 1. הגדרת גודל המסך
        int screenWidth = 800;
        int screenHeight = 600;

        GUI gui = new GUI("Collision Test", screenWidth, screenHeight);
        Sleeper sleeper = new Sleeper();
        GameEnvironment environment = new GameEnvironment();

        // 2. יצירת "קירות" (בלוקים בגבולות המסך)
        int wallThickness = 20; // עובי הקיר

        // קיר עליון
        Block topWall = new Block(new Rectangle(new Point(0, 0), screenWidth, wallThickness));
        // קיר תחתון (שים לב לחישוב ה-Y)
        Block bottomWall = new Block(new Rectangle(new Point(0, screenHeight - wallThickness), screenWidth, wallThickness));
        // קיר שמאלי
        Block leftWall = new Block(new Rectangle(new Point(0, 0), wallThickness, screenHeight));
        // קיר ימני (שים לב לחישוב ה-X)
        Block rightWall = new Block(new Rectangle(new Point(screenWidth - wallThickness, 0), wallThickness, screenHeight));

        // הוספת הקירות לסביבה
        environment.addCollidable(topWall);
        environment.addCollidable(bottomWall);
        environment.addCollidable(leftWall);
        environment.addCollidable(rightWall);

        // 3. יצירת מכשולים באמצע המסך לבדיקה
        // בלוק שחור לבדיקת פגיעה מלמעלה/למטה
        Block centerBlock = new Block(new Rectangle(new Point(350, 250), 100, 30));
        environment.addCollidable(centerBlock);

        // בלוק כחול לבדיקת פגיעה מהצדדים
        Block sideBlock = new Block(new Rectangle(new Point(150, 150), 30, 100));
        environment.addCollidable(sideBlock);

        // 4. יצירת הכדור
        // מתחילים אותו באמצע כדי שלא יתקע בקיר על ההתחלה
        Ball ball = new Ball(new Point(400, 300), 10, Color.RED);
        // מהירות אלכסונית שתגרום לו לפגוע בכל הקירות
        ball.setVelocity(6, 6);
        ball.setGameEnvironment(environment);

        // 5. לולאת המשחק
        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // צביעת רקע
            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, screenWidth, screenHeight);

            // ציור הבלוקים (הנחה שהוספת להם drawOn)
            topWall.drawOn(d);
            bottomWall.drawOn(d);
            leftWall.drawOn(d);
            rightWall.drawOn(d);
            centerBlock.drawOn(d);
            sideBlock.drawOn(d);

            // הזזת הכדור וציורו
            ball.moveOneStep();
            ball.drawOn(d);

            gui.show(d);
            sleeper.sleepFor(50); // 20 פריימים לשנייה
        }
    }
}