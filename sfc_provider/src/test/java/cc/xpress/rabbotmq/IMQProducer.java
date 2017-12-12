package cc.xpress.rabbotmq;

import cc.xpress.bean.vo.RabbitMassage;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-06 14:21
 * @modified By:
 */
public interface IMQProducer {
    /**
     * 发送消息到指定队列
     * @param msg
     */
    public void sendDataToQueue(RabbitMassage msg);
}
