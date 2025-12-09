package geometry;

import java.util.ArrayList;
import java.util.List;

public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;

    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    public List<Line> rectLines() {
        Point lowerLeft = new Point(getUpperLeft().getX(), getUpperLeft().getY() + getHeight());
        Point upperRight = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY());
        Point lowerRight = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY() + getHeight());
        Line upperLine = new Line(getUpperLeft(), upperRight);
        Line leftLine = new Line(getUpperLeft(), lowerLeft);
        Line lowerLine = new Line(lowerLeft, lowerRight);
        Line rightLine = new Line(lowerRight, upperRight);
        List<Line> lines = new ArrayList<>();
        lines.add(upperLine);
        lines.add(leftLine);
        lines.add(lowerLine);
        lines.add(rightLine);
        return lines;

    }

    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersections = new ArrayList<>();
        for (Line rectLine : rectLines()) {
            if (rectLine.isIntersecting(line)) {
                Point p = rectLine.intersectionWith(line);
                if (p != null && !intersections.contains(p)) {
                    intersections.add(p);
                }
            }
        }

        return intersections;
    }

    // Return the width and height of the rectangle
    public double getWidth() {
        return this.width;

    }

    public double getHeight() {
        return this.height;

    }

    // Returns the upper-left point of the rectangle.
    public Point getUpperLeft() {
        return this.upperLeft;

    }
}