package cc.xpress.bean.vo;

import cc.xpress.bean.dto.CinemaTbDTO;

import java.io.Serializable;

public class CinemaVo implements Serializable {
    private CinemaTbDTO cinemaTbDTO;
    private String cityId;

    public CinemaVo() {

    }

    public CinemaVo(CinemaTbDTO cinemaTbDTO, String cityId) {
        this.cinemaTbDTO = cinemaTbDTO;
        this.cityId = cityId;
    }

    public CinemaTbDTO getCinemaTbDTO() {
        return cinemaTbDTO;
    }

    public void setCinemaTbDTO(CinemaTbDTO cinemaTbDTO) {
        this.cinemaTbDTO = cinemaTbDTO;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }
}
