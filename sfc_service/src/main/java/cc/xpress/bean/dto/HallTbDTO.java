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
public class HallTbDTO implements Serializable {
    @EntityId
    private Integer hallId;
    private String hallName;
    private String hallScreenType;
    private String hallDescribe;
    private Integer hallMaxRow;
    private Integer hallMaxCol;
    @JSONField(serialize = false)
    private CinemaTbDTO cinemaTbDTO;
    @JSONField(serialize = false)
    private Set<PlanTbDTO> planTbDTOSet;
    @JSONField(serialize = false)
    private Set<SeatTbDTO> seatTbDTOSet;

    public HallTbDTO() {
    }

    public CinemaTbDTO getCinemaTbDTO() {
        return cinemaTbDTO;
    }

    public void setCinemaTbDTO(CinemaTbDTO cinemaTbDTO) {
        this.cinemaTbDTO = cinemaTbDTO;
    }

    public Set<PlanTbDTO> getPlanTbDTOSet() {
        return planTbDTOSet;
    }

    public void setPlanTbDTOSet(Set<PlanTbDTO> planTbDTOSet) {
        this.planTbDTOSet = planTbDTOSet;
    }

    public Set<SeatTbDTO> getSeatTbDTOSet() {
        return seatTbDTOSet;
    }

    public void setSeatTbDTOSet(Set<SeatTbDTO> seatTbDTOSet) {
        this.seatTbDTOSet = seatTbDTOSet;
    }

    public Integer getHallId() {
        return hallId;
    }

    public void setHallId(Integer hallId) {
        this.hallId = hallId;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public String getHallScreenType() {
        return hallScreenType;
    }

    public void setHallScreenType(String hallScreenType) {
        this.hallScreenType = hallScreenType;
    }

    public String getHallDescribe() {
        return hallDescribe;
    }

    public void setHallDescribe(String hallDescribe) {
        this.hallDescribe = hallDescribe;
    }

    public Integer getHallMaxRow() {
        return hallMaxRow;
    }

    public void setHallMaxRow(Integer hallMaxRow) {
        this.hallMaxRow = hallMaxRow;
    }

    public Integer getHallMaxCol() {
        return hallMaxCol;
    }

    public void setHallMaxCol(Integer hallMaxCol) {
        this.hallMaxCol = hallMaxCol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HallTbDTO)) return false;

        HallTbDTO hallTbDTO = (HallTbDTO) o;

        if (getHallId() != null ? !getHallId().equals(hallTbDTO.getHallId()) : hallTbDTO.getHallId() != null)
            return false;
        if (getHallName() != null ? !getHallName().equals(hallTbDTO.getHallName()) : hallTbDTO.getHallName() != null)
            return false;
        if (getHallScreenType() != null ? !getHallScreenType().equals(hallTbDTO.getHallScreenType()) : hallTbDTO.getHallScreenType() != null)
            return false;
        if (getHallDescribe() != null ? !getHallDescribe().equals(hallTbDTO.getHallDescribe()) : hallTbDTO.getHallDescribe() != null)
            return false;
        if (getHallMaxRow() != null ? !getHallMaxRow().equals(hallTbDTO.getHallMaxRow()) : hallTbDTO.getHallMaxRow() != null)
            return false;
        return getHallMaxCol() != null ? getHallMaxCol().equals(hallTbDTO.getHallMaxCol()) : hallTbDTO.getHallMaxCol() == null;
    }

    @Override
    public int hashCode() {
        int result = getHallId() != null ? getHallId().hashCode() : 0;
        result = 31 * result + (getHallName() != null ? getHallName().hashCode() : 0);
        result = 31 * result + (getHallScreenType() != null ? getHallScreenType().hashCode() : 0);
        result = 31 * result + (getHallDescribe() != null ? getHallDescribe().hashCode() : 0);
        result = 31 * result + (getHallMaxRow() != null ? getHallMaxRow().hashCode() : 0);
        result = 31 * result + (getHallMaxCol() != null ? getHallMaxCol().hashCode() : 0);
        return result;
    }
}
