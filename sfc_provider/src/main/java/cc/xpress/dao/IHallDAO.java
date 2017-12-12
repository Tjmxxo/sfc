package cc.xpress.dao;

import cc.xpress.bean.dto.HallTbDTO;

import java.util.List;

public interface IHallDAO extends IBaseDAO<HallTbDTO> {

    public List<HallTbDTO> getAllHall(int cinemaId);

    HallTbDTO getHallById(int hallId, int cinemaId);
}
