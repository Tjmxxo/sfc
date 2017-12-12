package cc.xpress.rabbotmq;

import cc.xpress.bean.vo.RabbitMassage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-06 14:30
 * @modified By:
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-rabbitmq.xml"})

public class TestQueue{
    @Autowired
    IMQProducer mqProducer;
    final String queue_key = "delay_queue";
    @Test
    public void send(){
        Map<String,Object> msg = new HashMap<>();
        RabbitMassage rabbitMassage = new RabbitMassage();
        rabbitMassage.setMessageCreateTime(System.currentTimeMillis());
        mqProducer.sendDataToQueue(rabbitMassage);
    }
}
