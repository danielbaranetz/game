import biuoop.DrawSurface;

import javax.swing.text.EditorKit;
import java.awt.Color;

public class Block implements Collidable, Sprite {

    private Rectangle rect;

    public Block(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getDx();
        double newDy = currentVelocity.getDy();
        if (collisionPoint.getY() == rect.getUpperLeft().getY() ||
                collisionPoint.getY() == rect.getUpperLeft().getY() + rect.getHeight()) {
            newDy = -newDy;
        }
        if (collisionPoint.getX() == rect.getUpperLeft().getX() ||
                collisionPoint.getX() == rect.getUpperLeft().getX() + rect.getWidth()) {
            newDx = -newDx;
        }
        return new Velocity(newDx, newDy);
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GRAY);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(),
                (int) this.rect.getHeight());

        // ציור המסגרת (כדי שנראה את הגבולות ברור)
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
}