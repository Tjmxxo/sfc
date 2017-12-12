package cc.xpress.bean.dto;

import cc.xpress.annotation.EntityId;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-27 23:32
 * @modified By:
 */
public class MovieTbDTO implements Serializable {
    @EntityId
    private Integer movieId;
    private String movieName;
    private Integer moviePlayTime;
    private Date moviePublishTime;
    private String movieDirector;
    private String movieCountry;
    private String movieLanguage;
    private String movieActor;
    private String movieType;
    private String moviePoster;
    private int minPrice;
    private int minVipPrice;
    @JSONField(serialize = false)
    private Set<PlanTbDTO> planTbDTOSet;

    public MovieTbDTO() {
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Integer getMoviePlayTime() {
        return moviePlayTime;
    }

    public void setMoviePlayTime(Integer moviePlayTime) {
        this.moviePlayTime = moviePlayTime;
    }

    public Date getMoviePublishTime() {
        return moviePublishTime;
    }

    public void setMoviePublishTime(Date moviePublishTime) {
        this.moviePublishTime = moviePublishTime;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieCountry() {
        return movieCountry;
    }

    public void setMovieCountry(String movieCountry) {
        this.movieCountry = movieCountry;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(String movieActor) {
        this.movieActor = movieActor;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public void setMoviePoster(String moviePoster) {
        this.moviePoster = moviePoster;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMinVipPrice() {
        return minVipPrice;
    }

    public void setMinVipPrice(int minVipPrice) {
        this.minVipPrice = minVipPrice;
    }

    public Set<PlanTbDTO> getPlanTbDTOSet() {
        return planTbDTOSet;
    }

    public void setPlanTbDTOSet(Set<PlanTbDTO> planTbDTOSet) {
        this.planTbDTOSet = planTbDTOSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieTbDTO)) return false;

        MovieTbDTO that = (MovieTbDTO) o;

        if (getMinPrice() != that.getMinPrice()) return false;
        if (getMinVipPrice() != that.getMinVipPrice()) return false;
        if (getMovieId() != null ? !getMovieId().equals(that.getMovieId()) : that.getMovieId() != null) return false;
        if (getMovieName() != null ? !getMovieName().equals(that.getMovieName()) : that.getMovieName() != null)
            return false;
        if (getMoviePlayTime() != null ? !getMoviePlayTime().equals(that.getMoviePlayTime()) : that.getMoviePlayTime() != null)
            return false;
        if (getMoviePublishTime() != null ? !getMoviePublishTime().equals(that.getMoviePublishTime()) : that.getMoviePublishTime() != null)
            return false;
        if (getMovieDirector() != null ? !getMovieDirector().equals(that.getMovieDirector()) : that.getMovieDirector() != null)
            return false;
        if (getMovieCountry() != null ? !getMovieCountry().equals(that.getMovieCountry()) : that.getMovieCountry() != null)
            return false;
        if (getMovieLanguage() != null ? !getMovieLanguage().equals(that.getMovieLanguage()) : that.getMovieLanguage() != null)
            return false;
        if (getMovieActor() != null ? !getMovieActor().equals(that.getMovieActor()) : that.getMovieActor() != null)
            return false;
        if (getMovieType() != null ? !getMovieType().equals(that.getMovieType()) : that.getMovieType() != null)
            return false;
        return getMoviePoster() != null ? getMoviePoster().equals(that.getMoviePoster()) : that.getMoviePoster() == null;
    }

    @Override
    public int hashCode() {
        int result = getMovieId() != null ? getMovieId().hashCode() : 0;
        result = 31 * result + (getMovieName() != null ? getMovieName().hashCode() : 0);
        result = 31 * result + (getMoviePlayTime() != null ? getMoviePlayTime().hashCode() : 0);
        result = 31 * result + (getMoviePublishTime() != null ? getMoviePublishTime().hashCode() : 0);
        result = 31 * result + (getMovieDirector() != null ? getMovieDirector().hashCode() : 0);
        result = 31 * result + (getMovieCountry() != null ? getMovieCountry().hashCode() : 0);
        result = 31 * result + (getMovieLanguage() != null ? getMovieLanguage().hashCode() : 0);
        result = 31 * result + (getMovieActor() != null ? getMovieActor().hashCode() : 0);
        result = 31 * result + (getMovieType() != null ? getMovieType().hashCode() : 0);
        result = 31 * result + (getMoviePoster() != null ? getMoviePoster().hashCode() : 0);
        result = 31 * result + getMinPrice();
        result = 31 * result + getMinVipPrice();
        return result;
    }
}
