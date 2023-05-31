package factoryMethod;

public class Shirt implements Clothes{

	@Override
	public void buy() {
		System.out.println("Shirt is sold");
	}

	@Override
	public String toString() {
		return "Shirt";
	}
	

}
