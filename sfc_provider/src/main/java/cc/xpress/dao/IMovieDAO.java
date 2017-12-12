package cc.xpress.dao;

import cc.xpress.bean.dto.MovieTbDTO;

import java.util.List;

public interface IMovieDAO extends IBaseDAO<MovieTbDTO> {

    List<MovieTbDTO> getAllMovieByCinemaId(int cinemaId);

    List<MovieTbDTO> getAllMovieIdByCinemaId();

}
