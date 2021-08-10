package icu.junyao.statisticsService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import icu.junyao.commonUtils.R;
import icu.junyao.statisticsService.client.UcenterClient;
import icu.junyao.statisticsService.entity.StatisticsDaily;
import icu.junyao.statisticsService.mapper.StatisticsDailyMapper;
import icu.junyao.statisticsService.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author junyao
 * @since 2021-07-24
 */
@RequiredArgsConstructor
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {

    private final UcenterClient ucenterClient;

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        //根据条件查询对应数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);
        wrapper.select("date_calculated",type);
        wrapper.orderByAsc("date_calculated");
        List<StatisticsDaily> staList = baseMapper.selectList(wrapper);

        //因为返回有两部分数据：日期 和 日期对应数量
        //前端要求数组json结构，对应后端java代码是list集合
        //创建两个list集合，一个日期list，一个数量list
        List<String> dateCalculatedList = new ArrayList<>();
        List<Integer> numDataList = new ArrayList<>();

        //遍历查询所有数据list集合，进行封装
        for (StatisticsDaily daily : staList) {
            //封装日期list集合
            dateCalculatedList.add(daily.getDateCalculated());
            //封装对应数量
            switch (type) {
                case "login_num":
                    numDataList.add(daily.getLoginNum());
                    break;
                case "register_num":
                    numDataList.add(daily.getRegisterNum());
                    break;
                case "video_view_num":
                    numDataList.add(daily.getVideoViewNum());
                    break;
                case "course_num":
                    numDataList.add(daily.getCourseNum());
                    break;
                default:
                    break;
            }
        }
        //把封装之后两个list集合放到map集合，进行返回
        Map<String, Object> map = new HashMap<>();
        map.put("date_calculatedList",dateCalculatedList);
        map.put("numDataList",numDataList);
        return map;
    }

    @Override
    public void registerCount(String day) {
        //添加记录之前删除表相同日期的数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated",day);
        baseMapper.delete(wrapper);

        //远程调用得到某一天注册人数
        R registerR = ucenterClient.countRegister(day);
        Integer countRegister = (Integer)registerR.getData().get("countRegister");

        //把获取数据添加数据库，统计分析表里面
        StatisticsDaily sta = new StatisticsDaily();
        //注册人数
        sta.setRegisterNum(countRegister);
        //统计日期
        sta.setDateCalculated(day);

        sta.setVideoViewNum(RandomUtils.nextInt(100,200));
        sta.setLoginNum(RandomUtils.nextInt(100,200));
        sta.setCourseNum(RandomUtils.nextInt(100,200));
        baseMapper.insert(sta);
    }
}
