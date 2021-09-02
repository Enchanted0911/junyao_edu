package icu.junyao.eduCms.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.commonUtils.R;
import icu.junyao.eduCms.entity.CrmBanner;
import icu.junyao.eduCms.req.BannerReq;
import icu.junyao.eduCms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 后台banner管理接口
 * </p>
 *
 * @author wu
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/eduCms/bannerAdmin")
@RequiredArgsConstructor
public class BannerAdminController {

    private final CrmBannerService bannerService;

    /**
     * 1 分页查询banner
     * @param page
     * @param limit
     * @return
     */
    @PostMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page, @PathVariable long limit, @RequestBody(required = false) BannerReq bannerReq) {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        LambdaQueryWrapper<CrmBanner> wrapper = Wrappers.lambdaQuery();
        wrapper.ge(StringUtils.isNotEmpty(bannerReq.getStart()), CrmBanner::getGmtCreate, bannerReq.getStart())
                .le(StringUtils.isNotEmpty(bannerReq.getEnd()), CrmBanner::getGmtCreate, bannerReq.getEnd())
                .like(StringUtils.isNotEmpty(bannerReq.getTitle()), CrmBanner::getTitle, bannerReq.getTitle())
                .like(StringUtils.isNotEmpty(bannerReq.getLinkUrl()), CrmBanner::getLinkUrl, bannerReq.getLinkUrl())
                .orderByAsc(CrmBanner::getSort);
        bannerService.page(pageBanner,wrapper);
        return R.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    /**
     * 添加banner
     * @param crmBanner
     * @return
     */
    @PostMapping
    public R addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.saveUniqueTitle(crmBanner);
        return R.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("banner", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateUniqueTitle(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }
}

