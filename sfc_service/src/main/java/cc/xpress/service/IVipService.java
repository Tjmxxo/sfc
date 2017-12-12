package cc.xpress.service;

import cc.xpress.bean.vo.VipVo;

import java.util.List;

/**
 * @Create By Tjmxxo
 */
public interface IVipService {
    /**
     * 获取所有会员卡信息
     *
     * @return
     */
    List<VipVo> getAllVip(int cinemaId);

    /**
     * 增加vip会员卡,数量可自定
     *
     * @param num
     */
    void addVip(int num, int cinemaId);
}
