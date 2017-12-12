package cc.xpress.provider.impl.service;

import cc.xpress.bean.dto.CityTbDTO;
import cc.xpress.dao.ICityDAO;
import cc.xpress.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-01 18:01
 * @modified By:
 */
@Service("cityService")
public class CityServiceImpl extends BaseServiceImpl<CityTbDTO> implements ICityService {
    @Autowired
    ICityDAO cityDAO;

    /**
     * 查询所有城市
     *
     * @return
     */
    @Transactional
    @Override
    public List<CityTbDTO> getAllCity() {
        return cityDAO.query("from", CityTbDTO.class).list();
    }
}
