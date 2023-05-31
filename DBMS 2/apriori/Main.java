package apriori;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws IOException {
		
		System.out.print("Enter minimum support count: ");
		Scanner sc = new Scanner(System.in);
		int minSupport = sc.nextInt();
		
		Apriori apriori=new Apriori(minSupport);

		ArrayList<Set<String>> database = apriori.readFile();
		HashMap<Set<String>, Integer> items = apriori.getItems(database);
	
		apriori.getItemSets(database, items);
		apriori.getConfidence(items);
		sc.close();

	}
	

}
