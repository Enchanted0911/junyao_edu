package icu.junyao.eduService.client;

import icu.junyao.commonUtils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 调用的服务名称
 * @author wu
 */
@FeignClient(name = "service-vod",fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {



    /**
     * 定义调用的方法路径
     * 根据视频id删除阿里云视频
     * @PathVariable 注解一定要指定参数名称，否则出错
     * @param id
     * @return
     */
    @DeleteMapping("/eduVod/video/removeAlyVideo/{id}")
    R removeAlyVideo(@PathVariable("id") String id);



    /**
     * 定义调用删除多个视频的方法
     *     删除多个阿里云视频的方法
     *     参数多个视频id  List videoIdList
     * @param videoIdList
     * @return
     */
    @DeleteMapping("/eduVod/video/delete-batch")
    R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList);
}
