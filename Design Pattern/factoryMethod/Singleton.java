package factoryMethod;

import java.util.ArrayList;

public class Singleton {
	private static Singleton object;

    ArrayList<Clothes>database=new ArrayList<>();
	
	private Singleton() {
	}
	
	public static Singleton create() {
		if(object==null)
			object=new Singleton();
		return object;
	}
	
	 public void add(Clothes cloth){
		 database.add(cloth);
	      System.out.println(cloth+" is added");
	   }


}
