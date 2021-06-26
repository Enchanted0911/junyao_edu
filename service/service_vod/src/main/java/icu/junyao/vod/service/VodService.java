package icu.junyao.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author wu
 */
public interface VodService {


    /**
     * 上传视频到阿里云
     * @param file
     * @return
     */
    String uploadVideoAly(MultipartFile file);

    /**
     * 批量删除视频
     * @param videoIdList
     */
    void removeMoreAlyVideo(List<String> videoIdList);
}
