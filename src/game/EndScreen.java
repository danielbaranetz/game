package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import animations.Animation;
import java.awt.Color;

public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;
    private boolean isWin;

    public EndScreen(KeyboardSensor k, int score, boolean isWin) {
        this.keyboard = k;
        this.score = score;
        this.isWin = isWin;
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.isWin) {
            d.setColor(new Color(0, 150, 0));
        } else {
            d.setColor(new Color(150, 0, 0));
        }
        d.fillRectangle(0, 0, 800, 600);

        d.setColor(Color.WHITE);
        String message;
        if (this.isWin) {
            message = "You Win! Your score is " + this.score;
        } else {
            message = "Game Over. Your score is " + this.score;
        }

        d.drawText(150, 300, message, 32);

        d.drawText(250, 450, "Press Space to Close", 20);

        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}