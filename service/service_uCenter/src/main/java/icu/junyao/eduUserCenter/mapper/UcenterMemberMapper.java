package icu.junyao.eduUserCenter.mapper;

import icu.junyao.eduUserCenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author junyao
 * @since 2021-06-22
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    /**
     * 查询某一天用户注册人数
     * @param day
     * @return
     */
    Integer countRegisterDay(String day);
}
