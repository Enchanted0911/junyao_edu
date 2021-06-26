package icu.junyao.eduService.service;

import icu.junyao.eduService.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author junyao
 * @since 2021-06-16
 */
public interface EduVideoService extends IService<EduVideo> {

    /**
     * 根据课程id删除小节
     * @param courseId
     */
    void removeVideoByCourseId(String courseId);
}
