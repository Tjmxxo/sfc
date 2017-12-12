package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.RoleTbDTO;
import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.bean.vo.Node;
import cc.xpress.config.CommonNotice;
import cc.xpress.config.FrontUserConfig;
import cc.xpress.dao.IRoleDAO;
import cc.xpress.dao.IUserDAO;
import cc.xpress.service.IFrontUserService;
import cc.xpress.utils.CommonUtils;
import cc.xpress.utils.EncryptUtils;
import com.mchange.util.DuplicateElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashSet;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-30 8:43
 * @modified By:
 */
@Service("frontUserService")
public class FrontUserServiceImpl implements IFrontUserService {
    @Autowired
    IUserDAO userDAO;
    @Autowired
    IRoleDAO roleDAO;

    /**
     * 用户注册
     *
     * @param userTbDTO
     * @return
     */
    @Override
    @Transactional
    public UserTbDTO userRegister(UserTbDTO userTbDTO) throws NullPointerException, DuplicateElementException {
        if (userTbDTO == null || userTbDTO.getUserPassword() == null || userTbDTO.getUserTel() == null) {
            throw new NullPointerException("手机号或密码为空");
        }
        if (userDAO.query("from", UserTbDTO.class, new Node("userTel", userTbDTO.getUserTel())).list().size() != 0) {
            throw new DuplicateElementException("手机号不能重复");
        }
        String userPassword = userTbDTO.getUserPassword();
        String salt = CommonUtils.getCode(16);
        userPassword = EncryptUtils.encryptPassword(userPassword, salt);
        userTbDTO.setUserPassword(userPassword);
        userTbDTO.setUserSalt(salt);
        userTbDTO.setUserAccount(userTbDTO.getUserTel());
        RoleTbDTO roleTbDTO = roleDAO.getEntityById(RoleTbDTO.class, FrontUserConfig.DEFAULT_ROLE_ID);
        if (roleTbDTO == null) {
            throw new NullPointerException(CommonNotice.ROLE_NOT_EXITS);
        }
        HashSet<RoleTbDTO> roleTbDTOS = new HashSet<>();
        roleTbDTOS.add(roleTbDTO);
        userTbDTO.setRoleTbDTOSet(roleTbDTOS);
        Serializable serializable = userDAO.saveEntity(userTbDTO);
        userTbDTO.setUserId((Integer) serializable);
        return userTbDTO;
    }

    /**
     * 根据手机号查询用户
     *
     * @param userTbDTO
     * @return
     */
    @Transactional
    @Override
    public UserTbDTO getUserByTel(UserTbDTO userTbDTO) throws NullPointerException {
        if (userTbDTO == null || userTbDTO.getUserTel() == null) {
            throw new NullPointerException("手机号不能为空");
        }
        UserTbDTO userTbDTO1 = (UserTbDTO) userDAO.query("from", UserTbDTO.class, new Node("userTel", userTbDTO.getUserTel())).uniqueResult();
        return userTbDTO1;
    }

    /**
     * 修改用户信息
     *
     * @param userTbDTO
     * @return
     */
    @Transactional
    @Override
    public void modifyUser(UserTbDTO userTbDTO) throws NullPointerException {
        if (userTbDTO == null || userTbDTO.getUserId() == null || userTbDTO.getUserTel() == null || userTbDTO.getUserAccount() == null) {
            throw new NullPointerException(CommonNotice.MODIFY_FAILED);
        }
        userDAO.updateEntity(userTbDTO);
    }

    /**
     * 修改密码
     *
     * @param userTbDTO
     * @param newPassword
     * @param oldPassword
     * @return
     */
    @Transactional
    @Override
    public void modifyUserPassword(UserTbDTO userTbDTO, String newPassword, String oldPassword) {
        if (userTbDTO == null || userTbDTO.getUserPassword() == null || userTbDTO.getUserSalt() == null) {
            throw new NullPointerException(CommonNotice.USER_NOT_LOGIN_IN);
        }
        if (oldPassword == null || newPassword == null) {
            throw new NullPointerException(CommonNotice.NULL_PASSWORD);
        }
        String salt = userTbDTO.getUserSalt();
        /*加密旧用户密码*/
        oldPassword = EncryptUtils.encryptPassword(oldPassword, salt);
        /*判断旧密码是否正确*/
        if (!oldPassword.equals(userTbDTO.getUserPassword())) {
            throw new IllegalArgumentException(CommonNotice.ERROR_PASSWORD);
        }
        /*修改密码*/
        userTbDTO.setUserPassword(EncryptUtils.encryptPassword(newPassword, salt));
        modifyUser(userTbDTO);
    }
}
