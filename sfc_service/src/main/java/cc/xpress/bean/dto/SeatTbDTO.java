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
public class SeatTbDTO implements Serializable {
    @EntityId
    private Integer seatId;
    private Integer seatRow;
    private Integer seatCol;
    private int seatStatus;
    @JSONField(serialize = false)
    private HallTbDTO hallTbDTO;

    public SeatTbDTO() {
    }

    public SeatTbDTO(Integer seatRow, Integer seatCol, HallTbDTO hallTbDTO) {
        this.seatRow = seatRow;
        this.seatCol = seatCol;
        this.hallTbDTO = hallTbDTO;
    }

    public SeatTbDTO(Integer seatId) {
        this.seatId = seatId;
    }

    public HallTbDTO getHallTbDTO() {
        return hallTbDTO;
    }

    public void setHallTbDTO(HallTbDTO hallTbDTO) {
        this.hallTbDTO = hallTbDTO;
    }

    public int getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(int seatStatus) {
        this.seatStatus = seatStatus;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public Integer getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }

    public Integer getSeatCol() {
        return seatCol;
    }

    public void setSeatCol(Integer seatCol) {
        this.seatCol = seatCol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SeatTbDTO)) return false;

        SeatTbDTO seatTbDTO = (SeatTbDTO) o;

        if (getSeatStatus() != seatTbDTO.getSeatStatus()) return false;
        if (getSeatId() != null ? !getSeatId().equals(seatTbDTO.getSeatId()) : seatTbDTO.getSeatId() != null)
            return false;
        if (getSeatRow() != null ? !getSeatRow().equals(seatTbDTO.getSeatRow()) : seatTbDTO.getSeatRow() != null)
            return false;
        return getSeatCol() != null ? getSeatCol().equals(seatTbDTO.getSeatCol()) : seatTbDTO.getSeatCol() == null;
    }

    @Override
    public int hashCode() {
        int result = getSeatId() != null ? getSeatId().hashCode() : 0;
        result = 31 * result + (getSeatRow() != null ? getSeatRow().hashCode() : 0);
        result = 31 * result + (getSeatCol() != null ? getSeatCol().hashCode() : 0);
        result = 31 * result + getSeatStatus();
        return result;
    }
}
