package game;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import primitives.Ball;
import primitives.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Block implements Collidable, Sprite, HitNotifier {

    private Rectangle rect;
    private Color color;
    private List<HitListener> hitListeners;

    public Block(Rectangle rect,Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl){
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);

    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        double x = collisionPoint.getX();
        double y = collisionPoint.getY();
        double rectX = this.rect.getUpperLeft().getX();
        double rectY = this.rect.getUpperLeft().getY();
        double width = this.rect.getWidth();
        double height = this.rect.getHeight();

        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        double epsilon = 0.0001;

        if (Math.abs(x - rectX) < epsilon || Math.abs(x - (rectX + width)) < epsilon) {
            if (dx > 0) {
                dx = -Math.abs(dx);
            } else {
                dx = Math.abs(dx);
            }
        }

        if (Math.abs(y - rectY) < epsilon || Math.abs(y - (rectY + height)) < epsilon) {
            if (dy > 0) {
                dy = -Math.abs(dy);
            } else {
                dy = Math.abs(dy);
            }
        }

        return new Velocity(dx, dy);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());

        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
    }

    @Override
    public void timePassed() {
        // To edit
    }
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
}