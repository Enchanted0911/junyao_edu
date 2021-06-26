package icu.junyao.eduService.mapper;

import icu.junyao.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import icu.junyao.eduService.entity.frontVo.CourseWebVo;
import icu.junyao.eduService.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author junyao
 * @since 2021-06-16
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    /**
     * 根据id获取课程的最终详情
     * @param id
     * @return
     */
    CoursePublishVo getPublishCourseInfo(String id);

    /**
     * 根据课程id 获取课程详情
     * @param courseId
     * @return
     */
    CourseWebVo getBaseCourseInfo(String courseId);
}
