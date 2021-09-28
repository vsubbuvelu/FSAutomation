package week1;

import java.util.Scanner;

public class NumDivisible {
	public static void main(String[] args) throws InterruptedException {
		int num1;
		int num2;
		Scanner scan = new Scanner(System.in);
		System.out.println("Input:");
		num1 = scan.nextInt();
		num2 = scan.nextInt();
		scan.close();
		System.out.print("Output:");
		if (num1 < num2) {
			for (int i = num1; i <= num2; i++) {
				if (i % 3 == 0 && i % 5 == 0)
					System.out.print(" FizzBuzz ");
				else if (i % 3 == 0)
					System.out.print(" Fizz ");
				else if (i % 5 == 0)
					System.out.print(" Buzz ");
				else
					System.out.print(" " + i + " ");
			}
		}else {System.out.println("Please enter first number greater than second number");}

	}

}
