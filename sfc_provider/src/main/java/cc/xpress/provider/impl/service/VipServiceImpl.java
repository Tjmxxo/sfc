package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.CinemaTbDTO;
import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.bean.dto.VipTbDTO;
import cc.xpress.bean.vo.VipVo;
import cc.xpress.dao.ICinemaDAO;
import cc.xpress.dao.IVipDAO;
import cc.xpress.service.IVipService;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @Create By Tjmxxo
 */
@Service("vipService")
public class VipServiceImpl implements IVipService {

    @Autowired
    private IVipDAO vipDAO;

    @Autowired
    private ICinemaDAO cinemaDAO;

    /**
     * 获取所有会员卡信息
     *
     * @return
     */
    @Transactional
    @Override
    public List<VipVo> getAllVip(int cinemaId) throws NullPointerException {
        if (cinemaId == 0) {
            throw new NullPointerException("不存在此影院");
        }
        List<VipTbDTO> vipTbDTOList = vipDAO.getAllVipByCinemaId(cinemaId);
        if (vipTbDTOList == null) {
            throw new NullPointerException("该影院没有会员卡");
        }
        Iterator<VipTbDTO> iterator = vipTbDTOList.iterator();
        ArrayList<VipVo> vipVos = new ArrayList<>();
        while (iterator.hasNext()) {
            VipTbDTO next = iterator.next();
            UserTbDTO userTbDTO = next.getUserTbDTO();
            String userName = "未售出";
            if (userTbDTO != null) {
                userName = userTbDTO.getUserAccount();
            }
            vipVos.add(new VipVo(next, userName));
        }
        return vipVos;
    }

    /**
     * 增加vip会员卡,数量可自定
     *
     * @param num
     */


    @Transactional
    @Override
    public void addVip(int num, int cinemaId) throws IllegalArgumentException, HibernateException {
        if (num == 0) {
            throw new IllegalArgumentException("错误的会员卡数量,需要大于0");
        }
        if (cinemaId == 0) {
            throw new IllegalArgumentException("不存在此影院");
        }
        CinemaTbDTO cinema = cinemaDAO.getEntityById(CinemaTbDTO.class, cinemaId);
        if (cinema == null) {
            throw new IllegalArgumentException("不存在此影院");
        }
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            long l = System.currentTimeMillis() + random.nextInt(10000);
            String s = String.valueOf(l);
            vipDAO.saveEntity(new VipTbDTO(s, 0, cinema));
        }
    }
}
