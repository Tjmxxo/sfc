package cc.xpress.dao.impl;

import cc.xpress.bean.dto.VipTbDTO;
import cc.xpress.dao.IVipDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("vipDAO")
public class VipDAOImpl extends BaseDAOImpl<VipTbDTO> implements IVipDAO {


    @Override
    public List<VipTbDTO> getAllVipByCinemaId(int cinemaId) {
        String hql = "from VipTbDTO where cinemaTbDTO.cinemaId = ?";
        return sessionFactory.getCurrentSession().createQuery
                (hql, VipTbDTO.class).setParameter(0, cinemaId).
                list();
    }
}
