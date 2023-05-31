package factoryMethod;

public class Tshirt implements Clothes{

	@Override
	public void buy() {
		System.out.println("Tshirt is sold");
	}

	@Override
	public String toString() {
		return "Tshirt";
	}

}
