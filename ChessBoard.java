/**
 * I changed the definition of the PawnPiece class to allow 
 * for the definiton of the piece name so we could you 'p' or 'P'
 * We can use object references within the chessboard and remove the hashmap
 * cellContents
 * Then we'll need to work with the PawnPiece object
 * If we make the changes above we'll need to change the definition of 
 * chessBoard to be a 2-D array of Piece (?) to allow for polymorphism
 * we'll also need to change the display board that null means missing and not *
 * Also consider adding row and col to the member variables of PawnPiece so that we can determine
 * whether a piece can move, we could do this on initialization
 * Then we could use a method for the piece to determine if the user input is a valid move
 */

import java.util.HashMap;
import java.util.Scanner;


//Until we have color, uppercase is white, lowercase is black pieces.
public class ChessBoard {
    private char[][] board;
    private int[] firstCol = {1,2,3,4,5,6,7,8};
    private char[] bottomRow = {'A','B','C','D','E','F','G','H'};
    private HashMap<String,String> cellContents = new HashMap<>();
    private PawnPiece pawn = new PawnPiece();
    private Scanner scnr = new Scanner(System.in);
    private int moveNumber = 0;

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
        placePiece('p');
        placePiece('r');
        placePiece('h');
        placePiece('b');
        placePiece('k');
        placePiece('q');
    }
    private void placePiece(char pieceLetter){
        if (pieceLetter == 'P'){
            for(int i = 0; i < board[6].length; i++){
                board[6][i] = 'P';
            }
        }
        else if (pieceLetter == 'p') {
        	for (int i = 0; i < board[1].length; i++)
        		board[1][i] = 'p';
        }
        else if (pieceLetter == 'R'){
            board[7][0] = 'R';
            board[7][7] = 'R';
        }
        else if (pieceLetter == 'r') {
        	board[0][0] = 'r';
        	board[0][7] = 'r';
        }
        else if(pieceLetter == 'H'){
            board[7][1] = 'H';
            board[7][6] = 'H';
        }
        else if (pieceLetter == 'h') {
        	board[0][1] = 'h';
        	board[0][6] = 'h';
        }
        else if(pieceLetter == 'B'){
            board[7][2] = 'B';
            board[7][5] = 'B';
        }
        else if (pieceLetter == 'b') {
        	board[0][2] = 'b';
        	board[0][5] = 'b';
        }
        else if(pieceLetter == 'K'){
            board[7][4] = 'K';
        }
        else if (pieceLetter == 'k')
        	board[0][4] = 'k';
        
        else if (pieceLetter == 'Q') {
            board[7][3] = 'Q';
        }
        else
        	board[0][3] = 'q';
    }
    public void displayBoard(){
    	for (int i = 0; i <= bottomRow.length; i++) {
            if (i == 0)
                System.out.print("   ");
            else
                System.out.print(bottomRow[i-1] + " ");
        }
        System.out.println();
        System.out.println();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col <= board[row].length + 1; col++) {
                if (col == 0)
                    System.out.print(firstCol[firstCol.length - 1 - row] + "  ");
                else if (col > 0 && col <= board[row].length)
                    System.out.print(board[row][col-1] + " ");
                else
                	System.out.print("  " + firstCol[firstCol.length - 1 - row]);
                
            }
            System.out.println();
        }

        System.out.println();
        for (int i = 0; i <= bottomRow.length; i++) {
            if (i == 0)
                System.out.print("   ");
            else
                System.out.print(bottomRow[i-1] + " ");
        }
        System.out.println();
    }
    
    public void movePiece(){
    	
    	/*
    	 * 1. Get user input and check that it is an occupied space on the board
    	 * 2. Must check that the piece chosen is the correct color. Ensure users 
    	 * 	only select their pieces much later. Modular 2 indicates between 
    	 * 	white and black pieces based on amount of times loop runs.
    	 * 2 answer: This is "solved" with the private moveNumber variable    
    	 * 
    	 * 3. Must confirm piece can move in desired fashion - Chebyshev distance for knight?
    	 * 3a. Piece cannot capture it's own.
    	 * 4. Must execute capture if that is the case
    	 */

    	char pieceType;
    	int initRow, initCol, futRow, futCol;
    	System.out.println("Please enter the location of the piece you'd like to move.");
    	while (true) { // Solves problem 1
	    	int[] currentSpace = getUserInput();
	    	initRow = currentSpace[0];
	    	initCol = currentSpace[1];
	        pieceType = board[initRow][initCol];
	        
	        boolean wrongColor = ((moveNumber % 2 == 0 && isUpperCase(pieceType) != true) 
	        		|| (moveNumber % 2 != 0 && isUpperCase(pieceType) != false));
	        
	        if (wrongColor) {
	        	System.out.print("You selected the wrong color for the move. Please re-enter: ");
	        	continue;
	        }
	        
	        if (pieceType == '*'){
	            System.out.print("There is no piece located here. Please re-enter which piece you want to move: ");
	            continue;
	        }
	        break;
    	}

    	System.out.println("Please enter the location where you'd like to move the piece to.");
    	while (true) {
	    	int[] futureSpace = getUserInput();
	    	futRow = futureSpace[0];
	    	futCol = futureSpace[1];
	    	
	    	if (board[futRow][futCol] != '*' && isUpperCase(pieceType) == isUpperCase(board[futRow][futCol])) { // Solves 3a
	    		System.out.println("You cannot put your piece on top of another one of your pieces.");
	    		continue;
	    	}
	    	break;
    	}
        board[initRow][initCol] = '*';
        board[futRow][futCol] = pieceType;
        moveNumber++;
    }

    /**
     * Returns a 1-D array with 2 elements
     * First element is row, second is column
     * @return
     */
    private int[] getUserInput() {
        /*
        1. Check User input and that its valid
            a. Valid input in terms of length
            b. Within the confines of the board A1 - H8
         */

        String userInput = scnr.nextLine().toUpperCase().trim();
        int initCol;
        int initRow;

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

            break;
            
        }
        int[] toReturn = {initRow,initCol};
        return toReturn;

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
    
    private static boolean isUpperCase(char piece) {
    	return (int) piece >= 65 && (int) piece <=90;
    }
    
    public static void main(String[] args) {
    	System.out.println(isUpperCase('A') == isUpperCase('Q'));
    	System.out.println(isUpperCase('Z') == isUpperCase('a'));
    	System.out.println(isUpperCase('e') == isUpperCase('z'));
    }
}
