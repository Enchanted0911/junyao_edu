package icu.junyao.eduOrder.client;

import icu.junyao.commonUtils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author wu
 */
@Component
@FeignClient("service-edu")
public interface EduClient {

    /**
     * 根据课程id查询课程信息
     * @param id
     * @return
     */
    @PostMapping("/eduService/courseFront/getCourseInfoOrder/{id}")
    CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);

}
