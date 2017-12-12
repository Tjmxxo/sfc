package cc.xpress.utils;

import cc.xpress.bean.dto.PermissionTbDTO;
import cc.xpress.bean.dto.RoleTbDTO;
import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.service.IFrontUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-10-31 19:33
 * @modified By:
 */
@Controller
public class FrontRealm extends AuthorizingRealm {

    @Resource(name = "frontUserService")
    IFrontUserService frontUserService;

    /**
     * 给当前realm指定一个名字，必须唯一
     *
     * @return
     */
    @Override
    public String getName() {
        return "hibernate_realm";
    }

    /**
     * 权限验证方法
     * （自动执行）
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        List<String> roleList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();
        Set<RoleTbDTO> userRoles = principals.asSet();
        Iterator<RoleTbDTO> iterator = userRoles.iterator();
        while (iterator.hasNext()) {
            RoleTbDTO roleTbDTO = iterator.next();
            Set<PermissionTbDTO> permissionTbDTOSet = roleTbDTO.getPermissionTbDTOSet();
            if (permissionTbDTOSet.size() != 0) {
                for (PermissionTbDTO permissionTbDTO : permissionTbDTOSet)
                    permissionList.add(permissionTbDTO.getPermissionName());
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleList);
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }

    /**
     * 登陆认证的方法
     * （自动执行）
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        char[] credentials = (char[]) token.getCredentials();
        /*用户输入的账户和密码*/
        String password = new String(credentials);
        String username = token.getPrincipal().toString();
        UserTbDTO user = new UserTbDTO();
        user.setUserTel(username);
        user.setUserPassword(password);
        /*根据用户输入的账户名称查询用户*/
        UserTbDTO userTbDTO = frontUserService.getUserByTel(user);
        if (userTbDTO == null) {
            throw new NullPointerException("用户不存在");
        }
        String encryptPassword = EncryptUtils.encryptPassword(password, userTbDTO.getUserSalt());
        if (userTbDTO.getUserPassword().equals(encryptPassword)) {
            return new SimpleAuthenticationInfo(userTbDTO.getRoleTbDTOSet(), password, getName());
        } else {
            throw new AuthenticationException("账户或密码有误");
        }
    }
}