package hali.pro.com.haliyikama.DTO;

import java.util.Date;
import java.util.List;

/**
 * Created by ramazancesur on 01/05/2016.
 */
public class SiparisListesiDTO extends BaseDTO {
    private MusteriDTO musteri;
    private double totalammount;
    private List<UrunDTO> urunDTOList;
    //Müşteri Sipariş Notu
    private String orderNotesCustom;
    //Satıcı Sipariş Notu
    private String orderNotesSeller;
    //Kalan Borç
    private double arreas;
    //Teslimat Tarihi
    private Date deliveryDate;

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

    public MusteriDTO getMusteri() {
        return musteri;
    }

    public void setMusteri(MusteriDTO musteri) {
        this.musteri = musteri;
    }

    public double getTotalammount() {
        return totalammount;
    }

    public void setTotalammount(double totalammount) {
        this.totalammount = totalammount;
    }

    public List<UrunDTO> getUrunDTOList() {
        return urunDTOList;
    }

    public void setUrunDTOList(List<UrunDTO> urunDTOList) {
        this.urunDTOList = urunDTOList;
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
}
