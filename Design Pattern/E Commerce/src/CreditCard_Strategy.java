

import java.util.Scanner;

class CreditCard_Strategy implements PaymentStrategy{

    private String cardNumber;
    private String cvv;
    private String expirationDate;
    private Double deductedAmount;
   

    public CreditCard_Strategy() {}

    public CreditCard_Strategy(String cardNumber, String cvv, String expirationDate){
        this.cardNumber=cardNumber;
        this.cvv=cvv;
        this.expirationDate=expirationDate;
    }
    
    public CreditCard_Strategy getInfo(){
    	Scanner scanner=new Scanner(System.in);
        System.out.println("Enter card Number: ");
        cardNumber=scanner.nextLine();
        System.out.println("Enter cvv: ");
        cvv=scanner.nextLine();
        System.out.println("Enter expirationDate: ");
        expirationDate=scanner.nextLine();

        return new CreditCard_Strategy(cardNumber,cvv,expirationDate);
    }
    
    public void processPayment(Double amount){
        deductedAmount=amount;
        System.out.println("Payment done using credit card, Paid: "+deductedAmount);
    }
    
}

