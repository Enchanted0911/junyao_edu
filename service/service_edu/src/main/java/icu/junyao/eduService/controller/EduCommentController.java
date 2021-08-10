package icu.junyao.eduService.controller;


import icu.junyao.commonUtils.JwtUtils;
import icu.junyao.commonUtils.R;
import icu.junyao.eduService.client.UcenterClient;
import icu.junyao.eduService.client.UcenterMember;
import icu.junyao.eduService.entity.EduComment;
import icu.junyao.eduService.service.EduCommentService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author junyao
 * @since 2021-07-10
 */
@RestController
@RequestMapping("/eduService/comment")
@RequiredArgsConstructor
public class EduCommentController {
    private final EduCommentService eduCommentService;
    private final UcenterClient ucenterClient;

    @GetMapping("page-comment/{current}/{limit}")
    public R pageCourseCondition(@PathVariable long current, @PathVariable long limit, @RequestParam String courseId) {
        Map<String, Object> commentListAndTotalMap = eduCommentService.listCommentsAndGetTotal(current, limit, courseId);
        return R.ok().data(commentListAndTotalMap);
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("auth/save")
    public R save(@RequestBody EduComment comment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }
        Object uCenterInfo = (ucenterClient.getMemberInfoById(memberId).getData().get("userInfo"));
        LinkedHashMap<String, Object> map = (LinkedHashMap<String, Object>) uCenterInfo;
        return eduCommentService.saveByUserInfo(comment, map) ? R.ok() : R.error();
    }
}

