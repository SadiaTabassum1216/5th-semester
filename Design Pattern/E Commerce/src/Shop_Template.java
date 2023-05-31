import java.util.Scanner;

public abstract class Shop_Template {
    protected Mediator mediator;
    String name;
    String password;
    String email;
    String address;
  
    public Shop_Template(Mediator mediator){
        this.mediator=mediator;
    }
    

    
//template method
    public final void purchase() {
  
      	Scanner scanner=new Scanner(System.in); 
 
        System.out.println("Enter name: ");
        name=scanner.nextLine();

        System.out.println("Enter email: ");
        email=scanner.nextLine();

        System.out.println("Enter password: ");
        password=scanner.nextLine();

        System.out.println("Enter address: ");
        address=scanner.nextLine();
        
        User user = new User(name,email,password,address);
        displayProduct();
        
        int index=0;
        String input="";


        System.out.println("Add to cart: ");

        while(true) {
            System.out.print("Enter product number (type 'checkout' to exit): ");
            input = scanner.nextLine();

            if(input.equalsIgnoreCase("checkout")) {
                break;
            }
            try {
                int number = Integer.parseInt(input);
                addProductToCart(number-1);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
        
            
        System.out.println("Enter payment method: ");

        System.out.println("1. Credit Card\n2. Paypal\n3. Cryptocurrency");

        index=scanner.nextInt();
       // scanner.close();
        PaymentStrategy paymentStrategy=null;

        if(index==1)    {paymentStrategy=new CreditCard_Strategy();}
        else if(index==2)   {paymentStrategy=new Paypal_Strategy();}
        else if(index==3)   {paymentStrategy=new Crypto_Strategy();}

        processPayment(paymentStrategy);

        System.out.println("Thank you!");
    }
    
    
//steps
    protected abstract void displayProduct();
    protected abstract void addProductToCart(int index);
    protected abstract void processPayment(PaymentStrategy paymentMethod);
   
}

