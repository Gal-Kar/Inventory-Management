package Model;

public class ProductProfit {
    String productCode;
    String productName;
    int profit;

    public ProductProfit(String productCode, String productName, int profit) {
        this.productCode = productCode;
        this.productName = productName;
        this.profit = profit;
    }

    public String getProductCode() {
        return productCode;
    }


    public String getProductName() {
        return productName;
    }



    public int getProfit() {
        return profit;
    }
}
