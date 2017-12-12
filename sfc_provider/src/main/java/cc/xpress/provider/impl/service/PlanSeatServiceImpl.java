package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.PlanTbDTO;
import cc.xpress.bean.dto.SelectTbDTO;
import cc.xpress.dao.IPlanDAO;
import cc.xpress.service.IPlanSeatService;
import cc.xpress.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-03 15:18
 * @modified By:
 */
@Service("planSeatService")
public class PlanSeatServiceImpl implements IPlanSeatService {
    @Autowired
    IPlanDAO planDAO;

    /**
     * 获取计划座位
     *
     * @param planId
     * @return
     */
    @Transactional
    @Override
    public Map<Integer, List<SelectTbDTO>> getPlanSeat(String planId) {
        if (!CommonUtils.isConvertedToNumber(planId)) {
            //TODO
            throw new IllegalArgumentException();
        }
        int id = Integer.parseInt(planId);
        PlanTbDTO planTbDTO = planDAO.getEntityById(PlanTbDTO.class, id);
        if (planTbDTO == null) {
            //TODO
            throw new NullPointerException();
        }
        Iterator<SelectTbDTO> iterator = planTbDTO.getSelectTbDTOSet().iterator();
        HashMap<Integer, List<SelectTbDTO>> selectMap = new HashMap<>();
        while (iterator.hasNext()) {
            SelectTbDTO next = iterator.next();
            Integer seatRow = next.getSeatTbDTO().getSeatRow();
            List<SelectTbDTO> selectTbDTOS = selectMap.get(seatRow);
            if (selectTbDTOS == null) {
                selectTbDTOS = new ArrayList<>();
                selectMap.put(seatRow, selectTbDTOS);
            }
            selectTbDTOS.add(next);
        }
        return selectMap;
    }

    /**
     * 根据id查询计划
     *
     * @param planId
     * @return
     */
    @Override
    @Transactional
    public PlanTbDTO getPlanInfo(String planId) {
        if (!CommonUtils.isConvertedToNumber(planId)) {
            //TODO
            throw new IllegalArgumentException();
        }
        int id = Integer.parseInt(planId);
        return planDAO.getPlanInfo(id);
    }
}
