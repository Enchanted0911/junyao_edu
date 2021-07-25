package icu.junyao.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.eduService.entity.EduCourse;
import icu.junyao.eduService.entity.EduCourseDescription;
import icu.junyao.eduService.entity.frontVo.CourseFrontVo;
import icu.junyao.eduService.entity.frontVo.CourseWebVo;
import icu.junyao.eduService.entity.vo.CourseInfoVo;
import icu.junyao.eduService.entity.vo.CoursePublishVo;
import icu.junyao.eduService.mapper.EduCourseMapper;
import icu.junyao.eduService.service.EduChapterService;
import icu.junyao.eduService.service.EduCourseDescriptionService;
import icu.junyao.eduService.service.EduCourseService;
import icu.junyao.eduService.service.EduVideoService;
import icu.junyao.serviceBase.exceptionHandler.JunYaoException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author junyao
 * @since 2021-06-16
 */
@Service
@CacheConfig(cacheNames = "course")
@RequiredArgsConstructor
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    private final EduCourseDescriptionService courseDescriptionService;
    private final EduVideoService eduVideoService;
    private final EduChapterService chapterService;


    /**
     * 添加课程基本信息的方法
     *
     * @param courseInfoVo
     * @return
     */
    @Override
    @CacheEvict(allEntries = true)
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1 向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);
        if (insert == 0) {
            //添加失败
            throw new JunYaoException(20001, "添加课程信息失败");
        }

        //获取添加之后课程id
        String cid = eduCourse.getId();

        //2 向课程简介表添加课程简介
        //edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id就是课程id
        courseDescription.setId(cid);
        if (!courseDescriptionService.save(courseDescription)) {
            throw new JunYaoException(20001, "添加课程描述信息失败");
        }
        return cid;
    }

    @Override
    public CourseInfoVo getCourseInfo(String courseId) {
        //1 查询课程表
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse, courseInfoVo);

        //2 查询描述表
        EduCourseDescription courseDescription = courseDescriptionService.getById(courseId);
        courseInfoVo.setDescription(courseDescription.getDescription());

        return courseInfoVo;
    }

    @Override
    @CacheEvict(allEntries = true)
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //1 修改课程表
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int update = baseMapper.updateById(eduCourse);
        if (update == 0) {
            throw new JunYaoException(20001, "修改课程信息失败");
        }

        //2 修改描述表
        EduCourseDescription description = new EduCourseDescription();
        description.setId(courseInfoVo.getId());
        description.setDescription(courseInfoVo.getDescription());
        courseDescriptionService.updateById(description);
    }

    @Override
    public CoursePublishVo publishCourseInfo(String id) {
        return baseMapper.getPublishCourseInfo(id);
    }

    @Override
    @CacheEvict(allEntries = true)
    public void removeCourse(String courseId) {
        //1 根据课程id删除小节
        eduVideoService.removeVideoByCourseId(courseId);

        //2 根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);

        //3 根据课程id删除描述
        courseDescriptionService.removeById(courseId);

        //4 根据课程id删除课程本身
        int result = baseMapper.deleteById(courseId);
        if (result == 0) {
            throw new JunYaoException(20001, "删除失败");
        }
    }

    @Override
    @Cacheable(key = "'selectIndexList'")
    public List<EduCourse> listByRedis(QueryWrapper<EduCourse> wrapper) {
        return this.list(wrapper);
    }

    @Override
    public Map<String, Object> getCourseFrontList(Page<EduCourse> pageParam, CourseFrontVo courseFrontVo) {
        //2 根据讲师id查询所讲课程
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        //判断条件值是否为空，不为空拼接
        //一级分类
        wrapper.eq(StringUtils.isNotEmpty(courseFrontVo.getSubjectParentId()), "subject_parent_id", courseFrontVo.getSubjectParentId());
        //二级分类
        wrapper.eq(StringUtils.isNotEmpty(courseFrontVo.getSubjectId()), "subject_id", courseFrontVo.getSubjectId());
        //关注度
        wrapper.orderByDesc(StringUtils.isNotEmpty(courseFrontVo.getBuyCountSort()), "buy_count");
        //最新
        wrapper.orderByDesc(StringUtils.isEmpty(courseFrontVo.getGmtCreateSort()), "gmt_create");
        //价格
        wrapper.orderByDesc(StringUtils.isEmpty(courseFrontVo.getPriceSort()), "price");

        baseMapper.selectPage(pageParam, wrapper);

        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        //下一页
        boolean hasNext = pageParam.hasNext();
        //上一页
        boolean hasPrevious = pageParam.hasPrevious();

        //把分页数据获取出来，放到map集合
        Map<String, Object> map = new HashMap<>(16);
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);

        //map返回
        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        return baseMapper.getBaseCourseInfo(courseId);
    }
}
