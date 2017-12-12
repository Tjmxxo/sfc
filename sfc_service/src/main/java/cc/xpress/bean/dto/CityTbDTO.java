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
public class CityTbDTO implements Serializable {
    @EntityId
    private Integer cityId;
    private String cityName;
    private Integer cityZipCode;
    @JSONField(serialize = false)
    private Set<CinemaTbDTO> cinemaTbDTOSet;

    public CityTbDTO() {
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getCityZipCode() {
        return cityZipCode;
    }

    public void setCityZipCode(Integer cityZipCode) {
        this.cityZipCode = cityZipCode;
    }

    public Set<CinemaTbDTO> getCinemaTbDTOSet() {
        return cinemaTbDTOSet;
    }

    public void setCinemaTbDTOSet(Set<CinemaTbDTO> cinemaTbDTOSet) {
        this.cinemaTbDTOSet = cinemaTbDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CityTbDTO)) return false;

        CityTbDTO cityTbDTO = (CityTbDTO) o;

        if (getCityId() != null ? !getCityId().equals(cityTbDTO.getCityId()) : cityTbDTO.getCityId() != null)
            return false;
        if (getCityName() != null ? !getCityName().equals(cityTbDTO.getCityName()) : cityTbDTO.getCityName() != null)
            return false;
        return getCityZipCode() != null ? getCityZipCode().equals(cityTbDTO.getCityZipCode()) : cityTbDTO.getCityZipCode() == null;
    }

    @Override
    public int hashCode() {
        int result = getCityId() != null ? getCityId().hashCode() : 0;
        result = 31 * result + (getCityName() != null ? getCityName().hashCode() : 0);
        result = 31 * result + (getCityZipCode() != null ? getCityZipCode().hashCode() : 0);
        return result;
    }
}
