import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @Create By Tjmxxo
 */
public class test {

    @Test
    public void test1() {
        Random random = new Random();
        random.nextLong();
        for (int i = 0; i < 20; i++) {
            long l = System.currentTimeMillis() + random.nextInt(10000);
            String s = String.valueOf(l);
            System.out.println(s);
        }
    }
}
