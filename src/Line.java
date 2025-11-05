public class Line {
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

    public Point start() { return start; }
    public Point end() { return end; }

    // slope
    private Double slope() {
        double dx = end.getX() - start.getX();
        double dy = end.getY() - start.getY();
        if (Math.abs(dx) < 1e-9) return null; // קו אנכי
        return dy / dx;
    }

    // intersecting with y
    private double yIntercept(Double slope) {
        return start.getY() - slope * start.getX();
    }

    // check if intersecting
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    // intersecting point(if not null)
    public Point intersectionWith(Line other) {
        Double m1 = this.slope();
        Double m2 = other.slope();

        // two lines vertical - lines not intersecting
        if (m1 == null && m2 == null) return null;

        double x, y;

        // one line vertical
        if (m1 == null) {
            x = start.getX();
            double b2 = other.yIntercept(m2);
            y = m2 * x + b2;
        } else if (m2 == null) {
            x = other.start.getX();
            double b1 = this.yIntercept(m1);
            y = m1 * x + b1;
        } else {
            // parallels
            if (Math.abs(m1 - m2) < 1e-9) return null;

            double b1 = this.yIntercept(m1);
            double b2 = other.yIntercept(m2);

            x = (b2 - b1) / (m1 - m2);
            y = m1 * x + b1;
        }

        Point p = new Point(x, y);

        // check if the point on the both lines
        if (isOnSegment(p, this) && isOnSegment(p, other)) {
            return p;
        }

        return null;
    }

    private boolean isOnSegment(Point p, Line l) {
        double x = p.getX(), y = p.getY();
        return x >= Math.min(l.start.getX(), l.end.getX()) - 1e-9 &&
                x <= Math.max(l.start.getX(), l.end.getX()) + 1e-9 &&
                y >= Math.min(l.start.getY(), l.end.getY()) - 1e-9 &&
                y <= Math.max(l.start.getY(), l.end.getY()) + 1e-9;
    }
}
