package apriori;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Apriori {
	static int minSupport;

	public Apriori(int minSupport) {
		this.minSupport = minSupport;
	}

	public static ArrayList<Set<String>> readFile() throws IOException {
		ArrayList<Set<String>> database = new ArrayList<>();
		File file = new File("transaction.txt");
		Scanner Reader = new Scanner(file);

		while (Reader.hasNextLine()) {
			String[] items = Reader.nextLine().split(",");
			Set<String> transaction = new HashSet<>();
			for (String item : items) {
				transaction.add(item);
			}

			database.add(transaction);
		}
		Reader.close();

		return database;
	}
	

	public static HashMap<Set<String>, Integer> getItems(ArrayList<Set<String>> transactionDatabase) {
		HashMap<Set<String>, Integer> result = new HashMap<Set<String>, Integer>();
		
		for (int i = 0; i < transactionDatabase.size(); i++) {
			Set<String> transaction = new HashSet<String>();
			transaction = transactionDatabase.get(i);

			for (String itemlist : transaction) {
				Set<String> item = new HashSet<String>();
				item.add(itemlist);
				if (!result.containsKey(item))
					result.put(item, 1);
				else
					result.put(item, (result.get(item)) + 1);

			}
		}

		System.out.println("The unique items are-");
		for (Map.Entry<Set<String>, Integer> set : result.entrySet()) {
			System.out.println(set.getKey());
		}
		System.out.println("\n");

		result = selectFrequentItems(result);
		printFrequentItems(result, 0);

		return result;

	}



	public static void getItemSets(ArrayList<Set<String>> transactionDatabase, HashMap<Set<String>, Integer> result) {
		int k = 1;

		while (true) {
			HashMap<Set<String>, Integer> union = new HashMap<>();
			for (Map.Entry<Set<String>, Integer> A : result.entrySet()) {
				Set<String> X = (Set<String>) A.getKey();
				for (Map.Entry<Set<String>, Integer> B : result.entrySet()) {
					Set<String> Y = (Set<String>) B.getKey();

					Set<String> item = new HashSet<>();
					item.addAll(X);
					item.addAll(Y);

					if (item.size() > k) {
						union.put(item, 0);
					}
				}
			}

			for (int i = 0; i < transactionDatabase.size(); i++) {
				Set<String> transaction = new HashSet<String>();
				transaction = transactionDatabase.get(i);

				for (Map.Entry<Set<String>, Integer> newItem : union.entrySet()) {
					Set<String> item = (Set<String>) newItem.getKey();

					if (transaction.containsAll(item)) {
						union.put(item, union.get(item) + 1);
					}
				}
			}

			union = selectFrequentItems(union);

			if (union.size() == 0) {
				break;
			}

			result.putAll(union);
			printFrequentItems(union, k);

			k++;
		}

	}
	
	public static HashMap<Set<String>, Integer> selectFrequentItems(HashMap<Set<String>, Integer> result) {
		Iterator itr = result.entrySet().iterator();
		while (itr.hasNext()) {
			Map.Entry mapElement = (Map.Entry) itr.next();
			if ((int) mapElement.getValue() < minSupport) {
				itr.remove();
			}
		}

		return result;
	}

	public static void printFrequentItems(HashMap<Set<String>, Integer> result, int k) {
		System.out.println("K=" + (k + 1) + ":");
		for (Map.Entry<Set<String>, Integer> set : result.entrySet()) {
			System.out.println(set.getKey() + "\t: " + set.getValue());
		}
		System.out.println();
	}
	

	public static void getConfidence(HashMap<Set<String>, Integer> result) {
		String premise, conclusion;
		Scanner sc = new Scanner(System.in);
		Set<String> total = new HashSet<String>();
		double confidence = 0, premiseSupport = 0, combinedSupport = 0;

		System.out.print("Enter premises: ");
		premise = sc.nextLine();
		String[] items = premise.split(",");
		Set<String> premises = new HashSet<String>();
		for (String item : items) {
			premises.add(item);
		}
		System.out.print("Enter conclusion: ");
		conclusion = sc.nextLine();
		items = conclusion.split(",");
		Set<String> conclusions = new HashSet<String>();
		for (String item : items) {
			conclusions.add(item);
		}
		sc.close();

		total.addAll(premises);
		total.addAll(conclusions);
		
		for (Map.Entry<Set<String>, Integer> transaction : result.entrySet()) {
			Set<String> X = (Set<String>) transaction.getKey();
			if (X.equals(premises)) {
				premiseSupport = (int) transaction.getValue();
			}
			if (X.equals(total)) {
				combinedSupport = (int) transaction.getValue();
			}
			
		}

		if (premiseSupport != 0)
			confidence = (combinedSupport / premiseSupport)*100;

		System.out.println("Confidence of (" + premises + " -->" + conclusions + "): " + confidence + "%");
	}
}
