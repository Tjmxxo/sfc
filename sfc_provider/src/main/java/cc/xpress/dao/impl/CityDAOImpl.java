package cc.xpress.dao.impl;

import cc.xpress.bean.dto.CityTbDTO;
import cc.xpress.dao.ICityDAO;
import org.springframework.stereotype.Repository;

@Repository("cityDAO")
public class CityDAOImpl extends BaseDAOImpl<CityTbDTO> implements ICityDAO {

}
