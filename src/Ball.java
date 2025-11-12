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

    // It receives the boundaries from outside (instead of taking from DrawAnimation)
    public void moveOneStep(int minX, int minY, int maxX, int maxY) {
        // Calculate the next *proposed* position
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        double nextX = this.center.getX() + dx;
        double nextY = this.center.getY() + dy;

        // Check horizontal collision
        if (nextX + this.radius > maxX) {
            dx = -dx; // Reverse direction
            nextX = maxX - this.radius; // Snap to wall
        } else if (nextX - this.radius < minX) {
            dx = -dx; // Reverse direction
            nextX = minX + this.radius; // Snap to wall
        }

        // Check vertical collision
        if (nextY + this.radius > maxY) {
            dy = -dy; // Reverse direction
            nextY = maxY - this.radius; // Snap to wall
        } else if (nextY - this.radius < minY) {
            dy = -dy; // Reverse direction
            nextY = minY + this.radius; // Snap to wall
        }

        // Update the velocity and the position
        this.velocity = new Velocity(dx, dy);
        this.center = new Point(nextX, nextY);
    }




}