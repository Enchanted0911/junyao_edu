package icu.junyao.ossService.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author wu
 */
public interface OssService {
    /**
     * 上传头像到oss
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);

    /**
     * 上传课程封面到oss
     * @param file
     * @return
     */
    String uploadFileCover(MultipartFile file);
}
