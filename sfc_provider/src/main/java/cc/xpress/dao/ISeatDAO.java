package cc.xpress.dao;

import cc.xpress.bean.dto.SeatTbDTO;

import java.util.List;

public interface ISeatDAO extends IBaseDAO<SeatTbDTO> {

    List getAllSeatById(int hallId);

}
