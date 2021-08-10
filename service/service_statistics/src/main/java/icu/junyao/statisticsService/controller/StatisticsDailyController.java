package icu.junyao.statisticsService.controller;


import icu.junyao.commonUtils.R;
import icu.junyao.statisticsService.service.StatisticsDailyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author junyao
 * @since 2021-07-24
 */
@RestController
@RequestMapping("/eduStatistics/statistics-daily")
@RequiredArgsConstructor
public class StatisticsDailyController {
    private final StatisticsDailyService statisticsDailyService;



    /**
     * 统计某一天注册人数,生成统计数据
     * @param day
     * @return
     */
    @PostMapping("registerCount/{day}")
    public R registerCount(@PathVariable String day) {
        statisticsDailyService.registerCount(day);
        return R.ok();
    }



    /**
     * 图表显示，返回两部分数据，日期json数组，数量json数组
     * @param type
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("showData/{type}/{begin}/{end}")
    public R showData(@PathVariable String type, @PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = statisticsDailyService.getShowData(type,begin,end);
        return R.ok().data(map);
    }
}

