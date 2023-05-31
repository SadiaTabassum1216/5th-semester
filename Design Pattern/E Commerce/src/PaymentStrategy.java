

public interface PaymentStrategy {
    
    public PaymentStrategy getInfo();
    public void processPayment(Double price);
   
}
