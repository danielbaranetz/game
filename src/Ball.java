import biuoop.DrawSurface;

public class Ball {
    // constructor
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;

    public Ball(Point center, int radius, java.awt.Color color){
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(0,0);
    }

    // accessors
    public int getX(){
        return (int) center.getX();
    }
    public int getY(){
        return (int) center.getY();
    }
    public int getSize(){
        return this.radius;
    }
    public java.awt.Color getColor(){
        return this.color;
    }

    // draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
    }
    public void setVelocity(Velocity v){
        this.velocity = v;
    }
    public void setVelocity(double dx, double dy){
        this.velocity = new Velocity(dx,dy);
    }
    public Velocity getVelocity(){
        return this.velocity;
    }

    public void moveOneStep() {
        double x = center.getX();
        double y = center.getY();
        double dx = velocity.getDx();
        double dy = velocity.getDy();

        // בדיקה אם פוגע בקירות (ימין / שמאל)
        if (x + radius >= DrawAnimation.WIDTH) {
            dx = -dx;
            x = DrawAnimation.WIDTH - radius;
        }
        if (x - radius <= 0) {
            dx = -dx;
            x = radius;
        }

        // בדיקה אם פוגע בתקרה / רצפה
        if (y + radius >= DrawAnimation.HEIGHT) {
            dy = -dy;
            y = DrawAnimation.HEIGHT - radius;
        }
        if (y - radius <= 0) {
            dy = -dy;
            y = radius;
        }

        // עדכון מהירות
        this.velocity = new Velocity(dx, dy);

        // הזזת הכדור לפי המהירות המעודכנת
        this.center = new Point(x + dx, y + dy);
    }




}