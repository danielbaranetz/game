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
        if (!this.stop) {
            d.setColor(java.awt.Color.WHITE);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2 + 100, Integer.toString(count), 60);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}