package hali.pro.com.haliyikama.DTO;

/**
 * Created by ramazancesur on 01/05/2016.
 */
public class UrunDTO extends BaseDTO {
    private String productName;
    private double price;
    //Kalan
    private int remain;
    //Geliş Tarihi
    private String commingDate;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRemain() {
        return remain;
    }

    public void setRemain(int remain) {
        this.remain = remain;
    }

    public String getCommingDate() {
        return commingDate;
    }

    public void setCommingDate(String commingDate) {
        this.commingDate = commingDate;
    }
}