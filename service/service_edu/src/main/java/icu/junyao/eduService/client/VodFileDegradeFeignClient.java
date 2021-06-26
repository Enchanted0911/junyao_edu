package icu.junyao.eduService.client;

import icu.junyao.commonUtils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wu
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {


    /**
     * 出错之后会执行
     * @param id
     * @return
     */
    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("删除视频出错了");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("删除多个视频出错了");
    }
}
