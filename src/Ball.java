import biuoop.DrawSurface;

public class Ball {
    // constructor
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

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

    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    // It receives the boundaries from outside (instead of taking from DrawAnimation)
    public void moveOneStep(){
        double dx = this.velocity.getDx();
        double dy = this.velocity.getDy();
        Point start = this.center;
        Point end = new Point(start.getX() + dx, start.getY() + dy);
        Line trajectory = new Line(start, end);
        CollisionInfo collision = this.gameEnvironment.getClosestCollision(trajectory);
        if (collision == null) {
            this.center = end;
        }else{
            double newX = collision.collisionPoint().getX() - dx / 1000;
            double newY = collision.collisionPoint().getY() - dy / 1000;
            this.center = new Point(newX, newY);
            Collidable object = collision.collisionObject();
            Velocity newV = object.hit(collision.collisionPoint(), this.velocity);
            this.velocity = newV;
        }
    }




}