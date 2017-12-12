package cc.xpress.bean.dto;

import cc.xpress.annotation.EntityId;

import java.io.Serializable;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-27 23:32
 * @modified By:
 */
public class PermissionTbDTO implements Serializable {
    @EntityId
    private Integer permissionId;
    private String permissionName;
    private String permissionDescribe;
    private int permissionStatus;

    public PermissionTbDTO() {
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescribe() {
        return permissionDescribe;
    }

    public void setPermissionDescribe(String permissionDescribe) {
        this.permissionDescribe = permissionDescribe;
    }

    public int getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(int permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PermissionTbDTO)) return false;

        PermissionTbDTO that = (PermissionTbDTO) o;

        if (getPermissionStatus() != that.getPermissionStatus()) return false;
        if (getPermissionId() != null ? !getPermissionId().equals(that.getPermissionId()) : that.getPermissionId() != null)
            return false;
        if (getPermissionName() != null ? !getPermissionName().equals(that.getPermissionName()) : that.getPermissionName() != null)
            return false;
        return getPermissionDescribe() != null ? getPermissionDescribe().equals(that.getPermissionDescribe()) : that.getPermissionDescribe() == null;
    }

    @Override
    public int hashCode() {
        int result = getPermissionId() != null ? getPermissionId().hashCode() : 0;
        result = 31 * result + (getPermissionName() != null ? getPermissionName().hashCode() : 0);
        result = 31 * result + (getPermissionDescribe() != null ? getPermissionDescribe().hashCode() : 0);
        result = 31 * result + getPermissionStatus();
        return result;
    }
}
