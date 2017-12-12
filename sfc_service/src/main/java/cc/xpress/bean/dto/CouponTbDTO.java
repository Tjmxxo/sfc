package cc.xpress.bean.dto;

import cc.xpress.annotation.EntityId;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-27 23:32
 * @modified By:
 */
public class CouponTbDTO implements Serializable {
    @EntityId
    private Integer couponId;
    private String couponNo;
    private int couponMoney;
    private int couponMinMoney;
    private Timestamp couponTime;
    private int couponStatus;
    @JSONField(serialize = false)
    private UserTbDTO userTbDTO;
    @JSONField(serialize = false)
    private CinemaTbDTO cinemaTbDTO;

    public CouponTbDTO() {
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public int getCouponMoney() {
        return couponMoney;
    }

    public void setCouponMoney(int couponMoney) {
        this.couponMoney = couponMoney;
    }

    public int getCouponMinMoney() {
        return couponMinMoney;
    }

    public void setCouponMinMoney(int couponMinMoney) {
        this.couponMinMoney = couponMinMoney;
    }

    public Timestamp getCouponTime() {
        return couponTime;
    }

    public void setCouponTime(Timestamp couponTime) {
        this.couponTime = couponTime;
    }

    public int getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(int couponStatus) {
        this.couponStatus = couponStatus;
    }

    public UserTbDTO getUserTbDTO() {
        return userTbDTO;
    }

    public void setUserTbDTO(UserTbDTO userTbDTO) {
        this.userTbDTO = userTbDTO;
    }

    public CinemaTbDTO getCinemaTbDTO() {
        return cinemaTbDTO;
    }

    public void setCinemaTbDTO(CinemaTbDTO cinemaTbDTO) {
        this.cinemaTbDTO = cinemaTbDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CouponTbDTO)) return false;

        CouponTbDTO that = (CouponTbDTO) o;

        if (getCouponMoney() != that.getCouponMoney()) return false;
        if (getCouponMinMoney() != that.getCouponMinMoney()) return false;
        if (getCouponStatus() != that.getCouponStatus()) return false;
        if (getCouponId() != null ? !getCouponId().equals(that.getCouponId()) : that.getCouponId() != null)
            return false;
        if (getCouponNo() != null ? !getCouponNo().equals(that.getCouponNo()) : that.getCouponNo() != null)
            return false;
        return getCouponTime() != null ? getCouponTime().equals(that.getCouponTime()) : that.getCouponTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getCouponId() != null ? getCouponId().hashCode() : 0;
        result = 31 * result + (getCouponNo() != null ? getCouponNo().hashCode() : 0);
        result = 31 * result + getCouponMoney();
        result = 31 * result + getCouponMinMoney();
        result = 31 * result + (getCouponTime() != null ? getCouponTime().hashCode() : 0);
        result = 31 * result + getCouponStatus();
        return result;
    }
}
