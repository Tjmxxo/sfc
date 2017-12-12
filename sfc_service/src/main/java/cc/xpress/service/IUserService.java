package cc.xpress.service;

import cc.xpress.bean.dto.UserTbDTO;

import java.util.List;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-28 0:26
 * @modified By:
 */
public interface IUserService {
    /**
     * 根据id查询
     *
     * @param userTbDTO
     * @return
     */
    UserTbDTO getUserById(UserTbDTO userTbDTO);

    /**
     * 通过账号获取用户
     *
     * @param userAccount
     * @return
     */
    UserTbDTO getUserByAccount(String userAccount);

    /**
     * 用户账户重复验证
     *
     * @param userAccount
     * @return
     */
    Boolean registerRe(String userAccount);

    /**
     * 用户注册保存
     *
     * @param user
     * @param cinemaId
     * @return
     */
    UserTbDTO userRegister(UserTbDTO user, int cinemaId);

    /**
     * 通过电影院id获取所有用户
     *
     * @return
     */
    List<UserTbDTO> getAllUserByCinemaId();
}
