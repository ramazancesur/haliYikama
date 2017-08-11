package hali.pro.com.haliyikama.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

import hali.pro.com.haliyikama.helper.BaseDTO;

/**
 * Created by ramazancesur on 23/06/2017.
 */
public class SirketDTO extends BaseDTO {
    private String sirketAdi;
    private String logoPath;
    private String sirketLisansKey;
    private List<AdresTelefon> lstAdresTel;
    private Long userOid;
    // 06.08.2017 tarihinde eklendi
    private String encodedImages;
    @JsonIgnore
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date lisansEndTimes;
    @JsonIgnore
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date lisansStartTimes;
    private int kalanSms;
    private String androidLogoPath;
    private boolean changeImage;

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

    public String getSirketLisansKey() {
        return sirketLisansKey;
    }

    public void setSirketLisansKey(String sirketLisansKey) {
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

    public String getEncodedImages() {
        return encodedImages;
    }

    public void setEncodedImages(String encodedImages) {
        this.encodedImages = encodedImages;
    }

    public Date getLisansEndTimes() {
        return lisansEndTimes;
    }

    public void setLisansEndTimes(Date lisansEndTimes) {
        this.lisansEndTimes = lisansEndTimes;
    }

    public Date getLisansStartTimes() {
        return lisansStartTimes;
    }

    public void setLisansStartTimes(Date lisansStartTimes) {
        this.lisansStartTimes = lisansStartTimes;
    }

    public int getKalanSms() {
        return kalanSms;
    }

    public void setKalanSms(int kalanSms) {
        this.kalanSms = kalanSms;
    }

    public String getAndroidLogoPath() {
        return androidLogoPath;
    }

    public void setAndroidLogoPath(String androidLogoPath) {
        this.androidLogoPath = androidLogoPath;
    }

    public boolean isChangeImage() {
        return changeImage;
    }

    public void setChangeImage(boolean changeImage) {
        this.changeImage = changeImage;
    }
}