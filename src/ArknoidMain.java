public class ArknoidMain {
    public static void main(String[] args) {
        Game game = new Game();

        game.initialize(); // מכין את הלוח, הכדורים והבלוקים
        game.run();        // מתחיל את המשחק
    }
}