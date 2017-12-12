package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.CinemaTbDTO;
import cc.xpress.bean.dto.HallTbDTO;
import cc.xpress.bean.dto.SeatTbDTO;
import cc.xpress.dao.ICinemaDAO;
import cc.xpress.dao.IHallDAO;
import cc.xpress.service.IHallService;
import cc.xpress.service.ISeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.*;

/**
 * @Create By Tjmxxo
 */
@Service("hallService")
public class HallServiceImpl implements IHallService {

    @Autowired
    private IHallDAO hallDAO;

    @Autowired
    private ICinemaDAO cinemaDAO;

    @Autowired
    private ISeatService seatService;

    /**
     * 获取本影院所有影厅
     *
     * @return
     */
    @Transactional
    @Override
    public List<HallTbDTO> getAllHallTb(int cinemaId) {
        return hallDAO.getAllHall(cinemaId);
    }

    @Transactional
    @Override
    public void changeHall(HallTbDTO hall, int cinemaId) {
        if (hall == null || hall.getHallId() == 0 || cinemaId == 0) {
            throw new NullPointerException("不存在此影厅");
        }
        HallTbDTO hallById = hallDAO.getHallById(hall.getHallId(), cinemaId);
        hallById.setHallName(hall.getHallName());
        hallById.setHallScreenType(hall.getHallScreenType());
        hallById.setHallDescribe(hall.getHallDescribe());
        hallDAO.saveEntity(hallById);
    }

    /**
     * 新增影厅
     *
     * @param hall
     * @param cinemaId
     */
    @Transactional
    @Override
    public String saveHall(HallTbDTO hall, int cinemaId) {
        if (hall == null || hall.getHallMaxRow() == 0 || hall.getHallMaxCol() == 0 || cinemaId == 0) {
            return null;
        }
        CinemaTbDTO cinema = cinemaDAO.getEntityById(CinemaTbDTO.class, cinemaId);
        hall.setCinemaTbDTO(cinema);
        Serializable serializable = hallDAO.saveEntity(hall);
        hall.setHallId((int)serializable);
        HashSet<SeatTbDTO> seatTbDTOS = new HashSet<>();
        Integer hallMaxCol = hall.getHallMaxCol();
        Integer hallMaxRow = hall.getHallMaxRow();
        for (int i = 1; i <= hallMaxCol; i++) {
            for (int j = 1; j <= hallMaxRow; j++) {
                SeatTbDTO seatTbDTO = new SeatTbDTO(i, j, hall);
                seatTbDTOS.add(seatTbDTO);
            }
        }
        try {
            hall.setSeatTbDTOSet(seatTbDTOS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        hallDAO.saveEntity(hall);
        return "-1";
    }
}
