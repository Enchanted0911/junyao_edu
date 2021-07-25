package icu.junyao.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.commonUtils.R;
import icu.junyao.eduService.client.UcenterMember;
import icu.junyao.eduService.entity.EduComment;
import icu.junyao.eduService.entity.EduCourse;
import icu.junyao.eduService.mapper.EduCommentMapper;
import icu.junyao.eduService.service.EduCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author junyao
 * @since 2021-07-10
 */
@Service
@RequiredArgsConstructor
public class EduCommentServiceImpl extends ServiceImpl<EduCommentMapper, EduComment> implements EduCommentService {

    private final EduCommentMapper eduCommentMapper;
    @Override
    public Map<String, Object> listCommentsAndGetTotal(long current, long limit, String courseId) {
        Page<EduComment> pageComment = new Page<>(current,limit);
        QueryWrapper<EduComment> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        wrapper.eq("course_id", courseId);
        eduCommentMapper.selectPage(pageComment,wrapper);

        List<EduComment> records = pageComment.getRecords();
        long pages = pageComment.getPages();
        long total = pageComment.getTotal();
        //下一页
        boolean hasNext = pageComment.hasNext();
        //上一页
        boolean hasPrevious = pageComment.hasPrevious();

        //把分页数据获取出来，放到map集合
        Map<String, Object> commentListAndTotalMap = new HashMap<>(16);
        commentListAndTotalMap.put("items", records);
        commentListAndTotalMap.put("current", current);
        commentListAndTotalMap.put("pages", pages);
        commentListAndTotalMap.put("size", limit);
        commentListAndTotalMap.put("total", total);
        commentListAndTotalMap.put("hasNext", hasNext);
        commentListAndTotalMap.put("hasPrevious", hasPrevious);
        return commentListAndTotalMap;
    }

    @Override
    public boolean saveByUserInfo(EduComment comment, LinkedHashMap<String, Object> uCenterInfo) {
        comment.setMemberId((String) uCenterInfo.get("id"));
        comment.setNickname((String) uCenterInfo.get("nickname"));
        comment.setAvatar((String) uCenterInfo.get("avatar"));
        return this.save(comment);
    }
}
