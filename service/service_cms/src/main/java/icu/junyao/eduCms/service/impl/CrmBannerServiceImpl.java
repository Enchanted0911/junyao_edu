package icu.junyao.eduCms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.eduCms.entity.CrmBanner;
import icu.junyao.eduCms.mapper.CrmBannerMapper;
import icu.junyao.eduCms.service.CrmBannerService;
import icu.junyao.serviceBase.exceptionHandler.JunYaoException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author wu
 * @since 2020-03-07
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    /**
     * 查询所有banner
     * @return
     */
    @Cacheable(value = "banner",key = "'selectIndexList'")
    @Override
    public List<CrmBanner> selectAllBanner() {
        //根据id进行降序排列，显示排列之后前两条记录
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        //last方法，拼接sql语句
        wrapper.last("limit 2");
        return baseMapper.selectList(null);
    }

    @Override
    @CacheEvict(value = "banner",key = "'selectIndexList'")
    public void saveUniqueTitle(CrmBanner crmBanner) {
        CrmBanner banner = super.getOne(Wrappers.lambdaQuery(CrmBanner.class)
                .eq(CrmBanner::getTitle, crmBanner.getTitle()));
        if (banner != null) {
            throw new JunYaoException(20001, "已经存在相同标题的banner!");
        }
        super.save(crmBanner);
    }

    @Override
    public void updateUniqueTitle(CrmBanner crmBanner) {
        CrmBanner banner = super.getOne(Wrappers.lambdaQuery(CrmBanner.class)
                .eq(CrmBanner::getTitle, crmBanner.getTitle())
                .ne(CrmBanner::getId, crmBanner.getId()));
        if (banner != null) {
            throw new JunYaoException(20001, "已经存在相同标题的banner!");
        }
        super.updateById(crmBanner);
    }
}
