package factoryMethod;

public class Main {

	public static void main(String[] args) {
		Singleton object= Singleton.create();
		object.add(new Shirt());
		object.add(new Shirt());
		object.add(new Shirt());
		object.add(new Pant());
		
		
		
		GarmentsFactory factory=new GarmentsFactory();
		Clothes cloth1=factory.create("Shirt");
		cloth1.buy();
		Clothes cloth2=factory.create("Pant");
		cloth2.buy();
		Clothes cloth3=factory.create("Tshirt");
		cloth3.buy();

	}

}
