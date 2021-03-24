package Model;

import java.io.Serializable;

public class Product implements Serializable {
    private String productName;
    private int costForStore;
    private int costForClient;
    private Client client;
    private String code;

    public Product(String productName, int costForStore,int costForClient, Client client,String code) {
        this.productName = productName;
        this.costForStore = costForStore;
        this.client = client;
        this.costForClient=costForClient;
        this.code=code;
    }

    public Client getClient() {
        return client;
    }


    public String getProductName(){
        return productName;
    }

    public int getProfit(){
        return costForClient-costForStore;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Product " +
                "product=" + productName  +
                ", cost for store=" + costForStore +
                ", cost for client=" + costForClient +
                "\n" + client +
                "\n";
    }
}
