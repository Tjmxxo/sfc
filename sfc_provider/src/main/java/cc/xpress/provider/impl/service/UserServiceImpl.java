package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.RoleTbDTO;
import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.bean.vo.Node;
import cc.xpress.config.ManagerConfig;
import cc.xpress.dao.IRoleDAO;
import cc.xpress.dao.IUserDAO;
import cc.xpress.service.IUserService;
import cc.xpress.utils.CommonUtils;
import cc.xpress.utils.EncryptUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-28 0:33
 * @modified By:
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Autowired
    IRoleDAO roleDAO;

    Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Transactional
    @Override
    public UserTbDTO getUserById(UserTbDTO userTbDTO) throws HibernateException {
        return userDAO.getEntityById(UserTbDTO.class, userTbDTO.getUserId());
    }

    @Transactional
    @Override
    public UserTbDTO getUserByAccount(String userAccount) throws HibernateException {
        return userDAO.getUserByAccount(userAccount);
    }

    @Transactional
    @Override
    public Boolean registerRe(String userAccount) throws HibernateException {
        if (userDAO.query("from", UserTbDTO.class, new Node("userAccount", userAccount)).uniqueResult() != null) {
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public UserTbDTO userRegister(UserTbDTO user, int cinemaId) throws IllegalArgumentException {
        String code = CommonUtils.getCode(16);
        user.setUserSalt(code);
        Set<RoleTbDTO> roleTbDTOSet = new HashSet<>();
        RoleTbDTO role = roleDAO.getEntityById(RoleTbDTO.class, cinemaId);
        if (role == null) {
            throw new IllegalArgumentException("没有此权限");
        }
        roleTbDTOSet.add(role);
        user.setRoleTbDTOSet(roleTbDTOSet);
        user.setUserPassword(EncryptUtils.encryptPassword(user.getUserPassword(), code));
        try {
            userDAO.saveEntity(user);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(user.getUserAccount() + "注册失败");
            throw new IllegalArgumentException(user.getUserAccount() + "注册失败");
        }
        logger.info(user.getUserAccount() + ManagerConfig.REGISTER_SUCCESS);
        return user;
    }

    /**
     * 通过电影院id获取所有用户
     *
     * @return
     */
    @Override
    public List<UserTbDTO> getAllUserByCinemaId() {
        return null;
    }
}
