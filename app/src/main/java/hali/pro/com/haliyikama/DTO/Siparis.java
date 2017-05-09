package hali.pro.com.haliyikama.DTO;

import hali.pro.com.haliyikama.Annotation.IliskiAnnotation;
import hali.pro.com.haliyikama.Helper.BaseDTO;

/**
 * Created by ramazancesur on 02/05/2017.
 */
public class Siparis extends BaseDTO {
    @IliskiAnnotation
    private UrunDTO urunDTO;
    private int metre;
    @IliskiAnnotation
    private SiparisListesiDTO siparisListesiDTO;

    public UrunDTO getUrunDTO() {
        return urunDTO;
    }

    public void setUrunDTO(UrunDTO urunDTO) {
        this.urunDTO = urunDTO;
    }

    public int getMetre() {
        return metre;
    }

    public void setMetre(int metre) {
        this.metre = metre;
    }

    public SiparisListesiDTO getSiparisListesiDTO() {
        return siparisListesiDTO;
    }

    public void setSiparisListesiDTO(SiparisListesiDTO siparisListesiDTO) {
        this.siparisListesiDTO = siparisListesiDTO;
    }
}