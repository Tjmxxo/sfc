import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * @author: Robben.Hu
 * @Description:
 * @Date: Created in 2017-12-04 15:01
 * @modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-providers.xml")
@Rollback(false)
public class RedisTest {
    @Autowired
    ShardedJedisPool shardedJedisPool;

    @Test
    public void redisLockTest() {
        ShardedJedis resource = shardedJedisPool.getResource();
        while (true) {
            if (resource.setnx("1.lock", "locked") == 1) {
                System.out.println("获取到锁，执行方法");
                return;
            }
            System.out.println("等待获取锁");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void redisunLockTest() {
        ShardedJedis resource = shardedJedisPool.getResource();
        resource.del("1.lock");
    }
}
