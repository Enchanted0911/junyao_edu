package icu.junyao.eduUserCenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import icu.junyao.commonUtils.JwtUtils;
import icu.junyao.eduUserCenter.entity.UcenterMember;
import icu.junyao.eduUserCenter.entity.vo.RegisterVo;
import icu.junyao.eduUserCenter.mapper.UcenterMemberMapper;
import icu.junyao.eduUserCenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import icu.junyao.eduUserCenter.utils.MD5;
import icu.junyao.serviceBase.exceptionHandler.JunYaoException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author junyao
 * @since 2021-06-22
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 登录
     *
     * @param member
     * @return
     */
    @Override
    public String login(UcenterMember member) {
        //获取登录手机号和密码
        String mobile = member.getMobile();
        String password = member.getPassword();

        //手机号和密码非空判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new JunYaoException(20001, "用户名和密码不能为空!");
        }

        //判断手机号是否正确
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        UcenterMember mobileMember = baseMapper.selectOne(wrapper);
        //判断查询对象是否为空
        if (mobileMember == null) {
            //没有这个手机号
            throw new JunYaoException(20001, "账号不存在!请先注册");
        }

        //判断密码
        //因为存储到数据库密码肯定加密的
        //把输入的密码进行加密，再和数据库密码进行比较
        //加密方式 MD5
        if (!MD5.encrypt(password).equals(mobileMember.getPassword())) {
            throw new JunYaoException(20001, "密码错误!");
        }

        //判断用户是否禁用
        if (mobileMember.getIsDisabled()) {
            throw new JunYaoException(20001, "账号已冻结!请联系管理员");
        }

        //登录成功
        //生成token字符串，使用jwt工具类
        String jwtToken = JwtUtils.getJwtToken(mobileMember.getId(), mobileMember.getNickname());
        return jwtToken;
    }

    /**
     * 注册
     *
     * @param registerVo
     */
    @Override
    public void register(RegisterVo registerVo) {
        //获取注册的数据
        //验证码
        String code = registerVo.getCode();
        //手机号
        String mobile = registerVo.getMobile();
        //昵称
        String nickname = registerVo.getNickname();
        //密码
        String password = registerVo.getPassword();

        //非空判断
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code) || StringUtils.isEmpty(nickname)) {
            throw new JunYaoException(20001, "注册失败");
        }
        //判断验证码
        //获取redis验证码
        // 后续开启
//        String redisCode = redisTemplate.opsForValue().get(mobile);
//        if (!code.equals(redisCode)) {
//            throw new JunYaoException(20001, "注册失败");
//        }

        //判断手机号是否重复，表里面存在相同手机号不进行添加
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new JunYaoException(20001, "手机号重复, 注册失败");
        }

        //数据添加数据库中
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        //密码需要加密的
        member.setPassword(MD5.encrypt(password));
        //用户不禁用
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        baseMapper.insert(member);
    }

    @Override
    public UcenterMember getOpenIdMember(String openId) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openId);
        return baseMapper.selectOne(wrapper);
    }
}
