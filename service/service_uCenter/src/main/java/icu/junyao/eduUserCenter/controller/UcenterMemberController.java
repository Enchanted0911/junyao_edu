package icu.junyao.eduUserCenter.controller;


import icu.junyao.commonUtils.JwtUtils;
import icu.junyao.commonUtils.R;
import icu.junyao.commonUtils.ordervo.UcenterMemberOrder;
import icu.junyao.eduUserCenter.entity.UcenterMember;
import icu.junyao.eduUserCenter.entity.vo.RegisterVo;
import icu.junyao.eduUserCenter.req.PasswordReq;
import icu.junyao.eduUserCenter.service.UcenterMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author junyao
 * @since 2021-06-22
 */
@RestController
@RequestMapping("/eduUserCenter/member")
@RequiredArgsConstructor
public class UcenterMemberController {
    private final UcenterMemberService memberService;
    /**
     * 登录
     * @param member
     * @return
     */
    @PostMapping("login")
    public R loginUser(@RequestBody UcenterMember member) {
        //member对象封装手机号和密码
        //调用service方法实现登录
        //返回token值，使用jwt生成
        String token = memberService.login(member);
        return R.ok().data("token",token);
    }

    /**
     * 注册
     * @param registerVo
     * @return
     */
    @PostMapping("register")
    public R registerUser(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    @PutMapping
    public R updateUserInfo(@RequestBody UcenterMember ucenterMember) {
        memberService.updateById(ucenterMember);
        return R.ok();
    }

    /**
     * 根据token获取用户信息
     * @param request
     * @return
     */
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库根据用户id获取用户信息
        UcenterMember member = memberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }

    @GetMapping("getMemberInfoById/{id}")
    public R getMemberInfoById(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        return R.ok().data("userInfo",member);
    }

    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    @PostMapping("getUserInfoOrder/{id}")
    public UcenterMemberOrder getUserInfoOrder(@PathVariable String id) {
        UcenterMember member = memberService.getById(id);
        UcenterMemberOrder ucenterMemberOrder = new UcenterMemberOrder();
        BeanUtils.copyProperties(member,ucenterMemberOrder);
        return ucenterMemberOrder;
    }

    /**
     * 查询某一天的用户在线人数
     * @param day
     * @return
     */
    @GetMapping("countRegister/{day}")
    public R countRegister(@PathVariable String day) {
        Integer count = memberService.countRegisterDay(day);
        return R.ok().data("countRegister",count);
    }

    @PutMapping("password")
    public R updatePassword(@Valid @RequestBody PasswordReq passwordReq) {
        memberService.updatePassword(passwordReq);
        return R.ok();
    }
}

