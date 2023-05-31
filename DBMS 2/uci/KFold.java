package uci;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class KFold {
	public void fold(int numOfFold) throws Exception {
		int k = numOfFold;
		int total;
		int each;
		double accuracy;
		double sum=0;

		
		ArrayList <Balance> list= new ArrayList<Balance>();
		Scanner sc= new Scanner(new File("E:\\Codes\\semester 5\\Java\\DBMS\\src\\uci\\train.csv"));
		while(sc.hasNext()) {
		String temp=sc.nextLine();
		String[] s=temp.split(",");
		Balance p=new Balance(s[0],Integer.parseInt(s[1]),Integer.parseInt(s[2]),Integer.parseInt(s[3]),Integer.parseInt(s[4]));
		list.add(p);
		}
		
		total=list.size();
		Collections.shuffle(list, new Random(5));
		
			
		System.out.println("Total entry: "+ total);

		each = total / k;

		int i;
		int start = 0;
		int end = -1;
		for (i = 0; i < k; i++) {
			System.out.println("\nFold "+(i+1)+":");
			start = end + 1;
			end = start + each-1;

			if(end>list.size())
				end=list.size()-1;
			
			System.out.println("Start: "+start+"\tEnd: "+end);

			TrainAndTest trainAndTest = new TrainAndTest();
			trainAndTest.training(start, end,list);
			accuracy = trainAndTest.test(start, end,list);
			sum+=accuracy;

			System.out.printf("Accuracy: %.2f%%\n",(accuracy*100));
		}
		
		System.out.printf("Average Accuracy: %.2f%%\n",((sum*100/k)));

	}


}