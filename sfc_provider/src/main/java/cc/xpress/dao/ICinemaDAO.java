package cc.xpress.dao;

import cc.xpress.bean.dto.CinemaTbDTO;

import java.util.List;

public interface ICinemaDAO extends IBaseDAO<CinemaTbDTO> {
    /**
     * 得到影院和城市Id
     *
     * @return
     */
    List<CinemaTbDTO> getAllCinemas();
}
