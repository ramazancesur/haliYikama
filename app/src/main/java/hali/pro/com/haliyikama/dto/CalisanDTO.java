package hali.pro.com.haliyikama.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;
import java.util.List;

import hali.pro.com.haliyikama.helper.BaseDTO;
import hali.pro.com.haliyikama.helper.CustomerDateAndTimeDeserialize;
import hali.pro.com.haliyikama.helper.EnumUtil;

/**
 * Created by ramazancesur on 01/05/2016.
 */
public class CalisanDTO extends BaseDTO {
    private EnumUtil.EmployeeType employeeType;
    private String ad;
    private String soyad;
    private String kullaniciAdi;
    private String sifre;
    private List<AdresTelefon> lstAddresTel;
    @JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
    private Date iseGirisTarihi;


    public EnumUtil.EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EnumUtil.EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public List<AdresTelefon> getLstAddresTel() {
        return lstAddresTel;
    }

    public void setLstAddresTel(List<AdresTelefon> lstAddresTel) {
        this.lstAddresTel = lstAddresTel;
    }

    public Date getIseGirisTarihi() {
        return iseGirisTarihi;
    }

    public void setIseGirisTarihi(Date iseGirisTarihi) {
        this.iseGirisTarihi = iseGirisTarihi;
    }
}