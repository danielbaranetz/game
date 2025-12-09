package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import primitives.Ball;
import primitives.Velocity;

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private int speed;

    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle rect, int speed){
        this.keyboard = keyboard;
        this.rect = rect;
        this.speed = speed;
    }

    public void moveLeft() {
        double newX = this.rect.getUpperLeft().getX() - this.speed;

        // גבול שמאלי (רוחב הקיר השמאלי)
        double minX = 20;

        if (newX < minX) {
            newX = minX;
        }

        Point p = new Point(newX, this.rect.getUpperLeft().getY());
        this.rect = new Rectangle(p, this.rect.getWidth(), this.rect.getHeight());
    }
    public void moveRight() {
        double newX = this.rect.getUpperLeft().getX() + this.speed;

        double wallBorder = 780;

        double maxX = wallBorder - this.rect.getWidth();

        if (newX > maxX) {
            newX = maxX;
        }

        Point p = new Point(newX, this.rect.getUpperLeft().getY());
        this.rect = new Rectangle(p, this.rect.getWidth(), this.rect.getHeight());
    }

    // game.Sprite
    public void timePassed(){
            if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
                moveLeft();
            }
            if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
                moveRight();
            }
    }
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.ORANGE);
        d.fillRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(),
                (int) rect.getHeight());

        d.setColor(java.awt.Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(),
                (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(),
                (int) rect.getHeight());
    }

    // game.Collidable
    public Rectangle getCollisionRectangle(){
        return this.rect;
    }
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddleX = this.rect.getUpperLeft().getX();
        double paddleWidth = this.rect.getWidth();
        double hitX = collisionPoint.getX();

        // חישוב המהירות (כדי לשמור על הכוח)
        double currentSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));

        // סף דיוק
        double epsilon = 0.0001;

        // ------------------------------------------------------------
        // שלב 1: בדיקת קצוות (הכי חשוב!!)
        // ------------------------------------------------------------

        // האם נגענו בקיר השמאלי ממש?
        if (Math.abs(hitX - paddleX) < epsilon) {
            // תתנהג כמו קיר: הפוך DX, שמור על DY
            return new Velocity(-Math.abs(currentVelocity.getDx()), currentVelocity.getDy());
        }

        // האם נגענו בקיר הימני ממש?
        if (Math.abs(hitX - (paddleX + paddleWidth)) < epsilon) {
            // תתנהג כמו קיר: הפוך DX, שמור על DY
            return new Velocity(Math.abs(currentVelocity.getDx()), currentVelocity.getDy());
        }

        double regionWidth = paddleWidth / 5;
        Velocity newVelocity;

        // אזור 1
        if (hitX < paddleX + regionWidth) {
            newVelocity = Velocity.fromAngleAndSpeed(300, currentSpeed);
        }
        // אזור 2
        else if (hitX < paddleX + 2 * regionWidth) {
            newVelocity = Velocity.fromAngleAndSpeed(330, currentSpeed);
        }
        // אזור 3 (אמצע)
        else if (hitX < paddleX + 3 * regionWidth) {
            newVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }
        // אזור 4
        else if (hitX < paddleX + 4 * regionWidth) {
            newVelocity = Velocity.fromAngleAndSpeed(30, currentSpeed);
        }
        // אזור 5
        else {
            newVelocity = Velocity.fromAngleAndSpeed(60, currentSpeed);
        }

        // שסתום ביטחון: מכיוון שזו פגיעת אזורים (למעלה), הכדור *חייב* לעלות
        if (newVelocity.getDy() > 0) {
            newVelocity = new Velocity(newVelocity.getDx(), -newVelocity.getDy());
        }

        return newVelocity;
    }

    // Add this paddle to the game.
    public void addToGame(Game g){
        g.addSprite(this);
        g.addCollidable(this);
    }
}