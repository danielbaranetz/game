public class Line {
    // constructors
    private double x1;
    private double x2;
    private double y1;
    private double y2;
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    // Return the length of the line
    public double length() {
        return Math.sqrt(((x1 - x2)*(x1 - x2)) + ((y1 - y2)*(y1 - y2)));

    }

    // Returns the middle point of the line
    public Point middle() {
        return new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }

    // Returns the start point of the line
    public Point start() {
        return start;
    }

    // Returns the end point of the line
    public Point end() {
        return end;
    }

    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {

    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) { }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) { }

}
