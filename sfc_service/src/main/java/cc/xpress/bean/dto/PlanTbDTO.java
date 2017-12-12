package cc.xpress.bean.dto;

import cc.xpress.annotation.EntityId;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Set;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-27 23:32
 * @modified By:
 */
public class PlanTbDTO implements Serializable {
    @EntityId
    private Integer planId;
    private Integer planPrice;
    private Timestamp planStartTime;
    private Date planData;
    private Time planTime;
    private String planLanguage;
    private String planScreenType;
    private Integer planDiscount;
    private Integer planStatus;
    @JSONField(serialize = false)
    private CityTbDTO cityTbDTO;
    @JSONField(serialize = false)
    private CinemaTbDTO cinemaTbDTO;
    @JSONField(serialize = false)
    private HallTbDTO hallTbDTO;
    @JSONField(serialize = false)
    private MovieTbDTO movieTbDTO;
    @JSONField(serialize = false)
    private Set<SelectTbDTO> selectTbDTOSet;

    public PlanTbDTO() {
    }

    public CityTbDTO getCityTbDTO() {
        return cityTbDTO;
    }

    public void setCityTbDTO(CityTbDTO cityTbDTO) {
        this.cityTbDTO = cityTbDTO;
    }

    public CinemaTbDTO getCinemaTbDTO() {
        return cinemaTbDTO;
    }

    public void setCinemaTbDTO(CinemaTbDTO cinemaTbDTO) {
        this.cinemaTbDTO = cinemaTbDTO;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public Integer getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(Integer planPrice) {
        this.planPrice = planPrice;
    }

    public Timestamp getPlanStartTime() {
        return planStartTime;
    }

    public void setPlanStartTime(Timestamp planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Date getPlanData() {
        return planData;
    }

    public void setPlanData(Date planData) {
        this.planData = planData;
    }

    public Time getPlanTime() {
        return planTime;
    }

    public void setPlanTime(Time planTime) {
        this.planTime = planTime;
    }

    public String getPlanLanguage() {
        return planLanguage;
    }

    public void setPlanLanguage(String planLanguage) {
        this.planLanguage = planLanguage;
    }

    public String getPlanScreenType() {
        return planScreenType;
    }

    public void setPlanScreenType(String planScreenType) {
        this.planScreenType = planScreenType;
    }

    public Integer getPlanDiscount() {
        return planDiscount;
    }

    public void setPlanDiscount(Integer planDiscount) {
        this.planDiscount = planDiscount;
    }

    public Integer getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(Integer planStatus) {
        this.planStatus = planStatus;
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

    public Set<SelectTbDTO> getSelectTbDTOSet() {
        return selectTbDTOSet;
    }

    public void setSelectTbDTOSet(Set<SelectTbDTO> selectTbDTOSet) {
        this.selectTbDTOSet = selectTbDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlanTbDTO)) return false;

        PlanTbDTO planTbDTO = (PlanTbDTO) o;

        if (getPlanId() != null ? !getPlanId().equals(planTbDTO.getPlanId()) : planTbDTO.getPlanId() != null)
            return false;
        if (getPlanPrice() != null ? !getPlanPrice().equals(planTbDTO.getPlanPrice()) : planTbDTO.getPlanPrice() != null)
            return false;
        if (getPlanStartTime() != null ? !getPlanStartTime().equals(planTbDTO.getPlanStartTime()) : planTbDTO.getPlanStartTime() != null)
            return false;
        if (getPlanData() != null ? !getPlanData().equals(planTbDTO.getPlanData()) : planTbDTO.getPlanData() != null)
            return false;
        if (getPlanTime() != null ? !getPlanTime().equals(planTbDTO.getPlanTime()) : planTbDTO.getPlanTime() != null)
            return false;
        if (getPlanLanguage() != null ? !getPlanLanguage().equals(planTbDTO.getPlanLanguage()) : planTbDTO.getPlanLanguage() != null)
            return false;
        if (getPlanScreenType() != null ? !getPlanScreenType().equals(planTbDTO.getPlanScreenType()) : planTbDTO.getPlanScreenType() != null)
            return false;
        if (getPlanDiscount() != null ? !getPlanDiscount().equals(planTbDTO.getPlanDiscount()) : planTbDTO.getPlanDiscount() != null)
            return false;
        return getPlanStatus() != null ? getPlanStatus().equals(planTbDTO.getPlanStatus()) : planTbDTO.getPlanStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getPlanId() != null ? getPlanId().hashCode() : 0;
        result = 31 * result + (getPlanPrice() != null ? getPlanPrice().hashCode() : 0);
        result = 31 * result + (getPlanStartTime() != null ? getPlanStartTime().hashCode() : 0);
        result = 31 * result + (getPlanData() != null ? getPlanData().hashCode() : 0);
        result = 31 * result + (getPlanTime() != null ? getPlanTime().hashCode() : 0);
        result = 31 * result + (getPlanLanguage() != null ? getPlanLanguage().hashCode() : 0);
        result = 31 * result + (getPlanScreenType() != null ? getPlanScreenType().hashCode() : 0);
        result = 31 * result + (getPlanDiscount() != null ? getPlanDiscount().hashCode() : 0);
        result = 31 * result + (getPlanStatus() != null ? getPlanStatus().hashCode() : 0);
        return result;
    }
}
