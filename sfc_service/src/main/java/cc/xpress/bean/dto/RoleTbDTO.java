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
public class RoleTbDTO implements Serializable {
    @EntityId
    private Integer roleId;
    private String roleName;
    private String roleDescribe;
    private int roleStatus;
    @JSONField(serialize = false)
    private Set<PermissionTbDTO> permissionTbDTOSet;

    public RoleTbDTO() {
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescribe() {
        return roleDescribe;
    }

    public void setRoleDescribe(String roleDescribe) {
        this.roleDescribe = roleDescribe;
    }

    public int getRoleStatus() {
        return roleStatus;
    }

    public void setRoleStatus(int roleStatus) {
        this.roleStatus = roleStatus;
    }

    public Set<PermissionTbDTO> getPermissionTbDTOSet() {
        return permissionTbDTOSet;
    }

    public void setPermissionTbDTOSet(Set<PermissionTbDTO> permissionTbDTOSet) {
        this.permissionTbDTOSet = permissionTbDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleTbDTO)) return false;

        RoleTbDTO roleTbDTO = (RoleTbDTO) o;

        if (getRoleStatus() != roleTbDTO.getRoleStatus()) return false;
        if (getRoleId() != null ? !getRoleId().equals(roleTbDTO.getRoleId()) : roleTbDTO.getRoleId() != null)
            return false;
        if (getRoleName() != null ? !getRoleName().equals(roleTbDTO.getRoleName()) : roleTbDTO.getRoleName() != null)
            return false;
        return getRoleDescribe() != null ? getRoleDescribe().equals(roleTbDTO.getRoleDescribe()) : roleTbDTO.getRoleDescribe() == null;
    }

    @Override
    public int hashCode() {
        int result = getRoleId() != null ? getRoleId().hashCode() : 0;
        result = 31 * result + (getRoleName() != null ? getRoleName().hashCode() : 0);
        result = 31 * result + (getRoleDescribe() != null ? getRoleDescribe().hashCode() : 0);
        result = 31 * result + getRoleStatus();
        return result;
    }
}
