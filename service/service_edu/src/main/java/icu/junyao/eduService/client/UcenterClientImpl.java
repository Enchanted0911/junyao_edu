package icu.junyao.eduService.client;

import icu.junyao.commonUtils.R;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wu
 */
@Component
public class UcenterClientImpl implements UcenterClient{
    @Override
    public R getMemberInfo(HttpServletRequest request) {
        return R.error().message("获取用户信息出错");
    }

    @Override
    public R getMemberInfoById(String id) {
        return R.error().message("获取用户信息出错");
    }
}
