package icu.junyao.statisticsService.schedule;

import icu.junyao.statisticsService.service.StatisticsDailyService;
import icu.junyao.statisticsService.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wu
 */
@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final StatisticsDailyService statisticsDailyService;

    /**
     *     在每天凌晨1点，把前一天数据进行数据查询添加
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }
}
