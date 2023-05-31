package factoryMethod;

public class Pant implements Clothes{

	@Override
	public void buy() {
		System.out.println("Pant is sold");
	}

	@Override
	public String toString() {
		return "Pant";
	}

}
