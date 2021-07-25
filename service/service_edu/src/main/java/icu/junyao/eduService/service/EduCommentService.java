package icu.junyao.eduService.service;

import icu.junyao.eduService.client.UcenterMember;
import icu.junyao.eduService.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author junyao
 * @since 2021-07-10
 */
public interface EduCommentService extends IService<EduComment> {

    /**
     * 获取当前页的评论信息
     * @param current 当前页码
     * @param limit 当前页容量
     * @param courseId 课程id
     * @return
     */
    Map<String, Object> listCommentsAndGetTotal(long current, long limit, String courseId);

    /**
     * 通过用户信息保存一条用户评论
     * @param comment 评论信息
     * @param uCenterInfo 用户信息
     * @return
     */
    boolean saveByUserInfo(EduComment comment, LinkedHashMap<String, Object> uCenterInfo);
}
