package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.PlanTbDTO;
import cc.xpress.bean.vo.PlanVo;
import cc.xpress.config.PlanStatus;
import cc.xpress.dao.IPlanDAO;
import cc.xpress.service.IPlanService;
import cc.xpress.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Create By Tjmxxo
 */
@Service("planService")
public class PlanServicelmpl implements IPlanService {

    @Autowired
    private IPlanDAO planDAO;

    @Transactional
    @Override
    public List<PlanVo> getTodayPlanList(int cinemaId) throws ParseException {
        List<PlanVo> planVos = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(date);
        java.util.Date parse = simpleDateFormat.parse(format);
        Date date1 = new Date(parse.getTime());
        List<PlanTbDTO> planTbDTO = planDAO.getPlanTbDTO(cinemaId, date1);
        System.out.println("-----------------------------------------------------"+date1.toString());
        Iterator<PlanTbDTO> iterator = planTbDTO.iterator();
        while (iterator.hasNext()) {
            PlanTbDTO next = iterator.next();
            planVos.add(new PlanVo(next));
        }

        return planVos;
    }

    @Transactional
    @Override
    public List<PlanVo> getAllPlanList(int cinemaId) {
        List<PlanVo> planVos = new ArrayList<>();
        List<PlanTbDTO> planTbDTO = planDAO.getPlanTbDTOById(cinemaId);
        Iterator<PlanTbDTO> iterator = planTbDTO.iterator();
        while (iterator.hasNext()) {
            PlanTbDTO next = iterator.next();
            planVos.add(new PlanVo(next));
        }
        return planVos;
    }

    @Transactional
    @Override
    public PlanTbDTO getPlanBtId(int PlanId) {
        return planDAO.getEntityById(PlanTbDTO.class, PlanId);
    }

    /**
     * 根据cityId planStatus查询 plan 并且根据 movieId分组
     *
     * @param movieId
     * @param cityId
     * @param cinemaId
     * @return
     */
    @Transactional
    @Override
    public List<PlanTbDTO> getPlanByMovieAndCityAndCinemaGroupByDate(String movieId, String cityId, String cinemaId, String planDate) {
        if (!CommonUtils.isConvertedToNumber(movieId) || !CommonUtils.isConvertedToNumber(cityId) || !CommonUtils.isConvertedToNumber(cinemaId)) {
            return null;
        }
        int cId = Integer.parseInt(cityId);
        int mId = Integer.parseInt(movieId);
        int cmId = Integer.parseInt(cinemaId);
        if (planDate == null) {
            return planDAO.getPlanByMovieAndCityAndCinemaGroupByDate(mId, cId, cmId, PlanStatus.ENABLE_STATUE);
        }
        Date date = new Date(Long.parseLong(planDate));
        return planDAO.getPlanByMovieAndCityAndCinemaAndDate(mId, cId, cmId, date, PlanStatus.ENABLE_STATUE);
    }
}
