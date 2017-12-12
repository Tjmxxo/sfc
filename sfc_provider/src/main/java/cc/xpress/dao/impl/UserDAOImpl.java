package cc.xpress.dao.impl;

import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.bean.vo.Node;
import cc.xpress.dao.IUserDAO;
import org.springframework.stereotype.Repository;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-10-25 21:33
 * @modified By:
 */
@Repository("userDAO")
public class UserDAOImpl extends BaseDAOImpl<UserTbDTO> implements IUserDAO {

    public UserTbDTO getUserByAccount(String userAccount) {
        return (UserTbDTO) query("from", UserTbDTO.class, new Node("userAccount", userAccount)).
                uniqueResult();
    }
}
