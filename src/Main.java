public class Main {
    public static void main(String[] args) {

//        LinesApp app = new LinesApp();
//        app.run();
        DrawAnimation app = new DrawAnimation();
        Point point = new Point(0,0);
        app.drawAnimation(point, 10, 10);
    }
}