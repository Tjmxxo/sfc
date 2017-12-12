package cc.xpress.bean.dto;

import cc.xpress.annotation.EntityId;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-27 23:32
 * @modified By:
 */
public class OrderTbDTO implements Serializable {
    @EntityId
    private Integer orderId;
    private Timestamp orderCreateTime;
    private Timestamp orderPayTime;
    private Integer orderStatus;
    private String orderPayMethod;
    private Integer orderSumPrice;
    private Integer orderPayPrice;
    private String userTel;
    @JSONField(serialize = false)
    private PlanTbDTO planTbDTO;
    @JSONField(serialize = false)
    private UserTbDTO userTbDTO;
    @JSONField(serialize = false)
    private MovieTbDTO movieTbDTO;
    @JSONField(serialize = false)
    private CinemaTbDTO cinemaTbDTO;
    @JSONField(serialize = false)
    private HallTbDTO hallTbDTO;
    private Set<OrderSeatTbDTO> orderSeatTbDTOS;

    public OrderTbDTO() {
    }

    public String getOrderPayMethod() {
        return orderPayMethod;
    }

    public void setOrderPayMethod(String orderPayMethod) {
        this.orderPayMethod = orderPayMethod;
    }

    public Integer getOrderSumPrice() {
        return orderSumPrice;
    }

    public void setOrderSumPrice(Integer orderSumPrice) {
        this.orderSumPrice = orderSumPrice;
    }

    public Integer getOrderPayPrice() {
        return orderPayPrice;
    }

    public void setOrderPayPrice(Integer orderPayPrice) {
        this.orderPayPrice = orderPayPrice;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public Set<OrderSeatTbDTO> getOrderSeatTbDTOS() {
        return orderSeatTbDTOS;
    }

    public void setOrderSeatTbDTOS(Set<OrderSeatTbDTO> orderSeatTbDTOS) {
        this.orderSeatTbDTOS = orderSeatTbDTOS;
    }

    public HallTbDTO getHallTbDTO() {
        return hallTbDTO;
    }

    public void setHallTbDTO(HallTbDTO hallTbDTO) {
        this.hallTbDTO = hallTbDTO;
    }

    public MovieTbDTO getMovieTbDTO() {
        return movieTbDTO;
    }

    public void setMovieTbDTO(MovieTbDTO movieTbDTO) {
        this.movieTbDTO = movieTbDTO;
    }

    public CinemaTbDTO getCinemaTbDTO() {
        return cinemaTbDTO;
    }

    public void setCinemaTbDTO(CinemaTbDTO cinemaTbDTO) {
        this.cinemaTbDTO = cinemaTbDTO;
    }

    public PlanTbDTO getPlanTbDTO() {
        return planTbDTO;
    }

    public void setPlanTbDTO(PlanTbDTO planTbDTO) {
        this.planTbDTO = planTbDTO;
    }

    public UserTbDTO getUserTbDTO() {
        return userTbDTO;
    }

    public void setUserTbDTO(UserTbDTO userTbDTO) {
        this.userTbDTO = userTbDTO;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Timestamp getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(Timestamp orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public Timestamp getOrderPayTime() {
        return orderPayTime;
    }

    public void setOrderPayTime(Timestamp orderPayTime) {
        this.orderPayTime = orderPayTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderTbDTO)) return false;

        OrderTbDTO that = (OrderTbDTO) o;

        if (getOrderId() != null ? !getOrderId().equals(that.getOrderId()) : that.getOrderId() != null) return false;
        if (getOrderCreateTime() != null ? !getOrderCreateTime().equals(that.getOrderCreateTime()) : that.getOrderCreateTime() != null)
            return false;
        if (getOrderPayTime() != null ? !getOrderPayTime().equals(that.getOrderPayTime()) : that.getOrderPayTime() != null)
            return false;
        if (getOrderStatus() != null ? !getOrderStatus().equals(that.getOrderStatus()) : that.getOrderStatus() != null)
            return false;
        if (getOrderPayMethod() != null ? !getOrderPayMethod().equals(that.getOrderPayMethod()) : that.getOrderPayMethod() != null)
            return false;
        if (getOrderSumPrice() != null ? !getOrderSumPrice().equals(that.getOrderSumPrice()) : that.getOrderSumPrice() != null)
            return false;
        if (getOrderPayPrice() != null ? !getOrderPayPrice().equals(that.getOrderPayPrice()) : that.getOrderPayPrice() != null)
            return false;
        return getUserTel() != null ? getUserTel().equals(that.getUserTel()) : that.getUserTel() == null;
    }

    @Override
    public int hashCode() {
        int result = getOrderId() != null ? getOrderId().hashCode() : 0;
        result = 31 * result + (getOrderCreateTime() != null ? getOrderCreateTime().hashCode() : 0);
        result = 31 * result + (getOrderPayTime() != null ? getOrderPayTime().hashCode() : 0);
        result = 31 * result + (getOrderStatus() != null ? getOrderStatus().hashCode() : 0);
        result = 31 * result + (getOrderPayMethod() != null ? getOrderPayMethod().hashCode() : 0);
        result = 31 * result + (getOrderSumPrice() != null ? getOrderSumPrice().hashCode() : 0);
        result = 31 * result + (getOrderPayPrice() != null ? getOrderPayPrice().hashCode() : 0);
        result = 31 * result + (getUserTel() != null ? getUserTel().hashCode() : 0);
        return result;
    }
}
