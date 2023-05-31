package factoryMethod;

public class GarmentsFactory {
	public Clothes create(String type) {
		if(type.equals("Shirt"))
			return new Shirt();
		
		else if(type.equals("Pant"))
			return new Pant();
		
		else if(type.equals("Tshirt"))
			return new Tshirt();
		
		else
			return null;
		
	}
	
}
