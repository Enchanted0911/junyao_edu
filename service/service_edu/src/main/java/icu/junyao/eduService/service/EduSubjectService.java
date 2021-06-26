package icu.junyao.eduService.service;

import icu.junyao.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.eduService.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author junyao
 * @since 2021-06-15
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 添加课程分类
     * @param file
     * @param subjectService
     */
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    /**
     * 课程分类列表树形结构
     * @return
     */
    List<OneSubject> getAllOneTwoSubject();
}
