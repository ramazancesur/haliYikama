package hali.pro.com.haliyikama.DTO;

import hali.pro.com.haliyikama.Annotation.IliskiAnnotation;
import hali.pro.com.haliyikama.Helper.BaseDTO;

/**
 * Created by ramazancesur on 02/05/2017.
 */
public class Siparis extends BaseDTO {
    @IliskiAnnotation
    private Urun urun;
    private int metre;
    @IliskiAnnotation
    private SiparisListesi siparisListesi;

    public Urun getUrun() {
        return urun;
    }

    public void setUrun(Urun urun) {
        this.urun = urun;
    }

    public int getMetre() {
        return metre;
    }

    public void setMetre(int metre) {
        this.metre = metre;
    }

    public SiparisListesi getSiparisListesi() {
        return siparisListesi;
    }

    public void setSiparisListesi(SiparisListesi siparisListesi) {
        this.siparisListesi = siparisListesi;
    }
}