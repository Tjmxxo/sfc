package cc.xpress.bean.dto;

import cc.xpress.annotation.EntityId;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-27 23:32
 * @modified By:
 */
public class VipTbDTO implements Serializable {
    @EntityId
    private Integer vipId;
    private String vipNo;
    private int vipMoney;
    @JSONField(serialize = false)
    private UserTbDTO userTbDTO;
    @JSONField(serialize = false)
    private CinemaTbDTO cinemaTbDTO;

    public VipTbDTO() {
    }

    public VipTbDTO(String vipNo, int vipMoney, CinemaTbDTO cinemaTbDTO) {
        this.vipNo = vipNo;
        this.vipMoney = vipMoney;
        this.cinemaTbDTO = cinemaTbDTO;
    }

    public Integer getVipId() {
        return vipId;
    }

    public void setVipId(Integer vipId) {
        this.vipId = vipId;
    }

    public String getVipNo() {
        return vipNo;
    }

    public void setVipNo(String vipNo) {
        this.vipNo = vipNo;
    }

    public int getVipMoney() {
        return vipMoney;
    }

    public void setVipMoney(int vipMoney) {
        this.vipMoney = vipMoney;
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
        if (!(o instanceof VipTbDTO)) return false;

        VipTbDTO vipTbDTO = (VipTbDTO) o;

        if (getVipMoney() != vipTbDTO.getVipMoney()) return false;
        if (getVipId() != null ? !getVipId().equals(vipTbDTO.getVipId()) : vipTbDTO.getVipId() != null) return false;
        return getVipNo() != null ? getVipNo().equals(vipTbDTO.getVipNo()) : vipTbDTO.getVipNo() == null;
    }

    @Override
    public int hashCode() {
        int result = getVipId() != null ? getVipId().hashCode() : 0;
        result = 31 * result + (getVipNo() != null ? getVipNo().hashCode() : 0);
        result = 31 * result + getVipMoney();
        return result;
    }
}
