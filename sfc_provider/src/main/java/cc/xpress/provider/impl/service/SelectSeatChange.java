package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.PlanTbDTO;
import cc.xpress.bean.dto.SeatTbDTO;
import cc.xpress.bean.dto.SelectTbDTO;
import cc.xpress.dao.IPlanDAO;
import cc.xpress.dao.ISelectDAO;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @Create By Tjmxxo
 */
@Service("selectSeatChange")
public class SelectSeatChange {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SelectSeatChange.class);

    @Autowired
    private IPlanDAO planDAO;

    @Autowired
    private ISelectDAO selectDAO;

    @Transactional
    public synchronized void planStartSeatChange() throws IllegalArgumentException, ParseException {
        System.out.println(1);
        long time = new Date(System.currentTimeMillis()).getTime();
        List<PlanTbDTO> allPlanStart = planDAO.getAllPlanStart();
        Iterator<PlanTbDTO> iterator = allPlanStart.iterator();
        while (iterator.hasNext()) {
            PlanTbDTO next = iterator.next();
            long time1 = next.getPlanStartTime().getTime();
            if (time1 < time) {
                System.out.println("timesStart------------------------------------------------------------");
                System.out.println("当前时间" + time + new Date(time).toString());
                System.out.println("数据库时间" + time1 + new Date(time1).toString());
                PlanTbDTO plan = planDAO.getEntityById(PlanTbDTO.class, next.getPlanId());
                plan.setPlanStatus(1);
                planDAO.saveEntity(plan);
                System.out.println(plan.getPlanId());
                Set<SeatTbDTO> seatTbDTOSet = plan.getHallTbDTO().getSeatTbDTOSet();
                if (seatTbDTOSet == null) {
                    throw new IllegalArgumentException("错误的影厅!!");
                }
                Iterator<SeatTbDTO> iterator1 = seatTbDTOSet.iterator();
                while (iterator1.hasNext()) {
                    SeatTbDTO next1 = iterator1.next();
                    System.out.println(next1.getSeatId() + "开始保存");
                    selectDAO.saveEntity(new SelectTbDTO(0, next1, plan));
                    System.out.println(next1.getSeatId() + "保存结束");
                }
            }
        }
    }


    @Transactional
    public synchronized void planEndSeatChange() throws ParseException {
        System.out.println(1);
        long time = new Date(System.currentTimeMillis()).getTime();
        List<PlanTbDTO> allPlanStart = planDAO.getAllPlanEnd();
        Iterator<PlanTbDTO> iterator = allPlanStart.iterator();
        while (iterator.hasNext()) {
            PlanTbDTO next = iterator.next();
            String s = next.getPlanTime().toString();
            String s1 = next.getPlanData().toString();
            String times = s1 + "-" + s;
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-hh:mm:ss");
            long time2 = simpleDateFormat.parse(times).getTime();
            if (time2 < time) {
                System.out.println("timesEnd------------------------------------------------------------");
                System.out.println("当前时间" + time + new Date(time).toString());
                System.out.println("数据库时间" + time2 + new Date(time2).toString());
                PlanTbDTO plan = null;
                try {
                    plan = planDAO.getEntityById(PlanTbDTO.class, next.getPlanId());
                } catch (HibernateException e) {
                    logger.error(e.getMessage());
                }
                System.out.println(next.getPlanId());
                plan.setPlanStatus(-1);
                try {
                    planDAO.saveEntity(plan);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(next.getPlanId());
            }
        }


    }
}
