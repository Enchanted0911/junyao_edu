package icu.junyao.eduOrder.client;


import icu.junyao.commonUtils.ordervo.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author wu
 */
@Component
@FeignClient("service-uCenter")
public interface UcenterClient {


    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    @PostMapping("/eduUserCenter/member/getUserInfoOrder/{id}")
    UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
