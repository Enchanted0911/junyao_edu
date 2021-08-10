package icu.junyao.eduService.controller;


import icu.junyao.commonUtils.R;
import icu.junyao.eduService.entity.EduChapter;
import icu.junyao.eduService.entity.chapter.ChapterVo;
import icu.junyao.eduService.service.EduChapterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eduService/chapter")
@RequiredArgsConstructor
public class EduChapterController {
    private final EduChapterService chapterService;



    /**
     * 课程大纲列表,根据课程id进行查询
     * @param courseId
     * @return
     */
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVideo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return R.ok().data("allChapterVideo",list);
    }



    /**
     * 添加章节
     * @param eduChapter
     * @return
     */
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return R.ok();
    }



    /**
     * 根据章节id查询
     * @param chapterId
     * @return
     */
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = chapterService.getById(chapterId);
        return R.ok().data("chapter",eduChapter);
    }



    /**
     * 修改章节
     * @param eduChapter
     * @return
     */
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return R.ok();
    }



    /**
     * 删除的方法
     * @param chapterId
     * @return
     */
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if(flag) {
            return R.ok();
        } else {
            return R.error();
        }

    }
}

