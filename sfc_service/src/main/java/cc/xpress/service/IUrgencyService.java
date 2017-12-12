package cc.xpress.service;

/**
 * @Create By Tjmxxo
 */
public interface IUrgencyService {

    /**
     * 改变影厅状态,停止售票,退还已购票
     *
     * @return
     */
    String planStateChange(int id, long longTime, int state);

    /**
     * 改变座位状态,停止售票,退还已购票
     *
     * @return
     */
    String seatStateChange(int seatId);

    /**
     * 停止影院售票,退还已购票
     *
     * @return
     */
    String cinemaStateChange(int cinemaId);

}
