import cc.xpress.bean.dto.UserTbDTO;
import cc.xpress.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-11-23 21:13
 * @modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dubbo-test.xml")
public class UserServiceTest {
    @Resource(name = "userService")
    IUserService userService;

    @Test
    public void getUserByIdTest() {
        UserTbDTO userTbDTO = new UserTbDTO();
        userTbDTO.setUserId(1);
        UserTbDTO userById = userService.getUserById(userTbDTO);
        System.out.println(userById.getUserAccount());
    }
}