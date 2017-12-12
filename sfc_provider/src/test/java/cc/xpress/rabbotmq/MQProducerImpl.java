package cc.xpress.rabbotmq;

import cc.xpress.bean.vo.RabbitMassage;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-06 14:21
 * @modified By:
 */
@Service
public class MQProducerImpl implements IMQProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;
    Logger logger=Logger.getLogger(MQProducerImpl.class);
    private final static Logger LOGGER = Logger.getLogger(MQProducerImpl.class);

    @Override
    public void sendDataToQueue(RabbitMassage msg) {
        try {
            logger.info("create rabbitMQ message:   "+msg.toString());
            amqpTemplate.convertAndSend(msg);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
