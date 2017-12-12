package cc.xpress.plantimer;

import cc.xpress.bean.dto.PlanTbDTO;
import org.quartz.SchedulerException;

import java.util.Date;

/**
 * @Create By Tjmxxo
 */
public class PlanSetter {
    public synchronized static void setPlanTimer(PlanTbDTO plan, Date date) throws SchedulerException {
        if (plan == null || plan.getPlanData() == null || plan.getPlanStartTime() == null || plan.getPlanTime() == null) {
            throw new IllegalArgumentException("错误的时间!!");
        }
        long start = plan.getPlanStartTime().getTime();
        Date startD = new Date(start);
        String movieName = plan.getMovieTbDTO().getMovieName() + plan.getPlanId();
        String hallName = plan.getHallTbDTO().getHallName() + plan.getPlanId();
        Integer planId = plan.getPlanId();
        TimerStart timerStart = new TimerStart();
//        timerStart.startTimer(plan.getPlanStartTime(),startD,plan.getPlanId());
    }
}
