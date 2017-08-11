package hali.pro.com.haliyikama.serverapplication;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import hali.pro.com.haliyikama.helper.EnumUtil;

/**
 * Created by ramazancesur on 11/08/2017.
 */

public class User {
    private Long oid;
    private Long lastUpdated;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date updatedDate;
    private EnumUtil.EntityState entityState;

    private String userName;
    private String password;
    private String soyadi;
    private String adi;
    private EnumUtil.UserType userType;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public EnumUtil.EntityState getEntityState() {
        return entityState;
    }

    public void setEntityState(EnumUtil.EntityState entityState) {
        this.entityState = entityState;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSoyadi() {
        return soyadi;
    }

    public void setSoyadi(String soyadi) {
        this.soyadi = soyadi;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public EnumUtil.UserType getUserType() {
        return userType;
    }

    public void setUserType(EnumUtil.UserType userType) {
        this.userType = userType;
    }
}