package hali.pro.com.haliyikama.dto;

import java.util.Date;

import hali.pro.com.haliyikama.helper.BaseDTO;

/**
 * Created by ramazancesur on 23/06/2017.
 */
public class OdemeDTO extends BaseDTO {
    private Long musteriOid;
    private Date odemeTarihi;
    private Double odemeMiktari;
    private Double kalanMiktar;
    private String musteriAdi;
    private String musteriSoyadi;
    private Long borcOid;

    public Long getMusteriOid() {
        return musteriOid;
    }

    public void setMusteriOid(Long musteriOid) {
        this.musteriOid = musteriOid;
    }

    public Date getOdemeTarihi() {
        return odemeTarihi;
    }

    public void setOdemeTarihi(Date odemeTarihi) {
        this.odemeTarihi = odemeTarihi;
    }

    public Double getOdemeMiktari() {
        return odemeMiktari;
    }

    public void setOdemeMiktari(Double odemeMiktari) {
        this.odemeMiktari = odemeMiktari;
    }

    public Double getKalanMiktar() {
        return kalanMiktar;
    }

    public void setKalanMiktar(Double kalanMiktar) {
        this.kalanMiktar = kalanMiktar;
    }

    public String getMusteriAdi() {
        return musteriAdi;
    }

    public void setMusteriAdi(String musteriAdi) {
        this.musteriAdi = musteriAdi;
    }

    public String getMusteriSoyadi() {
        return musteriSoyadi;
    }

    public void setMusteriSoyadi(String musteriSoyadi) {
        this.musteriSoyadi = musteriSoyadi;
    }

    public Long getBorcOid() {
        return borcOid;
    }

    public void setBorcOid(Long borcOid) {
        this.borcOid = borcOid;
    }
}