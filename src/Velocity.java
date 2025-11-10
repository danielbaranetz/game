public class Velocity {
    private double dx;
    private double dy;
    public Velocity(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }
    public double getDx(){
        return this.dx;
    }
    public double getDy(){
        return this.dy;
    }
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // ממירים את הזווית מ-degree ל-radian
        double rad = Math.toRadians(angle);

        double dx = speed * Math.sin(rad);   // x גדל ימינה
        double dy = -speed * Math.cos(rad);  // y גדל למטה, לכן סימן מינוס
        return new Velocity(dx, dy);
    }
    // Take a point with position (x,y) and return a new point
    // with position (x+dx, y+dy)
    public Point applyToPoint(Point p){
        return new Point((p.getX() + dx),(p.getY() + dy));
    }
}