package icu.junyao.eduService.service;

import icu.junyao.eduService.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.eduService.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author junyao
 * @since 2021-06-16
 */
public interface EduChapterService extends IService<EduChapter> {

    /**
     * 根据课程id查询章节小节
     * @param courseId
     * @return
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    /**
     * 删除小节
     * @param chapterId
     * @return
     */
    boolean deleteChapter(String chapterId);

    /**
     * 根据课程id删除小节
     * @param courseId
     */
    void removeChapterByCourseId(String courseId);
}
