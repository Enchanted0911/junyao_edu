package icu.junyao.eduService.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.eduService.entity.frontVo.CourseFrontVo;
import icu.junyao.eduService.entity.frontVo.CourseWebVo;
import icu.junyao.eduService.entity.vo.CourseInfoVo;
import icu.junyao.eduService.entity.vo.CoursePublishVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author junyao
 * @since 2021-06-16
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加一条课程信息
     * @param courseInfoVo
     * @return
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据课程id获取课程基本信息
     * @param courseId
     * @return
     */
    CourseInfoVo getCourseInfo(String courseId);

    /**
     * 修改课程信息
     * @param courseInfoVo
     */
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据id获得课程的最终详情
     * @param id
     * @return
     */
    CoursePublishVo publishCourseInfo(String id);

    /**
     * 删除课程
     * @param courseId
     */
    void removeCourse(String courseId);

    /**
     * 将首页数据存入redis中
     * @param wrapper
     * @return
     */
    List<EduCourse> listByRedis(QueryWrapper<EduCourse> wrapper);

    /**
     * 获得课程分页列表信息
     * @param pageCourse
     * @param courseFrontVo
     * @return
     */
    Map<String, Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    /**
     * 根据课程id 获取课程详情
     * @param courseId
     * @return
     */
    CourseWebVo getBaseCourseInfo(String courseId);
}
