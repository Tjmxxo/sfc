package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.*;
import cc.xpress.dao.IHallDAO;
import cc.xpress.dao.IMovieDAO;
import cc.xpress.dao.IPlanDAO;
import cc.xpress.service.IPlanTimerService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;

/**
 * @Create By Tjmxxo
 */
@Service("planTimerServieImpl")
public class PlanTimerServieImpl implements IPlanTimerService {

    @Autowired
    private IPlanDAO planDAO;

    @Autowired
    private IMovieDAO movieDAO;

    @Autowired
    private IHallDAO hallDAO;

    /**
     * 设置场次开始时间
     */
    @Transactional
    @Override
    public void setPlanTimer(PlanTbDTO plan, String startTime, String time, int hallId, int movieId) throws ParseException, IllegalArgumentException, SchedulerException {
        if (plan == null || time == null || time == null || hallId == 0 || movieId == 0) {
            throw new IllegalArgumentException("错误的影厅信息");
        }
        long startTimeL = Long.parseLong(startTime);
        long timeL = Long.parseLong(time);
        Date date = new Date(timeL);
        Time time1 = new Time(timeL);
        java.util.Date date1 = new java.util.Date(timeL);
        Timestamp timestamp = new Timestamp(startTimeL);
        HallTbDTO hallTbDTO = hallDAO.getEntityById(HallTbDTO.class, hallId);
        MovieTbDTO movieTbDTO = movieDAO.getEntityById(MovieTbDTO.class, movieId);
        if (hallTbDTO == null || movieTbDTO == null) {
            throw new IllegalArgumentException("错误的影厅信息");
        }
        CinemaTbDTO cinemaTbDTO = hallTbDTO.getCinemaTbDTO();
        if (cinemaTbDTO == null) {
            throw new IllegalArgumentException("错误的影厅信息");
        }
        CityTbDTO cityTbDTO = cinemaTbDTO.getCityTbDTO();
        if (cityTbDTO == null) {
            throw new IllegalArgumentException("错误的影厅信息");
        }
        int i = plan.getPlanPrice() * 100;
        plan.setPlanPrice(i);
        plan.setCinemaTbDTO(cinemaTbDTO);
        plan.setCityTbDTO(cityTbDTO);
        plan.setPlanTime(time1);
        plan.setPlanData(date);
        plan.setPlanStartTime(timestamp);
        plan.setHallTbDTO(hallTbDTO);
        plan.setMovieTbDTO(movieTbDTO);
        plan.setPlanStatus(0);
        planDAO.saveEntity(plan);
    }
}
