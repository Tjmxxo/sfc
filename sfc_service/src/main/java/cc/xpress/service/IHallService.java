package cc.xpress.service;

import cc.xpress.bean.dto.HallTbDTO;

import java.util.List;

/**
 * @Create By Tjmxxo
 */
public interface IHallService {
    /**
     * 获取本影院所有影厅
     *
     * @return
     */
    List<HallTbDTO> getAllHallTb(int cinemaId);

    /**
     * 修改影厅信息
     *
     * @param hall
     */
    void changeHall(HallTbDTO hall, int cinemaId);

    /**
     * 新增影厅
     * @param hall
     * @param cinemaId
     */
    String saveHall(HallTbDTO hall, int cinemaId);
}
