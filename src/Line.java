

public class Line {
    // Fields
    private Point start;
    private Point end;

    // Constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);

    }

    // Returns the length of the line
    public double length() {
        return start.distance(end);
    }

    // Returns the middle point of the line
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    // Returns the start point
    public Point start() {
        return start;
    }

    // Returns the end point
    public Point end() {
        return end;
    }

    // Returns the slope of the line (∞ for vertical lines)
    public double slope() {
        if (end.getX() == start.getX()) {
            return Double.POSITIVE_INFINITY;
        }
        return (end.getY() - start.getY()) / (end.getX() - start.getX());
    }

    // Returns the intersection with the Y-axis (b in y = mx + b)
    public double intersectionY() {
        if (Double.isInfinite(slope())) {
            return Double.NaN;
        }
        return -slope() * start.getX() + start.getY();
    }

    // Returns true if the two line segments intersect
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    // Returns the intersection point if the segments intersect, otherwise null
    public Point intersectionWith(Line other) {
        double x1 = this.start.getX();
        double y1 = this.start.getY();
        double x2 = this.end.getX();
        double y2 = this.end.getY();

        double x3 = other.start.getX();
        double y3 = other.start.getY();
        double x4 = other.end.getX();
        double y4 = other.end.getY();

        double denom = (x1 - x2) * (y3 - y4) - (y1 - y2) * (x3 - x4);

        // denom == 0 → lines are parallel or collinear
        if (Math.abs(denom) < 1e-10) {
            // Check if they are collinear
            if (isCollinearWith(other)) {
                // Check if they overlap
                if (isOverlapping(other)) {
                    // צריך לבדוק את כל 4 הנקודות
                    if (this.isPointOnSegment(other.start)) {
                        return other.start;
                    }
                    if (this.isPointOnSegment(other.end)) {
                        return other.end;
                    }
                    if (other.isPointOnSegment(this.start)) {
                        return this.start;
                    }
                    if (other.isPointOnSegment(this.end)) {
                        return this.end;
                    }
                }
            }
            return null;
        }

        // Calculate intersection point (for infinite lines)
        double px = ((x1 * y2 - y1 * x2) * (x3 - x4)
                - (x1 - x2) * (x3 * y4 - y3 * x4)) / denom;
        double py = ((x1 * y2 - y1 * x2) * (y3 - y4)
                - (y1 - y2) * (x3 * y4 - y3 * x4)) / denom;

        Point intersection = new Point(px, py);

        // Check if the intersection lies on both line segments
        if (isPointOnSegment(intersection) && other.isPointOnSegment(intersection)) {
            return intersection;
        }

        return null; // intersection is outside the segments
    }

    // Checks if a point lies on this line segment
    private boolean isPointOnSegment(Point p) {
        double minX = Math.min(start.getX(), end.getX());
        double maxX = Math.max(start.getX(), end.getX());
        double minY = Math.min(start.getY(), end.getY());
        double maxY = Math.max(start.getY(), end.getY());

        return p.getX() >= minX - 1e-10 && p.getX() <= maxX + 1e-10 &&
                p.getY() >= minY - 1e-10 && p.getY() <= maxY + 1e-10;
    }

    // Checks if this line and another line are collinear (on the same infinite line)
    private boolean isCollinearWith(Line other) {
        // If one point from 'other' lies on this line → they are collinear
        double area = (other.start.getX() - start.getX()) * (end.getY() - start.getY())
                - (other.start.getY() - start.getY()) * (end.getX() - start.getX());
        return Math.abs(area) < 1e-10;
    }

    // Checks if two collinear lines overlap
    private boolean isOverlapping(Line other) {
        return this.isPointOnSegment(other.start) ||
                this.isPointOnSegment(other.end) ||
                other.isPointOnSegment(this.start) ||
                other.isPointOnSegment(this.end);
    }

    // Returns true if the two lines are equal (regardless of direction)
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end)) ||
                (this.start.equals(other.end) && this.end.equals(other.start));
    }
}
