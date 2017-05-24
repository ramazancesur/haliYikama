package hali.pro.com.haliyikama.DTO;

import java.util.Date;
import java.util.List;

import hali.pro.com.haliyikama.Annotation.IliskiAnnotation;
import hali.pro.com.haliyikama.Helper.BaseDTO;

/**
 * Created by ramazancesur on 01/05/2016.
 */
public class SiparisListesi extends BaseDTO {
    @IliskiAnnotation
    private Musteri musteri;
    private double totalammount;
    //Müşteri Sipariş Notu
    private String orderNotesCustom;
    //Satıcı Sipariş Notu
    private String orderNotesSeller;
    //Kalan Borç
    private double arreas;
    //Teslimat Tarihi
    private Date deliveryDate;
    @IliskiAnnotation
    private List<Siparis> lstSiparis;


    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public double getTotalammount() {
        return totalammount;
    }

    public void setTotalammount(double totalammount) {
        this.totalammount = totalammount;
    }

    public String getOrderNotesCustom() {
        return orderNotesCustom;
    }

    public void setOrderNotesCustom(String orderNotesCustom) {
        this.orderNotesCustom = orderNotesCustom;
    }

    public String getOrderNotesSeller() {
        return orderNotesSeller;
    }

    public void setOrderNotesSeller(String orderNotesSeller) {
        this.orderNotesSeller = orderNotesSeller;
    }

    public double getArreas() {
        return arreas;
    }

    public void setArreas(double arreas) {
        this.arreas = arreas;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<Siparis> getLstSiparis() {
        return lstSiparis;
    }

    public void setLstSiparis(List<Siparis> lstSiparis) {
        this.lstSiparis = lstSiparis;
    }
}
