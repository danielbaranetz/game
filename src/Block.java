import biuoop.DrawSurface;
import java.awt.Color;

public class Block implements Collidable {

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
    // בתוך Block.java
    public void drawOn(DrawSurface d) {
        // ציור המלבן עצמו (צבע מילוי)
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
}