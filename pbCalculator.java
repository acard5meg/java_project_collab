import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.HashSet;

/*
 *  ATC NOTES 10.13: Added user input validation method for numbers and operations
 *  Added undo method
 *  Wrapped everything inside a do-while, assuming a user wouldn't open
 *  the app to close it
*/
//I made a change
// I made a second change

public class pbCalculator {
	private static Scanner scnr = new Scanner(System.in);
	// ATC NOTE: allOps will be used for inputValidation. Each operation we 
	// build out, the symbol can be put here
	private static HashSet<String> allOps = new HashSet<>() {{
		add("+");
		add("-");
		add("*");
		add("/");
		add("^");
		add("undo"); // Not sure we can do non-case sensitive check in HashSet 
		add("Undo");
		add("UNDO");
	}};
	/**
	 * CHECK THIS IS HOW TO INCOPORATE USER DEFINED DATA STRUCTURE
	 * Point of the data structure is to allow user to 'undo' a prior
	 * operation when we put this inside a loop
	 * One issue is if the user attempts to user the Stack when it is empty
	 * it'll exit the program. We need to think of a way around it
	 */
	private static Stack<Double> result = new Stack<>();
	/**
	 * CHECK ABOVE
	 * @param args
	 */
	
	
    public static void main(String[] args) {
        
        System.out.println("Welcome to the calculator App");
//        System.out.print("Enter the first number: ");
//        double num1 = scnr.nextDouble();
        double num1 = readNum("Enter the first number: ");
//        System.out.print("Enter the operation: ");
//        String str1 = scnr.next();
//        String str1 = readOperation("Enter the operation: ");
//        System.out.print("Enter the next number: ");
//        double num2 = scnr.nextDouble();
//        double num2 = readNum("Enter the next number: ");
        boolean userCont;
        do {
        	boolean undoFlag = false;
        	String str1 = readOperation("Enter the operation: ");
        	
        	if (str1.equalsIgnoreCase("undo"))
        		undoFlag = true;
        	else {
	            double num2 = readNum("Enter the next number: ");
	        	double calcResult;
			    if(str1.equals("/")){
			    	// Accounts for divide by 0
			    	// Assume we can make the condition cleaner, but because it's a double
			    	// doing a == can be imprecise
			    	while (Math.abs(num2) < 0.00000000001) {
			    		num2 = readNum("Cannot divide by 0, please re-enter: ");
			    	}
	//		    	System.out.print(calcDivision(num1, num2));
			    	calcResult = calcDivision(num1, num2);
			    }
			    else if(str1.equals("*"))
			    	calcResult = calcMultiplication(num1, num2);
			    else if(str1.equals("+"))
			    	calcResult = calcAddition(num1, num2);
			    else if(str1.equals("-"))
			    	calcResult = calcSubtraction(num1, num2);
			    else if (str1.equals("^"))
			    	calcResult = calcPower(num1, num2);
			    else
			    	// This is a placeholder. The value is awkward so we'd know if we get here
			    	// Without this we get an unitialized error when we try to push calcResult
			    	calcResult = 0.101010101;
			    
			    result.push(calcResult);
        	}
        	
        	if (undoFlag)
        		result.pop();

		    
		    System.out.println("The result of the calculation is: " + result.peek());	
		    userCont = readContinue("If you'd like to continue enter 'Y', otherwise enter 'N'");
		    num1 = result.peek();
        } while (userCont);
    }


    private static double calcDivision(double num1, double num2){
        double myDiv = num1 / num2;
        return myDiv;
        //Alternatively, you can use return num1 / num 2
    }

    private static double calcMultiplication(double num1, double num2){
        return num1 * num2;
    }

    private static double calcAddition(double num1, double num2){
        return num1 + num2;
    }

    private static double calcSubtraction(double num1, double num2){
        return num1 - num2;
    }
    
    private static double calcPower(double num1, double num2) {
    	return Math.pow(num1, num2);
    }
    /**
     * Does input validation for any numbers that need to be read in
     * @return
     */
    private static double readNum(String userPrompt) {
    	System.out.print(userPrompt);
    	double userInput;
    	while (true) {
    		try {
    			userInput = scnr.nextDouble();
    			break;
    		}
    		catch(InputMismatchException e) {
    			System.out.println(scnr.nextLine() + " is invalid, it must be a number.");
    		}
    	}
    	/*
    	 * Based on the cadence of user input:
    	 * num
    	 * operation
    	 * num
    	 * nextDouble does not clear the \n token when the user presses enter
    	 * this will cause the readOperation to have unexpected behavior
    	 * Use the additional nextLine() method to clear the \n token from
    	 * the numeric input
    	 */
    	scnr.nextLine();
    	return userInput;
    }
    
    /**
     * Does input validation on the operations
     * Checks whether the operation is in the allOps set
     * as a way to determine whether calculator can perform action
     * @param userPrompt
     * @return
     */
    private static String readOperation(String userPrompt) {
    	System.out.print(userPrompt);
    	String userInput = scnr.nextLine().trim();
    	while (!allOps.contains(userInput)) {
    		System.out.println("Invalid operation, please re-enter");
    		userInput = scnr.nextLine().trim();
    	}
    	return userInput;
    }
    
    private static boolean readContinue(String userPrompt) {
    	System.out.println(userPrompt);
    	String userInput = scnr.nextLine().trim();
    	while (!(userInput.equalsIgnoreCase("Y") || userInput.equalsIgnoreCase("N"))) {
    		System.out.println("Invalid, please enter a Y or N");
    		userInput = scnr.nextLine().trim();
    	}
    	if (userInput.equalsIgnoreCase("Y"))
    		return true;
    	else
    		return false;
    }

}