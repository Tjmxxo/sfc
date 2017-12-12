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
public class SelectTbDTO implements Serializable {
    @EntityId
    private Long selectId;
    private int seatStatus;
    @JSONField(serialize = false)
    private SeatTbDTO seatTbDTO;
    @JSONField(serialize = false)
    private PlanTbDTO planTbDTO;

    public SelectTbDTO() {
    }

    public SelectTbDTO(int seatStatus, SeatTbDTO seatTbDTO, PlanTbDTO planTbDTO) {
        this.seatStatus = seatStatus;
        this.seatTbDTO = seatTbDTO;
        this.planTbDTO = planTbDTO;
    }

    public Long getSelectId() {
        return selectId;
    }

    public void setSelectId(Long selectId) {
        this.selectId = selectId;
    }

    public int getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(int seatStatus) {
        this.seatStatus = seatStatus;
    }

    public SeatTbDTO getSeatTbDTO() {
        return seatTbDTO;
    }

    public void setSeatTbDTO(SeatTbDTO seatTbDTO) {
        this.seatTbDTO = seatTbDTO;
    }

    public PlanTbDTO getPlanTbDTO() {
        return planTbDTO;
    }

    public void setPlanTbDTO(PlanTbDTO planTbDTO) {
        this.planTbDTO = planTbDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SelectTbDTO)) return false;

        SelectTbDTO that = (SelectTbDTO) o;

        if (getSeatStatus() != that.getSeatStatus()) return false;
        return getSelectId() != null ? getSelectId().equals(that.getSelectId()) : that.getSelectId() == null;
    }

    @Override
    public int hashCode() {
        int result = getSelectId() != null ? getSelectId().hashCode() : 0;
        result = 31 * result + getSeatStatus();
        return result;
    }
}
