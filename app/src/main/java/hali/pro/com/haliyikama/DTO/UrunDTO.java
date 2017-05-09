package hali.pro.com.haliyikama.DTO;

import hali.pro.com.haliyikama.Helper.BaseDTO;

/**
 * Created by ramazancesur on 01/05/2016.
 */
public class UrunDTO extends BaseDTO {
    private String productName;
    private double price;
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

    public String getCommingDate() {
        return commingDate;
    }

    public void setCommingDate(String commingDate) {
        this.commingDate = commingDate;
    }
}