package hali.pro.com.haliyikama.Helper;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ramazancesur on 01/05/2016.
 */
public class BaseDTO extends Model implements Serializable{
    @Column
    private EnumUtil.EntityState entityState;
    @Column
    private Date createdDate;
    @Column
    private Date updatedDate;
    @Column
    private Long oid;

    public EnumUtil.EntityState getEntityState() {
        return entityState;
    }

    public void setEntityState(EnumUtil.EntityState entityState) {
        this.entityState = entityState;
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

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }
}