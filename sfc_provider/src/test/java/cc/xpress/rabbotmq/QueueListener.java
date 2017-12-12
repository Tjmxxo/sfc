package cc.xpress.rabbotmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-06 14:26
 * @modified By:
 */
@Component("queueListener")
public class QueueListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        try{
            System.out.print(message.toString());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
