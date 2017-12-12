package cc.xpress.provider.impl.service;

import cc.xpress.dao.ICinemaDAO;
import cc.xpress.dao.IHallDAO;
import cc.xpress.dao.IMovieDAO;
import cc.xpress.dao.IPlanDAO;
import cc.xpress.service.IPlanAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Create By Tjmxxo
 */

@Service("planAddService")
public class PlanAddService implements IPlanAdd {

    @Autowired
    private IPlanDAO planDAO;

    @Autowired
    private IMovieDAO movieDAO;

    @Autowired
    private ICinemaDAO cinemaDAO;

    @Autowired
    private IHallDAO hallDAO;

    @Transactional
    @Override
    public void planAdd() {
//        planDAO.query("from", PlanTbDTO.class,new Node(""))
    }
}
