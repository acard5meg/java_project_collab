public class PawnPiece extends Piece{
    /*
    Pawns can only move 1 square except on first turn they can move 2.
    Pawns can only take diagonally
    Pawns that are blocked cannot move forward
    Empassant
    Pawns on the 8th square promote
     */

    private char name;
    private boolean isBlocked;
    private boolean hasMoved;
    private boolean hasCapture;
    private boolean isCaptured;

    public PawnPiece() {
        super();
        name = 'p';
        isBlocked = false;
        hasMoved = false;
        hasCapture = false;
        isCaptured = false;


    }

    private boolean getBlocked(){
        return isBlocked;
    }

    private boolean getFirstMove(){
        return hasMoved;
    }

    private boolean getPawnCapture(){
        return hasCapture;
    }

    private boolean getExistence(){
        return isCaptured;
    }

    public boolean validMove(ChessBoard cb){
        return true;
    }

}
