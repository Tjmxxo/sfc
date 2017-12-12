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
public class OrderSeatTbDTO implements Serializable {
    @EntityId
    private Integer osId;
    private Integer osPrice;
    @JSONField(serialize = false)
    private SeatTbDTO seatTbDTO;
    @JSONField(serialize = false)
    private OrderTbDTO orderTbDTO;

    public OrderSeatTbDTO() {
    }

    public OrderSeatTbDTO(Integer osPrice, SeatTbDTO seatTbDTO) {
        this.osPrice = osPrice;
        this.seatTbDTO = seatTbDTO;
    }

    public OrderTbDTO getOrderTbDTO() {
        return orderTbDTO;
    }

    public void setOrderTbDTO(OrderTbDTO orderTbDTO) {
        this.orderTbDTO = orderTbDTO;
    }

    public Integer getOsId() {
        return osId;
    }

    public void setOsId(Integer osId) {
        this.osId = osId;
    }

    public Integer getOsPrice() {
        return osPrice;
    }

    public void setOsPrice(Integer osPrice) {
        this.osPrice = osPrice;
    }

    public SeatTbDTO getSeatTbDTO() {
        return seatTbDTO;
    }

    public void setSeatTbDTO(SeatTbDTO seatTbDTO) {
        this.seatTbDTO = seatTbDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderSeatTbDTO)) return false;

        OrderSeatTbDTO that = (OrderSeatTbDTO) o;

        if (getOsId() != null ? !getOsId().equals(that.getOsId()) : that.getOsId() != null) return false;
        if (getOsPrice() != null ? !getOsPrice().equals(that.getOsPrice()) : that.getOsPrice() != null) return false;
        return getSeatTbDTO() != null ? getSeatTbDTO().equals(that.getSeatTbDTO()) : that.getSeatTbDTO() == null;
    }

    @Override
    public int hashCode() {
        int result = getOsId() != null ? getOsId().hashCode() : 0;
        result = 31 * result + (getOsPrice() != null ? getOsPrice().hashCode() : 0);
        result = 31 * result + (getSeatTbDTO() != null ? getSeatTbDTO().hashCode() : 0);
        return result;
    }
}
