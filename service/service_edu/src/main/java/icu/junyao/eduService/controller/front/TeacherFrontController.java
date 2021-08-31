package icu.junyao.eduService.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.commonUtils.R;
import icu.junyao.eduService.entity.EduCourse;
import icu.junyao.eduService.entity.EduTeacher;
import icu.junyao.eduService.service.EduCourseService;
import icu.junyao.eduService.service.EduTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author wu
 */
@RestController
@RequestMapping("/eduService/teacherFront")
@RequiredArgsConstructor
public class TeacherFrontController {

    private final EduTeacherService teacherService;
    private final EduCourseService courseService;



    /**
     * 1 分页查询讲师的方法
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page, @PathVariable long limit, @RequestParam(required = false) String teacherName) {
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        Map<String,Object> map = teacherService.getTeacherFrontList(pageTeacher, teacherName);
        //返回分页所有数据
        return R.ok().data(map);
    }

    /**
     * 2 讲师详情的功能
     * @param teacherId
     * @return
     */
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable String teacherId) {
        //1 根据讲师id查询讲师基本信息
        EduTeacher eduTeacher = teacherService.getById(teacherId);
        //2 根据讲师id查询所讲课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);
        List<EduCourse> courseList = courseService.list(wrapper);
        return R.ok().data("teacher",eduTeacher).data("courseList",courseList);
    }
}












