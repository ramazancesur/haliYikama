package hali.pro.com.haliyikama.dto;

import java.util.Date;

import hali.pro.com.haliyikama.helper.BaseDTO;
import hali.pro.com.haliyikama.helper.EnumUtil;

/**
 * Created by ramazancesur on 01/05/2016.
 */
public class UrunDTO extends BaseDTO {
    private String productName;
    private double price;
    private Date sonKullanmaTarihi;
    private Date gelisTarihi;
    private String urunAciklamasi;
    private EnumUtil.UnitType unitType;

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

    public Date getSonKullanmaTarihi() {
        return sonKullanmaTarihi;
    }

    public void setSonKullanmaTarihi(Date sonKullanmaTarihi) {
        this.sonKullanmaTarihi = sonKullanmaTarihi;
    }

    public Date getGelisTarihi() {
        return gelisTarihi;
    }

    public void setGelisTarihi(Date gelisTarihi) {
        this.gelisTarihi = gelisTarihi;
    }

    public String getUrunAciklamasi() {
        return urunAciklamasi;
    }

    public void setUrunAciklamasi(String urunAciklamasi) {
        this.urunAciklamasi = urunAciklamasi;
    }

    public EnumUtil.UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(EnumUtil.UnitType unitType) {
        this.unitType = unitType;
    }
}