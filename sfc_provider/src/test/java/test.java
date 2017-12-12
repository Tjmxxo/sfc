import cc.xpress.bean.dto.HallTbDTO;
import cc.xpress.dao.IMovieDAO;
import cc.xpress.dao.IOrderDAO;
import cc.xpress.dao.IOrderSeatDAO;
import cc.xpress.dao.IPlanDAO;
import cc.xpress.provider.impl.service.SelectSeatChange;
import cc.xpress.service.*;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

/**
 * @Create By Tjmxxo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-provider.xml")
@Rollback(false)
public class test {

    @Autowired
    IPlanDAO planDAO;

    @Autowired
    IUserService userService;

    @Autowired
    IMovieService movieService;

    @Autowired
    ICinemaService cinemaService;

    @Autowired
    IVipService vipService;

    @Autowired
    SelectSeatChange selectSeatChange;
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ISeatService seatService;

    @Autowired
    private IOrderDAO orderDAO;

    @Autowired
    private IMovieDAO movieDAO;

    @Autowired
    private IUserCountService count;

    @Autowired
    private IOrderSeatDAO orderSeatDAO;

    @Autowired
    private IUrgencyService urgencyService;

    @Autowired
    private IHallService hallService;
    @Transactional
    @Test
    public void test3() throws InterruptedException, ParseException {
        HallTbDTO hallTbDTO = new HallTbDTO();
        hallTbDTO.setHallName("test");
        hallTbDTO.setHallScreenType("IMAX");
        hallTbDTO.setHallDescribe("无");
        hallTbDTO.setHallMaxCol(10);
        hallTbDTO.setHallMaxRow(10);
        hallService.saveHall(hallTbDTO,1);
    }
}
