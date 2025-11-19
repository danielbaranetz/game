import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import java.awt.Color;

public class CollisionTest {
    public static void main(String[] args) {
        // 1. הגדרת גודל המסך
        int screenWidth = 800;
        int screenHeight = 600;

        GUI gui = new GUI("Collision Test", screenWidth, screenHeight);
        Sleeper sleeper = new Sleeper();
        GameEnvironment environment = new GameEnvironment();

        int wallThickness = 20;

        Block topWall = new Block(new Rectangle(new Point(0, 0), screenWidth, wallThickness));
        Block bottomWall = new Block(new Rectangle(new Point(0, screenHeight - wallThickness), screenWidth, wallThickness));
        Block leftWall = new Block(new Rectangle(new Point(0, 0), wallThickness, screenHeight));
        Block rightWall = new Block(new Rectangle(new Point(screenWidth - wallThickness, 0), wallThickness, screenHeight));

        environment.addCollidable(topWall);
        environment.addCollidable(bottomWall);
        environment.addCollidable(leftWall);
        environment.addCollidable(rightWall);

        Block centerBlock = new Block(new Rectangle(new Point(350, 250), 100, 30));
        environment.addCollidable(centerBlock);

        Block sideBlock = new Block(new Rectangle(new Point(150, 150), 30, 100));
        environment.addCollidable(sideBlock);

        Ball ball = new Ball(new Point(400, 300), 10, Color.RED);
        ball.setVelocity(6, 6);
        ball.setGameEnvironment(environment);

        while (true) {
            DrawSurface d = gui.getDrawSurface();

            d.setColor(Color.WHITE);
            d.fillRectangle(0, 0, screenWidth, screenHeight);

            topWall.drawOn(d);
            bottomWall.drawOn(d);
            leftWall.drawOn(d);
            rightWall.drawOn(d);
            centerBlock.drawOn(d);
            sideBlock.drawOn(d);

            ball.moveOneStep();
            ball.drawOn(d);

            gui.show(d);
            sleeper.sleepFor(50);
        }
    }
}