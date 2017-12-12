package cc.xpress.bean.vo;

import cc.xpress.bean.dto.VipTbDTO;

import java.io.Serializable;

/**
 * @Create By Tjmxxo
 */
public class VipVo implements Serializable {

    private VipTbDTO vipTbDTO;
    private String userAccount;

    public VipVo(VipTbDTO vipTbDTO, String userAccount) {
        this.vipTbDTO = vipTbDTO;
        this.userAccount = userAccount;
    }

    public VipVo() {

    }

    public VipTbDTO getVipTbDTO() {
        return vipTbDTO;
    }

    public void setVipTbDTO(VipTbDTO vipTbDTO) {
        this.vipTbDTO = vipTbDTO;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VipVo)) return false;

        VipVo vipVo = (VipVo) o;

        if (getVipTbDTO() != null ? !getVipTbDTO().equals(vipVo.getVipTbDTO()) : vipVo.getVipTbDTO() != null) return false;
        return getUserAccount() != null ? getUserAccount().equals(vipVo.getUserAccount()) : vipVo.getUserAccount() == null;
    }

    @Override
    public int hashCode() {
        int result = getVipTbDTO() != null ? getVipTbDTO().hashCode() : 0;
        result = 31 * result + (getUserAccount() != null ? getUserAccount().hashCode() : 0);
        return result;
    }
}
