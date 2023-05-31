public class Retailer {

    private Mediator mediator;
    public Retailer(){
        Product product1=new Product("Fresh Produce Bundle","Nutritious, delicious, in-season variety",299.00,20);
        Product product2=new Product("Whole Grain Bread","Hearty, nutritious, freshly baked",499.00,34);
        Product product3=new Product("Premium Coffee Beans","Rich, flavorful, sourced globally",129.00,45);
        Product product4=new Product("Organic Eggs","Free-range, healthy, protein-packed",50.00,122);

        Mediator mediator=new ConcreteMediator();
        mediator.addProduct(product1);
        mediator.addProduct(product2);
        mediator.addProduct(product3);
        mediator.addProduct(product4);
        this.mediator=mediator;
    }
    public Mediator getMediator(){
        return this.mediator;
    }
}

