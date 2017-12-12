package cc.xpress.plantimer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("spider")
public class Spider {

    @Scheduled(fixedRate = 10 * 1000)
    public void getNews() {
        System.out.println(new Date() + "-------getNews");
        System.out.println("start");
    }
}