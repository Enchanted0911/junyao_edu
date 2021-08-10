package icu.junyao.eduCms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.commonUtils.R;
import icu.junyao.eduCms.entity.CrmBanner;
import icu.junyao.eduCms.service.CrmBannerService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
    @GetMapping("pageBanner/{page}/{limit}")
    public R pageBanner(@PathVariable long page, @PathVariable long limit) {
        Page<CrmBanner> pageBanner = new Page<>(page,limit);
        bannerService.page(pageBanner,null);
        return R.ok().data("items",pageBanner.getRecords()).data("total",pageBanner.getTotal());
    }

    /**
     * 2 添加banner
     * @param crmBanner
     * @return
     */
    @PostMapping("addBanner")
    public R addBanner(@RequestBody CrmBanner crmBanner) {
        bannerService.save(crmBanner);
        return R.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getById(id);
        return R.ok().data("item", banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateById(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeById(id);
        return R.ok();
    }
}

