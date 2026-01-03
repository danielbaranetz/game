package game;

import biuoop.DrawSurface;

import java.awt.*;

public class LivesIndicator implements Sprite{
    private Counter lives;
    public LivesIndicator(Counter lives){
        this.lives = lives;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(20, 17, "Lives: " + this.lives.getValue(), 15);

    }

    @Override
    public void timePassed() {

    }

    public void addToGame(GameLevel g){
        g.addSprite(this);
    }
}
