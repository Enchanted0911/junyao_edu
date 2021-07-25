package icu.junyao.statisticsService.service;

import icu.junyao.statisticsService.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author junyao
 * @since 2021-07-24
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     * 图表显示，返回两部分数据，日期json数组，数量json数组
     * @param type
     * @param begin
     * @param end
     * @return
     */
    Map<String, Object> getShowData(String type, String begin, String end);

    /**
     * 统计某一天注册人数,生成统计数据
     * @param day
     */
    void registerCount(String day);
}
