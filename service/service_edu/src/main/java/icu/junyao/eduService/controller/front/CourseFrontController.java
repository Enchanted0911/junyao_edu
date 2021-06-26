package icu.junyao.eduService.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.commonUtils.R;
import icu.junyao.eduService.entity.EduCourse;
import icu.junyao.eduService.entity.chapter.ChapterVo;
import icu.junyao.eduService.entity.frontVo.CourseFrontVo;
import icu.junyao.eduService.entity.frontVo.CourseWebVo;
import icu.junyao.eduService.service.EduChapterService;
import icu.junyao.eduService.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author wu
 */
@RestController
@RequestMapping("/eduService/courseFront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;



    /**
     * 1 条件查询带分页查询课程
     * @param page
     * @param limit
     * @param courseFrontVo
     * @return
     */
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String,Object> map = courseService.getCourseFrontList(pageCourse,courseFrontVo);
        //返回分页所有数据
        return R.ok().data(map);
    }



    /**
     * 2 课程详情的方法
     * @param courseId
     * @return
     */
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVideoList);
    }
}












