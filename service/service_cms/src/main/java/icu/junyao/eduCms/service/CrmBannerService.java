package icu.junyao.eduCms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.eduCms.entity.CrmBanner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author wu
 * @since 2020-03-07
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 查询所有banner
     *
     * @return
     */
    List<CrmBanner> selectAllBanner();

    /**
     * 新增一条banner
     *
     * @param crmBanner 待新增的banner
     */
    void saveUniqueTitle(CrmBanner crmBanner);

    /**
     * 更新一条banner
     *
     * @param banner 待更新的banner
     */
    void updateUniqueTitle(CrmBanner banner);
}
