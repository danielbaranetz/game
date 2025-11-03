public class Line {
    // constructors
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    // Return the length of the line
    public double length() {
        return start.distance(end);

    }

    // Returns the middle point of the line
    public Point middle() {
        double midX = start.getX() + end.getX() / 2;
        double midY = start.getY() + end.getY() / 2;
        return new Point (midX,midY);
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
        return
    }

    // Returns the intersection point if the lines intersect,
    // and null otherwise.
    public Point intersectionWith(Line other) { }

    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) { }

}
