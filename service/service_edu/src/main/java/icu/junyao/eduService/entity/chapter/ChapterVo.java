package icu.junyao.eduService.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wu
 */
@Data
public class ChapterVo {

    private String id;

    private String title;


    /**
     * 表示小节
     */
    private List<VideoVo> children = new ArrayList<>();
}
