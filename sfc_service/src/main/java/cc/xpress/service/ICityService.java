package cc.xpress.service;

import cc.xpress.bean.dto.CityTbDTO;

import java.util.List;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-01 17:59
 * @modified By:
 */
public interface ICityService extends IBaseService<CityTbDTO> {
    /**
     * 查询所有城市
     *
     * @return
     */
    List<CityTbDTO> getAllCity();
}
