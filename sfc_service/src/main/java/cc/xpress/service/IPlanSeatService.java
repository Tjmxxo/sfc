package cc.xpress.service;

import cc.xpress.bean.dto.PlanTbDTO;
import cc.xpress.bean.dto.SelectTbDTO;

import java.util.List;
import java.util.Map;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-03 15:16
 * @modified By:
 */
public interface IPlanSeatService {
    /**
     * 获取当前作为的状态
     *
     * @param planId
     * @return
     */
    Map<Integer, List<SelectTbDTO>> getPlanSeat(String planId);

    /**
     * 根据id查询计划
     *
     * @param planId
     * @return
     */
    PlanTbDTO getPlanInfo(String planId);
}
