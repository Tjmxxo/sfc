package cc.xpress.service;

import cc.xpress.bean.dto.CinemaTbDTO;
import cc.xpress.bean.dto.UserTbDTO;

/**
 * @Create By Tjmxxo
 */
public interface ICinemaChangeImpl {
    /**
     * 根据用户查询影院id
     *
     * @param user
     * @return
     */
    int getCinemaId(UserTbDTO user);

    /**
     * 修改影院信息
     */
    void cinemaChange(CinemaTbDTO cinema, int cinemaId);

    /**
     * 显示影院信息
     *
     * @param cinemaId
     * @return
     */
    CinemaTbDTO cinemaDetail(int cinemaId);
}
