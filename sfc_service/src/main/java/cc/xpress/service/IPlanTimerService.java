package cc.xpress.service;

import cc.xpress.bean.dto.PlanTbDTO;
import org.quartz.SchedulerException;

import java.text.ParseException;

/**
 * @Create By Tjmxxo
 */
public interface IPlanTimerService {

    /**
     * 设置场次开始时间
     */
    void setPlanTimer(PlanTbDTO plan, String startTime, String time, int id, int cinemaId) throws ParseException, SchedulerException;
}
