package icu.junyao.eduService.client;

import icu.junyao.commonUtils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wu
 * @since 2021-07-10
 */
@FeignClient(name = "service-uCenter",fallback = UcenterClientImpl.class)
@Component
public interface UcenterClient {
    /**
     * 根据请求中的token获取用户信息
     * @param request
     * @return
     */
    @GetMapping("eduUserCenter/member/getMemberInfo")
    R getMemberInfo(HttpServletRequest request);

    /**
     * 通过id取得用户信息
     * @param id 用户id
     * @return
     */
    @GetMapping("eduUserCenter/member/getMemberInfoById/{id}")
    R getMemberInfoById(@PathVariable("id") String id);
}
