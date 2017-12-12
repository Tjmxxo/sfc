package cc.xpress.shiro;

import cc.xpress.bean.dto.PermissionTbDTO;
import cc.xpress.bean.dto.RoleTbDTO;
import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.service.IUserService;
import cc.xpress.utils.EncryptUtils;
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

@Controller
public class Realm extends AuthorizingRealm {

    @Resource(name = "userService1")
    private IUserService userService;

    /**
     * 权限验证方法
     * （自动执行）
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        List<String> roleList = new ArrayList<String>();
        List<String> permissionList = new ArrayList<String>();
        //提供当前用户有哪些权限
        String userId = principalCollection.getPrimaryPrincipal().toString();
        UserTbDTO userTbDTO = new UserTbDTO();
        userTbDTO.setUserId(Integer.parseInt(userId));
        try {
            UserTbDTO user = userService.getUserById(userTbDTO);
            //获取当前用户的角色
            Set<RoleTbDTO> roles = user.getRoleTbDTOSet();
            Iterator<RoleTbDTO> iterator = roles.iterator();
            while (iterator.hasNext()) {
                RoleTbDTO roleDTO = iterator.next();
                String roleName = roleDTO.getRoleName();
                roleList.add(roleName);
                //遍历角色对应的所有权限
                Set<PermissionTbDTO> permissions = roleDTO.getPermissionTbDTOSet();
                Iterator<PermissionTbDTO> permissionDTOIterator = permissions.iterator();
                while (permissionDTOIterator.hasNext()) {
                    PermissionTbDTO permissionDTO = permissionDTOIterator.next();
                    String permisionName = permissionDTO.getPermissionName();
                    permissionList.add(permisionName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将从数据库中查询出来的当前用户的角色名称配置到shiro中
        simpleAuthorizationInfo.addRoles(roleList);
        //将从数据库中查询出来的当前用户的权限配置到shiro中
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证的方法
     * （自动执行）
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        char[] credentials = (char[]) token.getCredentials();
        String password = new String(credentials);
        String username = token.getPrincipal().toString();
        UserTbDTO user = null;
        try {
            user = userService.getUserByAccount(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            throw new AuthenticationException("用户名或密码验证失败");
        }
        String testpassword = EncryptUtils.encryptPassword(password, user.getUserSalt());
        if (!testpassword.equals(user.getUserPassword())) {
            throw new AuthenticationException("用户名或密码验证失败");
        }
        return new SimpleAuthenticationInfo(user.getUserId(), password, getName());
    }
}