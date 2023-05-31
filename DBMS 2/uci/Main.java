package uci;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws Exception {
		System.out.print("Enter the value of k: ");
		Scanner sc = new Scanner(System.in);
		int k=sc.nextInt();
		
		KFold kFold=new KFold();
		kFold.fold(k);
		
		sc.close();
		

		
		

	}

}