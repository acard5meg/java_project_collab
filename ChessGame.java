public class ChessGame {
    public static void main(String[] args) {
        ChessBoard newGame = new ChessBoard();
        while(true) {
            newGame.displayBoard();
            newGame.movePiece();
        }
    }
}
