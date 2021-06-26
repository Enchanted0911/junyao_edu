package icu.junyao.eduService.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import icu.junyao.commonUtils.R;
import icu.junyao.eduService.entity.EduCourse;
import icu.junyao.eduService.entity.EduTeacher;
import icu.junyao.eduService.service.EduCourseService;
import icu.junyao.eduService.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wu
 */
@RestController
@RequestMapping("/eduService/indexFront")
@CrossOrigin
public class IndexFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduTeacherService teacherService;

    /**
     * 查询前8条热门课程，查询前4条名师
     * @return
     */
    @GetMapping("index")
    public R index() {
        //查询前8条热门课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        List<EduCourse> eduList = courseService.listByRedis(wrapper);

        //查询前4条名师
        QueryWrapper<EduTeacher> wrapperTeacher = new QueryWrapper<>();
        wrapperTeacher.orderByDesc("id");
        wrapperTeacher.last("limit 4");
        List<EduTeacher> teacherList = teacherService.listByRedis(wrapperTeacher);

        return R.ok().data("eduList",eduList).data("teacherList",teacherList);
    }

}
