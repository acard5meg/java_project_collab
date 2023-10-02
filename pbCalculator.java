import java.util.Scanner;

public class pbCalculator {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        System.out.println("Welcome to the calculator App");
        System.out.print("Enter the first number: ");
        double num1 = scnr.nextDouble();
        System.out.print("Enter the operation: ");
        String str1 = scnr.next();
        System.out.print("Enter the next number: ");
        double num2 = scnr.nextDouble();



        if(str1.equals("%")){
            System.out.print(calcDivision(num1, num2));
        }
        else if(str1.equals("*")){
            System.out.print(calcMultiplication(num1, num2));
        }
        else if(str1.equals("+")){
            System.out.print(calcAddition(num1, num2));
        }
        else if(str1.equals("-")){
            System.out.print(calcSubtraction(num1, num2));
        }
        else{
            System.out.print("You did not enter a valid operation for this calculator.");
        }


    }


    public static double calcDivision(double num1, double num2){
        double myDiv = num1 / num2;
        return myDiv;
        //Alternatively, you can use return num1 / num 2
    }

    public static double calcMultiplication(double num1, double num2){
        return num1 * num2;
    }

    public static double calcAddition(double num1, double num2){
        return num1 + num2;
    }

    public static double calcSubtraction(double num1, double num2){
        return num1 - num2;
    }


}
