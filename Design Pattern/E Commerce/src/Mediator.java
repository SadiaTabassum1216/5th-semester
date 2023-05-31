import java.util.List;

public interface Mediator {
    public void addProduct(Product product);
    public void addToCart(Product product);
    public Double processOrder();
    public List<Product> getProductList();
    public List<Product> getCart();
    public void setPaymentMethod(PaymentStrategy paymentMethod);
}