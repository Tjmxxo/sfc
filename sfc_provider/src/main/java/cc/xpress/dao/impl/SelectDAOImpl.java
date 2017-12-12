package cc.xpress.dao.impl;

import cc.xpress.bean.dto.SelectTbDTO;
import cc.xpress.config.SelectStatus;
import cc.xpress.dao.ISelectDAO;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository("selectDAO")
public class SelectDAOImpl extends BaseDAOImpl<SelectTbDTO> implements ISelectDAO {
    /**
     * 查询座位是否可以购买，并且加锁
     *
     * @param planId
     * @param seatId
     * @return
     */
    public SelectTbDTO getSelectWithLock(int planId, int seatId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SelectTbDTO st where seatTbDTO.seatId=? and st.planTbDTO.planId=? and  st.seatStatus=?");
        query.setParameter(0, seatId);
        query.setParameter(1, planId);
        query.setParameter(2, SelectStatus.ENABLE_STATUE);
        query.setLockMode("st", LockMode.UPGRADE);
        SelectTbDTO selectTbDTO = (SelectTbDTO) query.uniqueResult();
        return selectTbDTO;
    }
    /**
     * 根据planId和seatId查询select
     * @param seatId
     * @param planId
     * @return
     */
    public SelectTbDTO getSelectByPlanAndSeatId(int seatId,int planId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from SelectTbDTO st where seatTbDTO.seatId=? and st.planTbDTO.planId=?");
        query.setParameter(0, seatId);
        query.setParameter(1, planId);
        SelectTbDTO selectTbDTO = (SelectTbDTO) query.uniqueResult();
        return selectTbDTO;
    }
}
