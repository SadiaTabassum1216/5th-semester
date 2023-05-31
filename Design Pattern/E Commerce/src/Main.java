

public class Main {
    public static void main(String[] args) {

        Retailer retailer=new Retailer();

            Shop_Template onlineStore=new Concrete_shop(retailer.getMediator());
            onlineStore.purchase();
  
    }
}