package game;

import animations.Animation;
import biuoop.DrawSurface;

public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private long durationMillis;
    private long startTime;
    private boolean isRunning;

    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.durationMillis = (long) (numOfSeconds * 1000);
        this.isRunning = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // אתחול זמן ההתחלה רק בריצה הראשונה של הפונקציה
        if (!this.isRunning) {
            this.startTime = System.currentTimeMillis();
            this.isRunning = true;
        }

        long currTime = System.currentTimeMillis();
        long usedTime = currTime - this.startTime;
        long timeLeft = this.durationMillis - usedTime;

        // 1. ציור הרקע (המשחק הקפוא)
        d.setColor(java.awt.Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        this.gameScreen.drawAllOn(d);

        // 2. תנאי עצירה - הכי חשוב!
        if (timeLeft <= 0) {
            this.stop = true; // <--- זה מה שמשחרר את המשחק להתחיל!
        }

        // 3. חישוב המספר
        double portion = (double) timeLeft / this.durationMillis;
        int count = (int) (portion * this.countFrom) + 1;

        if (count > this.countFrom) {
            count = this.countFrom;
        }

        // 4. ציור המספר (רק אם לא סיימנו)
// ... (אחרי חישוב המשתנה count) ...

        if (!this.stop) {
            String countText = Integer.toString(count);
            int textSize = 60;
            // מיקום הטקסט
            int x = d.getWidth() / 2;
            int y = d.getHeight() / 2 + 100;

            // 1. יצירת ה"מסגרת" - ציור הטקסט בשחור בהזזות קטנות
            d.setColor(java.awt.Color.BLACK);
            d.drawText(x - 2, y, countText, textSize); // קצת שמאלה
            d.drawText(x + 2, y, countText, textSize); // קצת ימינה
            d.drawText(x, y - 2, countText, textSize); // קצת למעלה
            d.drawText(x, y + 2, countText, textSize); // קצת למטה

            // אפשר להוסיף גם אלכסונים למסגרת עבה יותר (אופציונלי)
            d.drawText(x - 2, y - 2, countText, textSize);
            d.drawText(x + 2, y + 2, countText, textSize);

            // 2. ציור הטקסט הלבן המקורי מעל הכל
            d.setColor(java.awt.Color.WHITE); // או הצבע המקורי שבחרת (למשל צבע האנטנה של הלוויין)
            d.drawText(x, y, countText, textSize);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}