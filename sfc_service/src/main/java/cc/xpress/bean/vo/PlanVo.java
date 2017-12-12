package cc.xpress.bean.vo;

import cc.xpress.bean.dto.PlanTbDTO;

import java.io.Serializable;

/**
 * @Create By Tjmxxo
 */
public class PlanVo implements Serializable {
    private PlanTbDTO planTbDTO;
    private String movieName;
    private String hallName;

    public PlanVo(PlanTbDTO planTbDTO) {
        this.planTbDTO = planTbDTO;
        this.hallName = planTbDTO.getHallTbDTO().getHallName();
        this.movieName = planTbDTO.getMovieTbDTO().getMovieName();
    }

    public PlanVo() {

    }

    public PlanTbDTO getPlanTbDTO() {
        return planTbDTO;
    }

    public void setPlanTbDTO(PlanTbDTO planTbDTO) {
        this.planTbDTO = planTbDTO;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }
}
