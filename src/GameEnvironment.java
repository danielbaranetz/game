import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    private List<Collidable> collidables;

    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point closestPoint = null;
        Collidable closestObject = null;
        double minDistance = Double.MAX_VALUE;
        for (Collidable c : collidables) {
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null) {
                double currentDistance = trajectory.start().distance(p);
                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    closestPoint = p;
                    closestObject = c;

                }
            }
        }
        if (closestObject == null) {
            return null;
        }

        return new CollisionInfo(closestPoint, closestObject);
    }
}


