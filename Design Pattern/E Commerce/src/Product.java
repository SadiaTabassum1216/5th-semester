
public class Product {
    private String name;
    private String description;
    private Double price;

    private Integer inventory;

    public Product(String name, String description, Double price, Integer inventory){
        this.name=name;
        this.price=price;
        this.description=description;
        this.inventory=inventory;
    }

    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }
    public Double getPrice(){
        return this.price;
    }
    public Integer getInventory(){
        return this.inventory;
    }
    public void decreaseInventory(){
        this.inventory--;
    }
}
