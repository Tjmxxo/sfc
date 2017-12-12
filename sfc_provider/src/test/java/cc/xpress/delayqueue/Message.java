package cc.xpress.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-06 9:44
 * @modified By:
 */
public class Message implements Delayed {
    private String id;
    private long insertTime ;//开始时间，广告上下架时间。

    public Message(String id,long insertTime){
        this.id = id;
        this.insertTime =  insertTime;
    }

    //获取失效时间
    @Override
    public long getDelay(TimeUnit unit) {
        //获取失效时间
        return this.insertTime+60000-System.currentTimeMillis();
    }


    @Override
    public int compareTo(Delayed o) {
        //比较 1是放入队尾  -1是放入队头
        Message that = (Message)o;
        if(this.insertTime>that.insertTime){
            return 1;
        }
        else  if(this.insertTime==that.insertTime){
            return 0;
        }else {
            return -1;
        }
    }

    public String getId() {
        return id;
    }
}
