package cc.xpress.dao.impl;

import cc.xpress.bean.dto.PlanTbDTO;
import cc.xpress.dao.IPlanDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository("planDAO")
public class PlanDAOImpl extends BaseDAOImpl<PlanTbDTO> implements IPlanDAO {
    public List<PlanTbDTO> getPlanTbDTO(int cinemaId, Date date) {
        String hql = "from PlanTbDTO where hallTbDTO.cinemaTbDTO.cinemaId = ?  and planStatus = ?";
        return sessionFactory.getCurrentSession().createQuery
                (hql, PlanTbDTO.class).setParameter(0, cinemaId).
                setParameter(1, 1).
                list();
    }

    @Override
    public List<PlanTbDTO> getPlanTbDTOById(int cinemaId) {
        String hql = "from PlanTbDTO where hallTbDTO.cinemaTbDTO.cinemaId = ?";
        return sessionFactory.getCurrentSession().createQuery
                (hql, PlanTbDTO.class).
                setParameter(0, cinemaId).
                list();
    }

    /**
     * 根据cityId planStatus查询 plan 并且根据 movieId分组
     *
     * @param cityId
     * @return
     */
    @Override
    public List<PlanTbDTO> getPlanByCityIdGroupByMovieId(int cityId, int planStatus) {
        String hql = "from PlanTbDTO plan  join fetch  plan.movieTbDTO where plan.cityTbDTO.cityId=? and plan.planStatus=? group by plan.movieTbDTO";
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter(0, cityId);
        query.setParameter(1, planStatus);
        List list = query.list();
        return list;
    }

    /**
     * 根据电影名称和城市查询
     *
     * @param movieId
     * @param cityId
     * @param planStatus
     * @return
     */
    public List<PlanTbDTO> getPlanByMovieAndCityGroupByCinema(int movieId, int cityId, int planStatus) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PlanTbDTO plan join fetch  plan.cinemaTbDTO where plan.cityTbDTO.cityId=? and plan.movieTbDTO.movieId=? and plan.planStatus=? group by plan.cinemaTbDTO");
        query.setParameter(0, cityId);
        query.setParameter(1, movieId);
        query.setParameter(2, planStatus);
        return query.list();
    }

    /**
     * 根据城市id 电影id 电影院id 查询播放日期
     *
     * @param movieId
     * @param cityId
     * @param planStatus
     * @return
     */
    public List<PlanTbDTO> getPlanByMovieAndCityAndCinemaGroupByDate(int movieId, int cityId, int cinemaId, int planStatus) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PlanTbDTO where cityTbDTO.cityId=? and movieTbDTO.movieId=?  and cinemaTbDTO.cinemaId=? and planStatus=? group by planData");
        query.setParameter(0, cityId);
        query.setParameter(1, movieId);
        query.setParameter(2, cinemaId);
        query.setParameter(3, planStatus);
        return query.list();
    }

    /**
     * 根据城市id 电影id 电影院id 计划日期 查询播放场次
     *
     * @param movieId
     * @param cityId
     * @param cinemaId
     * @param planDate
     * @param planStatus
     * @return
     */
    public List<PlanTbDTO> getPlanByMovieAndCityAndCinemaAndDate(int movieId, int cityId, int cinemaId, Date planDate, int planStatus) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PlanTbDTO where cityTbDTO.cityId=? and movieTbDTO.movieId=?  and cinemaTbDTO.cinemaId=?  and planData=? and planStatus=?");
        query.setParameter(0, cityId);
        query.setParameter(1, movieId);
        query.setParameter(2, cinemaId);
        query.setParameter(3, planDate);
        query.setParameter(4, planStatus);
        return query.list();
    }

    @Override
    public PlanTbDTO getPlanInfo(int planId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from PlanTbDTO plan join fetch plan.movieTbDTO  join fetch plan.cinemaTbDTO join fetch  plan.hallTbDTO where plan.planId=?");
        query.setParameter(0, planId);
        PlanTbDTO planTbDTO = (PlanTbDTO) query.uniqueResult();
        return planTbDTO;
    }

    @Override
    public List<PlanTbDTO> getAllPlanStart() {
        return sessionFactory.getCurrentSession().createQuery
                ("from PlanTbDTO where planStatus = ?", PlanTbDTO.class).
                setParameter(0, 0).
                list();
    }

    public List<PlanTbDTO> getAllPlanEnd() {
        return sessionFactory.getCurrentSession().createQuery
                ("from PlanTbDTO where planStatus = ?", PlanTbDTO.class).
                setParameter(0, 1).
                list();
    }

    public List<PlanTbDTO> getPlanListByTime(int hallId, Date startDate, Date endDate) {
        String hql = "from PlanTbDTO plan where plan.hallTbDTO.hallId = ? and plan.planData between ? and ? and plan.planStatus != -1";
        List<PlanTbDTO> list = sessionFactory.getCurrentSession().createQuery
                (hql, PlanTbDTO.class).
                setParameter(0, hallId).
                setParameter(1, startDate).
                setParameter(2, endDate).
                list();
        return list;
    }

    @Override
    public List<PlanTbDTO> getAllPlanByTime(int hallId, Date startDate, Date endDate) {
        String hql = "from PlanTbDTO plan where plan.cinemaTbDTO.cinemaId = ? and plan.planData between ? and ? and plan.planStatus != -1";
        List<PlanTbDTO> list = sessionFactory.getCurrentSession().createQuery
                (hql, PlanTbDTO.class).
                setParameter(0, hallId).
                setParameter(1, startDate).
                setParameter(2, endDate).
                list();
        return list;
    }

    public List<PlanTbDTO> getPlanListByTime(int hallId, Date startDate) {
        String hql = "from PlanTbDTO plan where plan.hallTbDTO.hallId = ? and plan.planData between ? and ? and plan.planStatus != -1";
        List<PlanTbDTO> list = sessionFactory.getCurrentSession().createQuery
                (hql, PlanTbDTO.class).
                setParameter(0, hallId).
                setParameter(1, startDate).
                list();
        return list;
    }
}
