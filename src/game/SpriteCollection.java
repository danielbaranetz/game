package game;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {
    private List<Sprite> spriteCollections;

    public SpriteCollection(){
        this.spriteCollections = new ArrayList<>();
    }

    public void addSprite(Sprite s){
        spriteCollections.add(s);

    }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed(){
        for(Sprite s : spriteCollections){
            s.timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d){
        for(Sprite s : spriteCollections){
            s.drawOn(d);
        }
    }

    public void removeSprite(Sprite s){
        this.spriteCollections.remove(s);
    }
}