package icu.junyao.eduService.controller;


import icu.junyao.commonUtils.R;
import icu.junyao.eduService.entity.subject.OneSubject;
import icu.junyao.eduService.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author junyao
 * @since 2021-06-15
 */
@RestController
@RequestMapping("/eduService/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;



    /**
     * 添加课程分类
     * 获取上传过来文件，把文件内容读取出来
     * @param file
     * @return
     */
    @PostMapping("addSubject")
    public R addSubject(@RequestPart("file") MultipartFile file) {
        //上传过来excel文件
        subjectService.saveSubject(file,subjectService);
        return R.ok();
    }

    /**
     * 获取课程分类列表（树形）
     * @return
     */
    @GetMapping("getAllSubject")
    public R getAllSubject() {
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return R.ok().data("list",list);
    }
}

