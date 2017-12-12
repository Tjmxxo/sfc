package cc.xpress.bean.dto;

import cc.xpress.annotation.EntityId;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Set;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-27 23:32
 * @modified By:
 */
public class CinemaTbDTO implements Serializable {
    @EntityId
    private Integer cinemaId;
    private String cinemaName;
    private String cinemaDescribe;
    private String cinemaAddress;
    private String cinemaTel;
    private String cinemaBusinessHours;
    private String cinemaLongitude;
    private String cinemaLatitude;
    private String cinemaImg;
    @JSONField(serialize = false)
    private CityTbDTO cityTbDTO;
    @JSONField(serialize = false)
    private Set<VipTbDTO> vipTbDTOSet;
    @JSONField(serialize = false)
    private Set<HallTbDTO> hallTbDTOSet;
    @JSONField(serialize = false)
    private Set<CouponTbDTO> couponTbDTOSet;

    public CinemaTbDTO() {
    }

    public Integer getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Integer cinemaId) {
        this.cinemaId = cinemaId;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaDescribe() {
        return cinemaDescribe;
    }

    public void setCinemaDescribe(String cinemaDescribe) {
        this.cinemaDescribe = cinemaDescribe;
    }

    public String getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }

    public String getCinemaTel() {
        return cinemaTel;
    }

    public void setCinemaTel(String cinemaTel) {
        this.cinemaTel = cinemaTel;
    }

    public String getCinemaBusinessHours() {
        return cinemaBusinessHours;
    }

    public void setCinemaBusinessHours(String cinemaBusinessHours) {
        this.cinemaBusinessHours = cinemaBusinessHours;
    }

    public String getCinemaLongitude() {
        return cinemaLongitude;
    }

    public void setCinemaLongitude(String cinemaLongitude) {
        this.cinemaLongitude = cinemaLongitude;
    }

    public String getCinemaLatitude() {
        return cinemaLatitude;
    }

    public void setCinemaLatitude(String cinemaLatitude) {
        this.cinemaLatitude = cinemaLatitude;
    }

    public String getCinemaImg() {
        return cinemaImg;
    }

    public void setCinemaImg(String cinemaImg) {
        this.cinemaImg = cinemaImg;
    }

    public CityTbDTO getCityTbDTO() {
        return cityTbDTO;
    }

    public void setCityTbDTO(CityTbDTO cityTbDTO) {
        this.cityTbDTO = cityTbDTO;
    }

    public Set<VipTbDTO> getVipTbDTOSet() {
        return vipTbDTOSet;
    }

    public void setVipTbDTOSet(Set<VipTbDTO> vipTbDTOSet) {
        this.vipTbDTOSet = vipTbDTOSet;
    }

    public Set<HallTbDTO> getHallTbDTOSet() {
        return hallTbDTOSet;
    }

    public void setHallTbDTOSet(Set<HallTbDTO> hallTbDTOSet) {
        this.hallTbDTOSet = hallTbDTOSet;
    }

    public Set<CouponTbDTO> getCouponTbDTOSet() {
        return couponTbDTOSet;
    }

    public void setCouponTbDTOSet(Set<CouponTbDTO> couponTbDTOSet) {
        this.couponTbDTOSet = couponTbDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CinemaTbDTO)) return false;

        CinemaTbDTO that = (CinemaTbDTO) o;

        if (getCinemaId() != null ? !getCinemaId().equals(that.getCinemaId()) : that.getCinemaId() != null)
            return false;
        if (getCinemaName() != null ? !getCinemaName().equals(that.getCinemaName()) : that.getCinemaName() != null)
            return false;
        if (getCinemaDescribe() != null ? !getCinemaDescribe().equals(that.getCinemaDescribe()) : that.getCinemaDescribe() != null)
            return false;
        if (getCinemaAddress() != null ? !getCinemaAddress().equals(that.getCinemaAddress()) : that.getCinemaAddress() != null)
            return false;
        if (getCinemaTel() != null ? !getCinemaTel().equals(that.getCinemaTel()) : that.getCinemaTel() != null)
            return false;
        if (getCinemaBusinessHours() != null ? !getCinemaBusinessHours().equals(that.getCinemaBusinessHours()) : that.getCinemaBusinessHours() != null)
            return false;
        if (getCinemaLongitude() != null ? !getCinemaLongitude().equals(that.getCinemaLongitude()) : that.getCinemaLongitude() != null)
            return false;
        if (getCinemaLatitude() != null ? !getCinemaLatitude().equals(that.getCinemaLatitude()) : that.getCinemaLatitude() != null)
            return false;
        return getCinemaImg() != null ? getCinemaImg().equals(that.getCinemaImg()) : that.getCinemaImg() == null;
    }

    @Override
    public int hashCode() {
        int result = getCinemaId() != null ? getCinemaId().hashCode() : 0;
        result = 31 * result + (getCinemaName() != null ? getCinemaName().hashCode() : 0);
        result = 31 * result + (getCinemaDescribe() != null ? getCinemaDescribe().hashCode() : 0);
        result = 31 * result + (getCinemaAddress() != null ? getCinemaAddress().hashCode() : 0);
        result = 31 * result + (getCinemaTel() != null ? getCinemaTel().hashCode() : 0);
        result = 31 * result + (getCinemaBusinessHours() != null ? getCinemaBusinessHours().hashCode() : 0);
        result = 31 * result + (getCinemaLongitude() != null ? getCinemaLongitude().hashCode() : 0);
        result = 31 * result + (getCinemaLatitude() != null ? getCinemaLatitude().hashCode() : 0);
        result = 31 * result + (getCinemaImg() != null ? getCinemaImg().hashCode() : 0);
        return result;
    }
}
