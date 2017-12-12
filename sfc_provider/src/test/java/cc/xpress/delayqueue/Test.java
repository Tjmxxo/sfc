package cc.xpress.delayqueue;

import java.util.concurrent.DelayQueue;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-06 9:54
 * @modified By:
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<Message> delayQueue =  new DelayQueue<>();
        for (int i=1;i<11;i++){
            Message m = new Message(i+"",System.currentTimeMillis()+i*4000);
            delayQueue.add(m);
        }

        while(!delayQueue.isEmpty()){
            Message message = delayQueue.take();//此处会阻塞
            System.out.println(message.getId());
        }
    }
}
