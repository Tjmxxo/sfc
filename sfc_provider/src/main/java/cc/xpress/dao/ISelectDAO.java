package cc.xpress.dao;

import cc.xpress.bean.dto.SelectTbDTO;

public interface ISelectDAO extends IBaseDAO<SelectTbDTO> {
    /**
     * 查询座位是否可以购买并且加锁
     *
     * @param planId
     * @param seatId
     * @return
     */
    SelectTbDTO getSelectWithLock(int planId, int seatId);

    SelectTbDTO getSelectByPlanAndSeatId(int seatId,int planId);
}
