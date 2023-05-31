class Concrete_shop extends Shop_Template {
    public Concrete_shop(Mediator mediator){
        super(mediator);
    }

    @Override
    public void displayProduct() {
    	System.out.println("Product List:");
        int i=1;
        for(Product product: mediator.getProductList()){
            System.out.println(i+". " + product.getName() + ":\t" + product.getDescription() + "\t\t $" + product.getPrice());
            i++;
        }
        System.out.println("\n");
    }


    public void addProductToCart(int index){
        mediator.addToCart(mediator.getProductList().get(index));
        System.out.println("Cart is: ");
        for(Product product: mediator.getCart()){
            System.out.println(product.getName() + " : $" + product.getPrice());
        }
        System.out.println();
    }
    

    @Override
    public void processPayment(PaymentStrategy paymentMethod) {
        if(mediator.getCart().isEmpty()){
            System.out.println("Cart is empty!");
            return;
        }
        mediator.setPaymentMethod(paymentMethod);
        System.out.println("Product bought with price: " + mediator.processOrder());
        paymentMethod.processPayment(mediator.processOrder());
    }
    

}
