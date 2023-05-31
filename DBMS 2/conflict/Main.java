package conflict;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		ArrayList<String> input = new ArrayList<String>();

		File file = new File("E:\\Codes\\semester 5\\Java\\DBMS\\src\\input.txt");
		Scanner Reader = new Scanner(file);
		while (Reader.hasNextLine()) {
			input.add(Reader.nextLine());
		}
//	      System.out.println(input.size());

		Serialization serialization = new Serialization();
		serialization.createGraph(input);
		if (serialization.DFS())
			System.out.println("Conflict serialization not possible");

		else
			System.out.println("Conflict serialization possible");

		Reader.close();
	}
}