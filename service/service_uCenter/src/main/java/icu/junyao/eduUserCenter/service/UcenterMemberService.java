package icu.junyao.eduUserCenter.service;

import icu.junyao.eduUserCenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import icu.junyao.eduUserCenter.entity.vo.RegisterVo;
import icu.junyao.eduUserCenter.req.PasswordReq;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author junyao
 * @since 2021-06-22
 */
public interface UcenterMemberService extends IService<UcenterMember> {
    /**
     * 登录
     * @param member
     * @return
     */
    String login(UcenterMember member);

    /**
     * 注册
     * @param registerVo
     */
    void register(RegisterVo registerVo);

    /**
     * 根据openId判断数据表里是否存在相同微信信息
     * @param openId
     * @return
     */
    UcenterMember getOpenIdMember(String openId);

    /**
     * 查询某一天注册人数
     * @param day
     * @return
     */
    Integer countRegisterDay(String day);

    /**
     * 更新密码
     *
     * @param passwordReq {@link PasswordReq}
     */
    void updatePassword(PasswordReq passwordReq);
}
