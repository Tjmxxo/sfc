package cc.xpress.plantimer;

import cc.xpress.provider.impl.service.SelectSeatChange;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * @Create By Tjmxxo
 */
@Component("overSeat")
public class OverSeat {

    @Autowired
    private SelectSeatChange selectSeatChange;

    @Scheduled(fixedRate = 5 * 1000)
    public void execute() throws JobExecutionException, ParseException {
        System.out.println("end");
        selectSeatChange.planEndSeatChange();
    }
}
