package hali.pro.com.haliyikama.Helper;

import java.io.Serializable;

/**
 * Created by ramazancesur on 05/06/2016.
 */
public class Kisi implements Serializable {
    private String isim;
    private boolean kadinMi;

    public Kisi(String isim, boolean kadinMi) {
        super();
        this.kadinMi = kadinMi;
        this.isim = isim;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public boolean isKadinMi() {
        return kadinMi;
    }

    public void setKadinMi(boolean kadinMi) {
        this.kadinMi = kadinMi;
    }
}
