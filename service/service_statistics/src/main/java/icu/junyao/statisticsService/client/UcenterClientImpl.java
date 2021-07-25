package icu.junyao.statisticsService.client;

import icu.junyao.commonUtils.R;

/**
 * @author wu
 */
public class UcenterClientImpl implements UcenterClient {
    @Override
    public R countRegister(String day) {
        return R.error().message("查询注册人数失败!");
    }
}
