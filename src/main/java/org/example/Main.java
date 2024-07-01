package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        System.out.println("Please Type to get results from calculator ");
        System.out.println("or type exit to close the program ::");
        Scanner sc=new Scanner(System.in);
        while(true){
            String expression= sc.nextLine();
            ExpressionAnalyzer analyzer = new ExpressionAnalyzer();
            if(expression.equalsIgnoreCase("exit")){
                System.out.println("Thank you for using the calculator :)");
                break;
            }
            try {
                double result = analyzer.analyze(expression);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

    }
}