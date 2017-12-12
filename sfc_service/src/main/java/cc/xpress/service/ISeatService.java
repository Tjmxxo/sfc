package cc.xpress.service;

import cc.xpress.bean.dto.SeatTbDTO;

import java.io.Serializable;
import java.util.List;

/**
 * @Create By Tjmxxo
 */
public interface ISeatService {

    /**
     * 修改座位信息
     *
     * @param seatTbDTO
     * @return
     */
    Serializable saveSeat(SeatTbDTO seatTbDTO,int hallId);

    /**
     * 获取本影所有座位
     *
     * @return
     */
    List<SeatTbDTO> getAllSeatByHallId(int hallId);
}
