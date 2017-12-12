package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.OrderSeatTbDTO;
import cc.xpress.bean.dto.OrderTbDTO;
import cc.xpress.bean.dto.PlanTbDTO;
import cc.xpress.bean.dto.SeatTbDTO;
import cc.xpress.dao.*;
import cc.xpress.service.IUrgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @Create By Tjmxxo
 */
@Service("urgencyService")
public class UrgencyServiceImpl implements IUrgencyService {

    @Autowired
    private IPlanDAO planDAO;

    @Autowired
    private ICinemaDAO cinemaDAO;

    @Autowired
    private ISeatDAO seatDAO;

    @Autowired
    private ISelectDAO selectDAO;

    @Autowired
    private IOrderDAO orderDAO;

    @Autowired
    private IVipDAO vipDAO;

    @Autowired
    private IHallDAO hallDAO;

    @Autowired
    private IOrderSeatDAO orderSeatDAO;

    /**
     * 改变影厅状态,停止售票,退还已购票
     *
     * @param id
     * @return
     */
    @Transactional
    @Override
    public String planStateChange(int id, long longTime, int state) {
        if (id == 0 || longTime == 0) {
            return null;
        }
        List<PlanTbDTO> planListByTime = null;
        if (state == 1) {
            planListByTime = planDAO.getPlanListByTime(id, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + longTime));
        } else {
            planListByTime = planDAO.getAllPlanByTime(id, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + longTime));
        }
        if (planListByTime.size() != 0) {
            Iterator<PlanTbDTO> iterator = planListByTime.iterator();
            while (iterator.hasNext()) {
                PlanTbDTO next = iterator.next();
                next.setPlanStatus(-1);

                planDAO.updateEntity(next);

                List<OrderTbDTO> allOrderBy = orderDAO.getAllOrderBy(next.getPlanId());
                if (allOrderBy.size() != 0) {
                    Iterator<OrderTbDTO> iterator1 = allOrderBy.iterator();
                    while (iterator1.hasNext()) {
                        OrderTbDTO next1 = iterator1.next();
                        next1.setOrderStatus(-1);
                        orderDAO.updateEntity(next1);
                    }
                }
            }
        }
        return "-1";
    }

    /**
     * 改变座位状态,停止售票,退还已购票
     *
     * @param seatId
     * @return
     */
    @Transactional
    @Override
    public String seatStateChange(int seatId) {
        if (seatId == 0) {
            return null;
        }
        SeatTbDTO entityById = seatDAO.getEntityById(SeatTbDTO.class, seatId);
        entityById.setSeatStatus(-1);
        seatDAO.saveEntity(entityById);
        OrderSeatTbDTO allOrderSeatBySeatId = orderSeatDAO.getAllOrderSeatBySeatId(seatId);
        if (allOrderSeatBySeatId != null) {
            OrderTbDTO orderTbDTO = allOrderSeatBySeatId.getOrderTbDTO();
            if (orderTbDTO != null) {
                orderTbDTO.setOrderStatus(-1);
                orderDAO.saveEntity(orderTbDTO);
            }
        }
        return "-1";
    }

    /**
     * 停止影院售票,退还已购票
     *
     * @return
     */
    @Transactional
    @Override
    public String cinemaStateChange(int cinemaId) {

        return "-1";
    }
}
