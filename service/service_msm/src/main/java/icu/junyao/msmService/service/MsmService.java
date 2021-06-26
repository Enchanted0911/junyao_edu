package icu.junyao.msmService.service;

import java.util.Map;

/**
 * @author wu
 */
public interface MsmService {


    /**
     * 发送短信的方法
     * @param param
     * @param phone
     * @return
     */
    boolean send(Map<String, Object> param, String phone) throws Exception;
}
