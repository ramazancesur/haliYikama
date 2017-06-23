package hali.pro.com.haliyikama.DTO;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import hali.pro.com.haliyikama.Annotation.IliskiAnnotation;
import hali.pro.com.haliyikama.Helper.BaseDTO;

/**
 * Created by ramazancesur on 02/05/2017.
 */
public class SiparisDTO extends BaseDTO {
    private UrunDTO urun;
    private int metre;
    private int adet;
    private Double ucreti;
    private Long borcOid;



    public UrunDTO getUrun() {
        return urun;
    }

    public void setUrun(UrunDTO urun) {
        this.urun = urun;
    }

    public int getMetre() {
        return metre;
    }

    public void setMetre(int metre) {
        this.metre = metre;
    }

    public int getAdet() {
        return adet;
    }

    public void setAdet(int adet) {
        this.adet = adet;
    }

    public Double getUcreti() {
        return ucreti;
    }

    public void setUcreti(Double ucreti) {
        this.ucreti = ucreti;
    }

    public Long getBorcOid() {
        return borcOid;
    }

    public void setBorcOid(Long borcOid) {
        this.borcOid = borcOid;
    }
}