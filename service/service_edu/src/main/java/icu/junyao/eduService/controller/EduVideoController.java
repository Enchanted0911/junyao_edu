package icu.junyao.eduService.controller;


import icu.junyao.commonUtils.R;
import icu.junyao.eduService.client.VodClient;
import icu.junyao.eduService.entity.EduChapter;
import icu.junyao.eduService.entity.EduVideo;
import icu.junyao.eduService.service.EduVideoService;
import icu.junyao.serviceBase.exceptionHandler.JunYaoException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author junyao
 * @since 2021-06-16
 */
@RestController
@RequestMapping("/eduService/video")
@RequiredArgsConstructor
public class EduVideoController {
    private final EduVideoService videoService;
    private final VodClient vodClient;

    /**
     * 根据小节id查询
     * @param videoId
     * @return
     */
    @GetMapping("getVideoInfo/{videoId}")
    public R getVideoInfo(@PathVariable String videoId) {
        EduVideo eduVideo = videoService.getById(videoId);
        return R.ok().data("video",eduVideo);
    }


    /**
     * 添加小节
     * @param eduVideo
     * @return
     */
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        videoService.save(eduVideo);
        return R.ok();
    }

    /**
     * 修改小节
     * @param eduVideo
     * @return
     */
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        videoService.updateById(eduVideo);
        return R.ok();
    }

    /**
     * 删除小节
     */
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id) {
        int errCode = 20001;
        //根据小节id获取视频id，调用方法实现视频删除
        EduVideo eduVideo = videoService.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        //判断小节里面是否有视频id
        if(!StringUtils.isEmpty(videoSourceId)) {
            //根据视频id，远程调用实现视频删除
            R result = vodClient.removeAlyVideo(videoSourceId);
            if(result.getCode() == errCode) {
                throw new JunYaoException(20001,"删除视频失败，熔断器...");
            }
        }
        //删除小节
        videoService.removeById(id);
        return R.ok();
    }
}

