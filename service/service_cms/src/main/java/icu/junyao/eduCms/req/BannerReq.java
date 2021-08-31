package icu.junyao.eduCms.req;

import lombok.Data;

/**
 * @author wu
 */
@Data
public class BannerReq {
    private String linkUrl;
    private String title;
    private String start;
    private String end;
}
