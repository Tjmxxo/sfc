package cc.xpress.bean.vo;

import cc.xpress.bean.dto.MovieTbDTO;

import java.io.Serializable;

/**
 * @Create By Tjmxxo
 */
public class MovieVo implements Serializable {

    private String movieName;
    private long userNum;

    public MovieVo(MovieTbDTO movieTbDTO, long userNum) {
        this.movieName = movieTbDTO.getMovieName();
        this.userNum = userNum;
    }

    public MovieVo() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieVo)) return false;

        MovieVo movieVo = (MovieVo) o;

        if (getUserNum() != movieVo.getUserNum()) return false;
        return getMovieName() != null ? getMovieName().equals(movieVo.getMovieName()) : movieVo.getMovieName() == null;
    }

    @Override
    public int hashCode() {
        int result = getMovieName() != null ? getMovieName().hashCode() : 0;
        result = 31 * result + (int) (getUserNum() ^ (getUserNum() >>> 32));
        return result;
    }

    public String getMovieName() {

        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public long getUserNum() {
        return userNum;
    }

    public void setUserNum(long userNum) {
        this.userNum = userNum;
    }
}
