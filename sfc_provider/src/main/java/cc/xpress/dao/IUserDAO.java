package cc.xpress.dao;


import cc.xpress.bean.dto.UserTbDTO;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-10-25 21:31
 * @modified By:
 */
public interface IUserDAO extends IBaseDAO<UserTbDTO> {
    UserTbDTO getUserByAccount(String userAccount);
}
