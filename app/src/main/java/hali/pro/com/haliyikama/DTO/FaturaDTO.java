package hali.pro.com.haliyikama.DTO;


import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import hali.pro.com.haliyikama.Annotation.IliskiAnnotation;
import hali.pro.com.haliyikama.Helper.BaseDTO;

/**
 * Created by ramazancesur on 01/05/2016.
 */
public class FaturaDTO extends BaseDTO {
    private String sirketAdi;
    private String sirketLogoYolu;
    private double faturaTutari;
    private String faturaNotu;
    private SiparisListesiDTO siparisListesi;
    private MusteriDTO musteri;
    private Long borcOid;
    private Long employeeOid;

    public String getSirketAdi() {
        return sirketAdi;
    }

    public void setSirketAdi(String sirketAdi) {
        this.sirketAdi = sirketAdi;
    }

    public String getSirketLogoYolu() {
        return sirketLogoYolu;
    }

    public void setSirketLogoYolu(String sirketLogoYolu) {
        this.sirketLogoYolu = sirketLogoYolu;
    }

    public double getFaturaTutari() {
        return faturaTutari;
    }

    public void setFaturaTutari(double faturaTutari) {
        this.faturaTutari = faturaTutari;
    }

    public String getFaturaNotu() {
        return faturaNotu;
    }

    public void setFaturaNotu(String faturaNotu) {
        this.faturaNotu = faturaNotu;
    }

    public SiparisListesiDTO getSiparisListesi() {
        return siparisListesi;
    }

    public void setSiparisListesi(SiparisListesiDTO siparisListesi) {
        this.siparisListesi = siparisListesi;
    }

    public MusteriDTO getMusteri() {
        return musteri;
    }

    public void setMusteri(MusteriDTO musteri) {
        this.musteri = musteri;
    }

    public Long getBorcOid() {
        return borcOid;
    }

    public void setBorcOid(Long borcOid) {
        this.borcOid = borcOid;
    }

    public Long getEmployeeOid() {
        return employeeOid;
    }

    public void setEmployeeOid(Long employeeOid) {
        this.employeeOid = employeeOid;
    }
}