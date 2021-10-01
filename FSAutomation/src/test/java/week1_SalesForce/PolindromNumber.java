package week1;

import java.util.Scanner;

public class PolindromNumber {
	public static void main(String[] args) throws InterruptedException {
		int num;
		int x;
		String finNo = "";
		Scanner scan = new Scanner(System.in);
		System.out.println("Input:");
		num = scan.nextInt();
		int enterdNum = num;
		scan.close();
		do {
			x = num % 10;
			num = (num - x) / 10;
			finNo = finNo + x;
		} while (num > 0);
		if (enterdNum == Integer.parseInt(finNo))
			System.out.println(true);
		else
			System.out.println(false);
	}

}
