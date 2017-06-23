package hali.pro.com.haliyikama.DTO;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.Date;
import java.util.List;

import hali.pro.com.haliyikama.Annotation.IliskiAnnotation;
import hali.pro.com.haliyikama.Helper.BaseDTO;
import hali.pro.com.haliyikama.Helper.EnumUtil;

/**
 * Created by ramazancesur on 01/05/2016.
 */
public class SiparisListesiDTO extends BaseDTO {
    private MusteriDTO musteri;
    private double toplamSiparisBorcu;
    private String musteriNotu;
    private String saticiNotu;
    private Date beklenenTeslimatTarihi;
    private List<SiparisDTO> lstSiparisDTOS;
    private EnumUtil.SiparisDurum siparisDurum;

    public MusteriDTO getMusteri() {
        return musteri;
    }

    public void setMusteri(MusteriDTO musteri) {
        this.musteri = musteri;
    }

    public double getToplamSiparisBorcu() {
        return toplamSiparisBorcu;
    }

    public void setToplamSiparisBorcu(double toplamSiparisBorcu) {
        this.toplamSiparisBorcu = toplamSiparisBorcu;
    }

    public String getMusteriNotu() {
        return musteriNotu;
    }

    public void setMusteriNotu(String musteriNotu) {
        this.musteriNotu = musteriNotu;
    }

    public String getSaticiNotu() {
        return saticiNotu;
    }

    public void setSaticiNotu(String saticiNotu) {
        this.saticiNotu = saticiNotu;
    }

    public Date getBeklenenTeslimatTarihi() {
        return beklenenTeslimatTarihi;
    }

    public void setBeklenenTeslimatTarihi(Date beklenenTeslimatTarihi) {
        this.beklenenTeslimatTarihi = beklenenTeslimatTarihi;
    }

    public List<SiparisDTO> getLstSiparisDTOS() {
        return lstSiparisDTOS;
    }

    public void setLstSiparisDTOS(List<SiparisDTO> lstSiparisDTOS) {
        this.lstSiparisDTOS = lstSiparisDTOS;
    }

    public EnumUtil.SiparisDurum getSiparisDurum() {
        return siparisDurum;
    }

    public void setSiparisDurum(EnumUtil.SiparisDurum siparisDurum) {
        this.siparisDurum = siparisDurum;
    }
}