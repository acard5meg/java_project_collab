public class Piece {
    /*
    All pieces cannot move through other pieces, except knight.
    All pieces cannot take their own pieces.
    All pieces can only move once w/in a turn
    All pieces have presence
     */
    private boolean isCaptured;
    private boolean isWhite;

    public Piece(){
        isCaptured = false;
        isWhite = true;
    }

    public boolean getCapture(){
        return isCaptured;
    }

    public boolean getColor(){
        return isWhite;
    }

    public void setCapture(boolean capture){
        isCaptured = capture;
    }

    public void setColor(boolean color){
        isWhite = color;
    }

}
