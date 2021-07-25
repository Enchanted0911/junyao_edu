package icu.junyao.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import icu.junyao.eduService.client.VodClient;
import icu.junyao.eduService.entity.EduVideo;
import icu.junyao.eduService.mapper.EduVideoMapper;
import icu.junyao.eduService.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author junyao
 * @since 2021-06-16
 */
@Service
@RequiredArgsConstructor
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    private final VodClient vodClient;

    @Override
    public void removeVideoByCourseId(String courseId) {
        //1 根据课程id查询课程所有的视频id
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideoList = baseMapper.selectList(wrapperVideo);
        // List<EduVideo>变成List<String>
        List<String> videoIds = new ArrayList<>();
        for (EduVideo eduVideo : eduVideoList) {
            if (eduVideo != null) {
                //放到videoIds集合里面
                videoIds.add(eduVideo.getVideoSourceId());
            }
        }

        //根据多个视频id删除多个视频
        if(videoIds.size()>0) {
            vodClient.deleteBatch(videoIds);
        }

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
