import biuoop.DrawSurface;

import javax.swing.text.EditorKit;
import java.awt.Color;

public class Block implements Collidable, Sprite {

    private Rectangle rect;
    private Color color;

    public Block(Rectangle rect,Color color) {
        this.rect = rect;
        this.color = color;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
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
}