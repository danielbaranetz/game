package game;

import biuoop.DrawSurface;

import java.awt.*;

public class ScoreIndicator implements Sprite {
    private Counter score;
    public  ScoreIndicator(Counter score){
        this.score = score;

    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.LIGHT_GRAY);
        d.fillRectangle(0, 0, 800, 20);
        d.setColor(Color.BLACK);
        d.drawText(350, 17, "Score: " + this.score.getValue(), 15);

    }

    @Override
    public void timePassed() {

    }
    public void addToGame(Game g){
        g.addSprite(this);

    }
}
