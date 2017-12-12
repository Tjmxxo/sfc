package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.HallTbDTO;
import cc.xpress.bean.dto.SeatTbDTO;
import cc.xpress.dao.IHallDAO;
import cc.xpress.dao.ISeatDAO;
import cc.xpress.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @Create By Tjmxxo
 */
@Service("seatService")
public class SeatServiceImpl implements ISeatService {

    @Autowired
    private ISeatDAO seatDAO;

    @Autowired
    private IHallDAO hallDAO;

    /**
     * 修改座位信息
     */
    @Transactional
    @Override
    public Serializable saveSeat(SeatTbDTO seatTbDTO,int hallId) {
        if (seatTbDTO == null || seatTbDTO.getSeatId() == null) {
            return null;
        }
        System.out.println("座位Id"+seatTbDTO.getSeatId());
        Integer seatId = seatTbDTO.getSeatId();
        seatTbDTO.setSeatId(seatId);
        HallTbDTO entityById = hallDAO.getEntityById(HallTbDTO.class, hallId);
        seatTbDTO.setHallTbDTO(entityById);
        seatDAO.updateEntity(seatTbDTO);
        return "11";

    }

    /**
     * 获取本影所有座位
     *
     * @param hallId
     * @return
     */
    @Transactional
    @Override
    public List<SeatTbDTO> getAllSeatByHallId(int hallId) {
        if (hallId == 0) {
            return null;
        }
        List allSeatById = seatDAO.getAllSeatById(hallId);
        if (allSeatById == null) {
            return null;
        }
        return allSeatById;
    }
}
