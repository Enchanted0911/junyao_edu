package icu.junyao.ossService.controller;

import icu.junyao.commonUtils.R;
import icu.junyao.ossService.contant.FileContants;
import icu.junyao.ossService.service.OssService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author wu
 */
@RestController
@RequestMapping("/eduOss/fileOss")
@RequiredArgsConstructor
public class OssController {

    private final OssService ossService;

    @PostMapping
    @RequestMapping("/teachAvatar")
    public R uploadOssFileTeachAvatar(@RequestPart("file") MultipartFile file) {
        //获取上传文件  MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadPicture(file, FileContants.TEACHER_AVATAR_PATH);
        return R.ok().data("url", url);
    }

    @PostMapping
    @RequestMapping("/courseCover")
    public R uploadOssFileCourseCover(@RequestPart("file") MultipartFile file) {
        String url = ossService.uploadPicture(file, FileContants.COURSE_COVER_PATH);
        return R.ok().data("url", url);
    }

    @PostMapping
    @RequestMapping("/banner")
    public R uploadOssFileBanner(@RequestPart("file") MultipartFile file) {
        String url = ossService.uploadPicture(file, FileContants.BANNER_PATH);
        return R.ok().data("url", url);
    }

    @PostMapping
    @RequestMapping("/member")
    public R uploadOssFileMember(@RequestPart("file") MultipartFile file) {
        String url = ossService.uploadPicture(file, FileContants.MEMBER_PATH);
        return R.ok().data("url", url);
    }
}
