package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.CinemaTbDTO;
import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.dao.ICinemaDAO;
import cc.xpress.dao.IUserDAO;
import cc.xpress.service.ICinemaChangeImpl;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Create By Tjmxxo
 */
@Service("cinemaService2")
public class CinemaChangeImpl implements ICinemaChangeImpl {

    @Autowired
    private ICinemaDAO cinemaDAO;

    @Autowired
    private IUserDAO userDAO;

    /**
     * 根据用户查询影院id
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public int getCinemaId(UserTbDTO user) throws IllegalArgumentException {
        if (user == null) {
            throw new IllegalArgumentException("用户为空");
        }
        CinemaTbDTO cinemaTbDTO = null;
        try {
            cinemaTbDTO = userDAO.getUserByAccount(user.getUserAccount()).getCinemaTbDTO();
        } catch (Exception e) {
            return 0;
        }
        if (cinemaTbDTO!= null){
            return cinemaTbDTO.getCinemaId();
        }
        return 0;
    }

    /**
     * 修改影院信息
     */
    @Override
    @Transactional
    public void cinemaChange(CinemaTbDTO cinema, int cinemaId) throws HibernateException, IllegalArgumentException {
        if (cinema == null || cinemaId == 0) {
            throw new IllegalArgumentException("此影院不存在");
        }
        CinemaTbDTO oCinema = cinemaDAO.getEntityById(CinemaTbDTO.class, cinemaId);
        oCinema.setCinemaName(cinema.getCinemaName());
        oCinema.setCinemaDescribe(cinema.getCinemaDescribe());
        oCinema.setCinemaAddress(cinema.getCinemaAddress());
        oCinema.setCinemaTel(cinema.getCinemaTel());
        oCinema.setCinemaBusinessHours(cinema.getCinemaBusinessHours());
        cinemaDAO.saveEntity(oCinema);
    }

    /**
     * 显示影院信息
     *
     * @param cinemaId
     * @return
     */
    @Transactional
    @Override
    public CinemaTbDTO cinemaDetail(int cinemaId) throws IllegalArgumentException {
        if (cinemaId == 0) {
            throw new IllegalArgumentException("此影院不存在");
        }
        return cinemaDAO.getEntityById(CinemaTbDTO.class, cinemaId);
    }


}
