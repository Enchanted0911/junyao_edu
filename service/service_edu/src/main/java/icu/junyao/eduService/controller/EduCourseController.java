package icu.junyao.eduService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.commonUtils.R;
import icu.junyao.eduService.entity.EduCourse;
import icu.junyao.eduService.entity.EduTeacher;
import icu.junyao.eduService.entity.vo.CourseInfoVo;
import icu.junyao.eduService.entity.vo.CoursePublishVo;
import icu.junyao.eduService.entity.vo.EduCourseQuery;
import icu.junyao.eduService.entity.vo.EduTeacherQuery;
import icu.junyao.eduService.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author junyao
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/eduService/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    /**
     * 课程列表 基本实现
     *     TODO  完善条件查询带分页
     * @return
     */
    @PostMapping("pageCourseCondition/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current,@PathVariable long limit,
                                  @RequestBody(required = false) EduCourseQuery courseQuery) {
        //创建page对象
        Page<EduCourse> pageCourse = new Page<>(current,limit);

        //构建条件
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        // 多条件组合查询
        // mybatis学过 动态sql
        String status = courseQuery.getStatus();
        String title = courseQuery.getTitle();
        //判断条件值是否为空，如果不为空拼接条件
        if(!StringUtils.isEmpty(status)) {
            //构建条件
            wrapper.eq("status",status);
        }
        if(!StringUtils.isEmpty(title)) {
            wrapper.like("title",title);
        }

        wrapper.orderByDesc("gmt_create");
        //调用方法实现条件查询分页
        courseService.page(pageCourse,wrapper);

        //总记录数
        long total = pageCourse.getTotal();
        //数据list集合
        List<EduCourse> records = pageCourse.getRecords();
        return R.ok().data("total",total).data("list",records);
    }

    /**
     * 添加课程基本信息的方法
     * @param courseInfoVo
     * @return
     */
    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        //返回添加之后课程id，为了后面添加大纲使用
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",id);
    }


    /**
     * 根据课程id查询课程基本信息
     * @param courseId
     * @return
     */
    @GetMapping("getCourseInfo/{courseId}")
    public R getCourseInfo(@PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo",courseInfoVo);
    }



    /**
     * 修改课程信息
     * @param courseInfoVo
     * @return
     */
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    /**
     * 根据课程id查询课程确认信息
     * @param id
     * @return
     */
    @GetMapping("getPublishCourseInfo/{id}")
    public R getPublishCourseInfo(@PathVariable String id) {
        CoursePublishVo coursePublishVo = courseService.publishCourseInfo(id);
        return R.ok().data("publishCourse",coursePublishVo);
    }



    /**
     * 课程最终发布
     * 修改课程状态
     * @param id
     * @return
     */
    @PostMapping("publishCourse/{id}")
    public R publishCourse(@PathVariable String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        //设置课程发布状态
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }



    /**
     * 删除课程
     * @param courseId
     * @return
     */
    @DeleteMapping("{courseId}")
    public R deleteCourse(@PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return R.ok();
    }
}

