

import java.util.Scanner;

class Crypto_Strategy implements PaymentStrategy{
    private String walletNumber;
    private String transactionToken;
    Scanner scanner=new Scanner(System.in);

    public Crypto_Strategy(){

    }
    public Crypto_Strategy(String walletNumber, String transactionToken){
        this.walletNumber=walletNumber;
    }
    
    public Crypto_Strategy getInfo(){
        System.out.println("Enter walletNumber: ");
        walletNumber=scanner.nextLine();
        System.out.println("Enter Token: ");
        transactionToken=scanner.nextLine();

        return new Crypto_Strategy(walletNumber,transactionToken);
    }
    public void processPayment(Double amount){
        System.out.println("Payment done using paypal, Amount deducted: "+amount);
    }
   
}