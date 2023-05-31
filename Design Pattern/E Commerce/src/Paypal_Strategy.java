

import java.util.Scanner;

class Paypal_Strategy implements PaymentStrategy{
    private String username;
    private String password;
   


    public Paypal_Strategy(){}
    
    public Paypal_Strategy(String username, String password){
        this.username=username;
        this.password=password;
    }
    
    public Paypal_Strategy getInfo(){
    	Scanner scanner=new Scanner(System.in);
        System.out.println("Enter username: ");
        username=scanner.nextLine();
        System.out.println("Enter password: ");
        password=scanner.nextLine();
        scanner.close();

        return new Paypal_Strategy(username,password);
    }
    
    public void processPayment(Double amount){
  
        System.out.println("Payment done using paypal, Amount deducted: "+amount);
    }
    
    
}
