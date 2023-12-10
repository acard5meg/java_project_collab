import java.util.HashMap;
import java.util.Scanner;


//Until we have color, uppercase is white, lowercase is black pieces.
public class ChessBoard {
    private char[][] board;
    private int[] firstCol = {1,2,3,4,5,6,7,8};
    private char[] bottomRow = {'A','B','C','D','E','F','G','H'};
    private HashMap<String,String> cellContents = new HashMap<>();
    private PawnPiece pawn = new PawnPiece();
    Scanner scnr = new Scanner(System.in);

    public ChessBoard(){
        board = new char[8][8];
        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[row].length; col++)
                board[row][col] = '*';
        for (int row = 0; row < firstCol.length; row++)
            for (int col = 0; col < bottomRow.length; col++) {
                String temp = Character.toString(firstCol[row]) + Integer.toString(bottomRow[col]);
                cellContents.put(temp, "*");
            }
        placePiece('P');
        placePiece('R');
        placePiece('H');
        placePiece('B');
        placePiece('K');
        placePiece('Q');
    }
    private void placePiece(char pieceLetter){
        if (pieceLetter == 'P'){
            for(int i = 0; i < board[1].length; i++){
                board[6][i] = 'P';
            }
        }
        else if (pieceLetter == 'R'){
            board[7][0] = 'R';
            board[7][7] = 'R';
        }
        else if(pieceLetter == 'H'){
            board[7][1] = 'H';
            board[7][6] = 'H';
        }
        else if(pieceLetter == 'B'){
            board[7][2] = 'B';
            board[7][5] = 'B';
        }
        else if(pieceLetter == 'K'){
            board[7][4] = 'K';
        }
        else {
            board[7][3] = 'Q';
        }
    }
    public void displayBoard(){
        System.out.println();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col <= board[row].length; col++) {
                if (col == 0)
                    System.out.print(firstCol[firstCol.length - 1 - row] + " ");
                else
                    System.out.print(board[row][col-1] + " ");
            }
            System.out.println();
        }

        for (int i = 0; i <= bottomRow.length; i++) {
            if (i == 0)
                System.out.print("  ");
            else
                System.out.print(bottomRow[i-1] + " ");
        }
        System.out.println();
    }
    public void movePiece(){

        /*
        1. Check User input and that its valid
            a. Its not an Asterisk
            b. Within the confines of the board A1 - H8
            c. Ensure users only select their pieces much later. Modular divided by 2 indicates between white and black pieces based on amount of times loop runs.
            d. Valid move for piece.
         */

        String userInput = scnr.nextLine().toUpperCase().trim();
        int initCol;
        int initRow;
        int futCol;
        int futRow;
        char pieceType;

        while(true) {

            if (userInput.length() > 2) {
                userInput = fixSpaceBetween(userInput);
            }

            if (userInput.length() != 2) {
                System.out.print("Invalid entry, must enter single letter and number, e.g. D5: ");
                userInput = scnr.nextLine().toUpperCase().trim();
                continue;
            }
            initCol = ((int) userInput.charAt(0)) - 65;
            boolean validCol = validCellEntry(initCol);
            initRow = 8 - Character.getNumericValue(userInput.charAt(1));
            boolean validRow = validCellEntry(initRow);

            if(!validCol || !validRow){
                System.out.print("Outside the board Please re-enter: ");
                userInput = scnr.nextLine().toUpperCase().trim();
                continue;
            }
            pieceType = board[initRow][initCol];
            if (pieceType == '*'){
                System.out.print("There is no piece located here. Please re-enter which piece you want to move.");
                userInput = scnr.nextLine().toUpperCase().trim();
                continue;
            }


            break;
        }




        String newuserInput = scnr.nextLine().toUpperCase().trim();

        while(true) {
            if (newuserInput.length() > 2) {
                newuserInput = fixSpaceBetween(newuserInput);
            }

            if (newuserInput.length() != 2) {
                System.out.print("Invalid entry, must enter single letter and number, e.g. D5: ");
                newuserInput = scnr.nextLine().toUpperCase().trim();
                continue;
            }

            futCol = ((int) newuserInput.charAt(0)) - 65;
            boolean validFutCol = validCellEntry(futCol);
            futRow = 8 - Character.getNumericValue(newuserInput.charAt(1));
            boolean validFutRow = validCellEntry(futRow);
            if(!validFutCol || !validFutRow){
                System.out.print("Invalid entry, must enter single letter and number, e.g. D5: ");
                newuserInput = scnr.nextLine().toUpperCase().trim();
                continue;
            }
            break;

        }





        board[initRow][initCol] = '*';
        board[futRow][futCol] = pieceType;

    }

    //fixSpaceBetween removes the spaces between characters e.g., A 7 = A7.
    private String fixSpaceBetween(String uI) {
        String fixed = "";
        for (int i = 0; i < uI.length(); i++) {
            if ((int) uI.charAt(i) != 32)
                fixed += uI.charAt(i);
        }
        return fixed;
    }

    private boolean validCellEntry(int posReference){
        if (posReference < 0 || posReference >= 8){
            return false;
        }
        else{
            return true;
        }
    }
}
