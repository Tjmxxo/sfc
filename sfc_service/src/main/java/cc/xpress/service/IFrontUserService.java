package cc.xpress.service;

import cc.xpress.bean.dto.UserTbDTO;
import com.mchange.util.DuplicateElementException;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-30 8:38
 * @modified By:
 */
public interface IFrontUserService {
    /**
     * 普通用户注册和等登陆
     *
     * @param userTbDTO
     * @return
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    UserTbDTO userRegister(UserTbDTO userTbDTO) throws NullPointerException, DuplicateElementException;

    /**
     * 根据tel查询
     *
     * @param userTbDTO
     * @return
     */
    UserTbDTO getUserByTel(UserTbDTO userTbDTO) throws NullPointerException;

    /**
     * 修改用户信息
     *
     * @param userTbDTO
     * @return
     */
    void modifyUser(UserTbDTO userTbDTO) throws NullPointerException;

    /**
     * 用户密码修改
     *
     * @param userTbDTO
     * @param newPassword
     * @param oldPassword
     * @return
     */
    void modifyUserPassword(UserTbDTO userTbDTO, String newPassword, String oldPassword);
}
