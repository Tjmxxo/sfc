package cc.xpress.dao;

import cc.xpress.bean.dto.VipTbDTO;

import java.util.List;

public interface IVipDAO extends IBaseDAO<VipTbDTO> {

    List<VipTbDTO> getAllVipByCinemaId(int cinemaId);
}
