import java.util.ArrayList;
import java.util.List;

class ConcreteMediator implements Mediator{
    private List<Product> products=new ArrayList<>();
    private List<Product> cart=new ArrayList<>();
    private PaymentStrategy paymentStrategy;

    public void addProduct(Product product){
        products.add(product);
    }

    public void addToCart(Product product){
        cart.add(product);
        product.decreaseInventory();
    }

    public void setPaymentMethod(PaymentStrategy paymentMethod){
        this.paymentStrategy=paymentMethod;
        paymentStrategy.getInfo();
    }

    public Double processOrder(){
        Double price=0.0;
        for(Product product:cart){
            price+=product.getPrice();
        }
        return price;
    }

    public List<Product> getProductList(){
        return products;
    }
    public List<Product> getCart(){
        return cart;
    }

}