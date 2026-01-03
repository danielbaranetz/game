package game;

import biuoop.DrawSurface;
import java.awt.Color;

public class LevelNameIndicator implements Sprite {
    private String levelName;

    public LevelNameIndicator(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(550, 15, "Level Name: " + levelName, 15);
    }

    @Override
    public void timePassed() {
    }

    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}