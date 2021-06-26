package icu.junyao.eduService.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.junyao.eduService.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author junyao
 * @since 2021-06-07
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 将首页数据存入redis中
     * @param wrapperTeacher
     * @return
     */
    List<EduTeacher> listByRedis(QueryWrapper<EduTeacher> wrapperTeacher);

    /**
     * 获取讲师分页列表信息
     * @param pageTeacher
     * @return
     */
    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
