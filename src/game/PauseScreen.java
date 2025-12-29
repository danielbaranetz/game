package game;

import animations.Animation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.*;

public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private SpriteCollection gameScreen;


    public PauseScreen(KeyboardSensor k,SpriteCollection gameScreen) {
        this.keyboard = k;
        this.stop = false;
        this.gameScreen = gameScreen;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        this.gameScreen.drawAllOn(d);

        d.setColor(Color.WHITE);
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);

        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {

            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}