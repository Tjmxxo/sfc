package cc.xpress.bean.dto;

import cc.xpress.annotation.EntityId;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-27 23:32
 * @modified By:
 */
public class UserTbDTO implements Serializable {
    @EntityId
    private Integer userId;
    private String userAccount;
    private String userPassword;
    private String userSalt;
    private String userEmail;
    private String userTel;
    private String userImg;
    private int userStatus;
    private Timestamp userRegisterTime;
    @JSONField(serialize = false)
    private CinemaTbDTO cinemaTbDTO;
    @JSONField(serialize = false)
    private Set<RoleTbDTO> roleTbDTOSet;
    @JSONField(serialize = false)
    private Set<CouponTbDTO> couponTbDTOSet;
    @JSONField(serialize = false)
    private Set<VipTbDTO> vipTbDTOSet;
    @JSONField(serialize = false)
    private Set<OrderTbDTO> orderTbDTOSet;

    public UserTbDTO() {
    }

    public CinemaTbDTO getCinemaTbDTO() {
        return cinemaTbDTO;
    }

    public void setCinemaTbDTO(CinemaTbDTO cinemaTbDTO) {
        this.cinemaTbDTO = cinemaTbDTO;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserSalt() {
        return userSalt;
    }

    public void setUserSalt(String userSalt) {
        this.userSalt = userSalt;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public Timestamp getUserRegisterTime() {
        return userRegisterTime;
    }

    public void setUserRegisterTime(Timestamp userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public Set<RoleTbDTO> getRoleTbDTOSet() {
        return roleTbDTOSet;
    }

    public void setRoleTbDTOSet(Set<RoleTbDTO> roleTbDTOSet) {
        this.roleTbDTOSet = roleTbDTOSet;
    }

    public Set<CouponTbDTO> getCouponTbDTOSet() {
        return couponTbDTOSet;
    }

    public void setCouponTbDTOSet(Set<CouponTbDTO> couponTbDTOSet) {
        this.couponTbDTOSet = couponTbDTOSet;
    }

    public Set<VipTbDTO> getVipTbDTOSet() {
        return vipTbDTOSet;
    }

    public void setVipTbDTOSet(Set<VipTbDTO> vipTbDTOSet) {
        this.vipTbDTOSet = vipTbDTOSet;
    }

    public Set<OrderTbDTO> getOrderTbDTOSet() {
        return orderTbDTOSet;
    }

    public void setOrderTbDTOSet(Set<OrderTbDTO> orderTbDTOSet) {
        this.orderTbDTOSet = orderTbDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserTbDTO)) return false;

        UserTbDTO userTbDTO = (UserTbDTO) o;

        if (getUserStatus() != userTbDTO.getUserStatus()) return false;
        if (getUserId() != null ? !getUserId().equals(userTbDTO.getUserId()) : userTbDTO.getUserId() != null)
            return false;
        if (getUserAccount() != null ? !getUserAccount().equals(userTbDTO.getUserAccount()) : userTbDTO.getUserAccount() != null)
            return false;
        if (getUserPassword() != null ? !getUserPassword().equals(userTbDTO.getUserPassword()) : userTbDTO.getUserPassword() != null)
            return false;
        if (getUserSalt() != null ? !getUserSalt().equals(userTbDTO.getUserSalt()) : userTbDTO.getUserSalt() != null)
            return false;
        if (getUserEmail() != null ? !getUserEmail().equals(userTbDTO.getUserEmail()) : userTbDTO.getUserEmail() != null)
            return false;
        if (getUserTel() != null ? !getUserTel().equals(userTbDTO.getUserTel()) : userTbDTO.getUserTel() != null)
            return false;
        if (getUserImg() != null ? !getUserImg().equals(userTbDTO.getUserImg()) : userTbDTO.getUserImg() != null)
            return false;
        return getUserRegisterTime() != null ? getUserRegisterTime().equals(userTbDTO.getUserRegisterTime()) : userTbDTO.getUserRegisterTime() == null;
    }

    @Override
    public int hashCode() {
        int result = getUserId() != null ? getUserId().hashCode() : 0;
        result = 31 * result + (getUserAccount() != null ? getUserAccount().hashCode() : 0);
        result = 31 * result + (getUserPassword() != null ? getUserPassword().hashCode() : 0);
        result = 31 * result + (getUserSalt() != null ? getUserSalt().hashCode() : 0);
        result = 31 * result + (getUserEmail() != null ? getUserEmail().hashCode() : 0);
        result = 31 * result + (getUserTel() != null ? getUserTel().hashCode() : 0);
        result = 31 * result + (getUserImg() != null ? getUserImg().hashCode() : 0);
        result = 31 * result + getUserStatus();
        result = 31 * result + (getUserRegisterTime() != null ? getUserRegisterTime().hashCode() : 0);
        return result;
    }
}
