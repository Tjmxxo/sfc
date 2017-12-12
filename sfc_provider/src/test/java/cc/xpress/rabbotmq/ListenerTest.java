package cc.xpress.rabbotmq;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-06 14:52
 * @modified By:
 */
public class ListenerTest {
    @Test
    public void Start(){
        ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext("spring-rabbitmq.xml");
        path.start();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
