package hali.pro.com.haliyikama.DTO;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import java.util.List;

import hali.pro.com.haliyikama.Helper.BaseDTO;

/**
 * Created by ramazancesur on 23/06/2017.
 */
public class SirketDTO extends BaseDTO {
    private String sirketAdi;
    private String logoPath;
    private List<String> sirketLisansKey;
    private List<AdresTelefon> lstAdresTel;
    private Long userOid;




    public String getSirketAdi() {
        return sirketAdi;
    }

    public void setSirketAdi(String sirketAdi) {
        this.sirketAdi = sirketAdi;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public List<String> getSirketLisansKey() {
        return sirketLisansKey;
    }

    public void setSirketLisansKey(List<String> sirketLisansKey) {
        this.sirketLisansKey = sirketLisansKey;
    }

    public List<AdresTelefon> getLstAdresTel() {
        return lstAdresTel;
    }

    public void setLstAdresTel(List<AdresTelefon> lstAdresTel) {
        this.lstAdresTel = lstAdresTel;
    }

    public Long getUserOid() {
        return userOid;
    }

    public void setUserOid(Long userOid) {
        this.userOid = userOid;
    }
}